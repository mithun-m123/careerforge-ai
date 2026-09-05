package com.careerforge.backend.service;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.dto.ATSSuggestionRequest;
import com.careerforge.backend.entity.Experience;
import com.careerforge.backend.entity.ExperienceType;
import com.careerforge.backend.entity.Job;
import com.careerforge.backend.entity.JobSkill;
import com.careerforge.backend.entity.Skill;
import com.careerforge.backend.entity.SkillRequirementType;
import com.careerforge.backend.repository.ExperienceRepository;
import com.careerforge.backend.repository.JobRepository;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.util.SkillNormalizer;

@Service
public class ATSService {

    private final SkillRepository skillRepository;
    private final AIService aiService;
    private final JobRepository jobRepository;
    private final ExperienceRepository experienceRepository;

    public ATSService(
            SkillRepository skillRepository,
            JobRepository jobRepository,
            ExperienceRepository experienceRepository,
            AIService aiService) {

        this.skillRepository = skillRepository;
        this.jobRepository = jobRepository;
        this.experienceRepository = experienceRepository;
        this.aiService = aiService;
    }

    public ATSResponse analyze(ATSRequest request) {

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Job not found: " + request.getJobId()
                        ));

        List<Skill> candidateSkills =
                skillRepository.findByProfileId(request.getProfileId());

        List<JobSkill> requiredSkills = new ArrayList<>();
        List<JobSkill> preferredSkills = new ArrayList<>();

        List<String> matchedRequiredSkills = new ArrayList<>();
        List<String> missingRequiredSkills = new ArrayList<>();
        List<String> matchedPreferredSkills = new ArrayList<>();
        List<String> missingPreferredSkills = new ArrayList<>();

        /*
         * Separate required and preferred job skills.
         */
        for (JobSkill jobSkill : job.getJobSkills()) {

            if (jobSkill.getType() == SkillRequirementType.REQUIRED) {
                requiredSkills.add(jobSkill);
            }

            if (jobSkill.getType() == SkillRequirementType.PREFERRED) {
                preferredSkills.add(jobSkill);
            }
        }

        /*
         * Match required skills.
         */
        for (JobSkill jobSkill : requiredSkills) {

            String requiredSkillName =
                    jobSkill.getSkill().getName();

            String normalizedRequiredSkill =
                    SkillNormalizer.normalize(requiredSkillName);

            boolean matched = false;

            for (Skill candidateSkill : candidateSkills) {

                String candidateSkillName =
                        candidateSkill.getName();

                String normalizedCandidateSkill =
                        SkillNormalizer.normalize(candidateSkillName);

                if (normalizedCandidateSkill.equals(
                        normalizedRequiredSkill)) {

                    matched = true;
                    break;
                }
            }

            if (matched) {
                matchedRequiredSkills.add(requiredSkillName);
            } else {
                missingRequiredSkills.add(requiredSkillName);
            }
        }

        /*
         * Match preferred skills.
         */
        for (JobSkill jobSkill : preferredSkills) {

            String preferredSkillName =
                    jobSkill.getSkill().getName();

            String normalizedPreferredSkill =
                    SkillNormalizer.normalize(preferredSkillName);

            boolean matched = false;

            for (Skill candidateSkill : candidateSkills) {

                String candidateSkillName =
                        candidateSkill.getName();

                String normalizedCandidateSkill =
                        SkillNormalizer.normalize(candidateSkillName);

                if (normalizedCandidateSkill.equals(
                        normalizedPreferredSkill)) {

                    matched = true;
                    break;
                }
            }

            if (matched) {
                matchedPreferredSkills.add(preferredSkillName);
            } else {
                missingPreferredSkills.add(preferredSkillName);
            }
        }

        /*
         * Calculate candidate experience.
         */
        double candidateExperience =
                calculateTotalExperience(request.getProfileId());

        /*
         * Experience eligibility.
         */
        boolean experienceEligible = true;

        if (job.getExperienceType() == ExperienceType.REQUIRED) {

            experienceEligible =
                    candidateExperience >= job.getMinimumExperience();
        }

        /*
         * Final eligibility.
         */
        boolean eligible =
                missingRequiredSkills.isEmpty()
                && experienceEligible;

        /*
         * ATS score.
         *
         * Required skills = 70%
         * Preferred skills = 30%
         */
        int score;

        if (requiredSkills.isEmpty()
                && preferredSkills.isEmpty()) {

            score = 0;

        } else if (preferredSkills.isEmpty()) {

            score =
                    (matchedRequiredSkills.size() * 100)
                    / requiredSkills.size();

        } else if (requiredSkills.isEmpty()) {

            score =
                    (matchedPreferredSkills.size() * 100)
                    / preferredSkills.size();

        } else {

            int requiredScore =
                    (matchedRequiredSkills.size() * 70)
                    / requiredSkills.size();

            int preferredScore =
                    (matchedPreferredSkills.size() * 30)
                    / preferredSkills.size();

            score = requiredScore + preferredScore;
        }

        /*
         * Prepare Gemini suggestions.
         */
        ATSSuggestionRequest suggestionRequest =
                new ATSSuggestionRequest();

        suggestionRequest.setScore(score);

        suggestionRequest.setMatchedRequiredSkills(
                matchedRequiredSkills);

        suggestionRequest.setMissingRequiredSkills(
                missingRequiredSkills);

        suggestionRequest.setMatchedPreferredSkills(
                matchedPreferredSkills);

        suggestionRequest.setMissingPreferredSkills(
                missingPreferredSkills);

        String suggestions = "";

        /*
         * Gemini is only called when improvement suggestions
         * are actually required.
         */
        if (!missingRequiredSkills.isEmpty()
                || !missingPreferredSkills.isEmpty()) {

            suggestions =
                    aiService.generateATSSuggestions(
                            suggestionRequest);
        }

        return new ATSResponse(
                score,
                eligible,
                matchedRequiredSkills,
                missingRequiredSkills,
                matchedPreferredSkills,
                missingPreferredSkills,
                suggestions
        );
    }

    private double calculateTotalExperience(Long profileId) {

        List<Experience> experiences =
                experienceRepository.findByProfileId(profileId);

        double totalMonths = 0;

        for (Experience experience : experiences) {

            if (experience.getStartDate() == null
                    || experience.getEndDate() == null) {

                continue;
            }

            YearMonth startMonth =
                    YearMonth.parse(
                            experience.getStartDate());

            YearMonth endMonth =
                    YearMonth.parse(
                            experience.getEndDate());

            long months =
                    startMonth.until(
                            endMonth,
                            ChronoUnit.MONTHS);

            totalMonths += Math.max(months, 0);
        }

        return totalMonths / 12.0;
    }
}