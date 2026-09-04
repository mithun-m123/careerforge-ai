package com.careerforge.backend.service;

import java.time.YearMonth;
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

        // 1. Get the job
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Job not found: " + request.getJobId()
                        ));

        // 2. Get candidate skills
        List<Skill> resumeSkills =
                skillRepository.findByProfileId(request.getProfileId());

        // 3. Get job skills
        List<JobSkill> jobSkills = job.getJobSkills();

        List<JobSkill> requiredSkills = new ArrayList<>();
        List<JobSkill> preferredSkills = new ArrayList<>();

        List<String> matchedRequiredSkills = new ArrayList<>();
        List<String> missingRequiredSkills = new ArrayList<>();

        List<String> matchedPreferredSkills = new ArrayList<>();
        List<String> missingPreferredSkills = new ArrayList<>();

        // 4. Separate required and preferred skills
        for (JobSkill jobSkill : jobSkills) {

            if (jobSkill.getType() == SkillRequirementType.REQUIRED) {

                requiredSkills.add(jobSkill);

            } else if (jobSkill.getType() == SkillRequirementType.PREFERRED) {

                preferredSkills.add(jobSkill);
            }
        }

        // 5. Match required skills
        for (JobSkill jobSkill : requiredSkills) {

          String requiredSkill =
        jobSkill.getSkill().getName();

String normalizedRequiredSkill =
        SkillNormalizer.normalize(requiredSkill);

boolean presentInResume =
        resumeSkills.stream()
                .anyMatch(resumeSkill ->
                        SkillNormalizer.normalize(
                                resumeSkill.getName()
                        ).equals(normalizedRequiredSkill));

            if (presentInResume) {

                matchedRequiredSkills.add(requiredSkill);

            } else {

                missingRequiredSkills.add(requiredSkill);
            }
        }

        // 6. Match preferred skills
        for (JobSkill jobSkill : preferredSkills) {

            String preferredSkill =
        jobSkill.getSkill().getName();

String normalizedPreferredSkill =
        SkillNormalizer.normalize(preferredSkill);

boolean presentInResume =
        resumeSkills.stream()
                .anyMatch(resumeSkill ->
                        SkillNormalizer.normalize(
                                resumeSkill.getName()
                        ).equals(normalizedPreferredSkill));

            if (presentInResume) {

                matchedPreferredSkills.add(preferredSkill);

            } else {

                missingPreferredSkills.add(preferredSkill);
            }
        }

        // 7. Calculate candidate experience
        double candidateExperience =
                calculateTotalExperience(request.getProfileId());

        // 8. Check experience eligibility
        boolean experienceEligible = true;

        if (job.getExperienceType() == ExperienceType.REQUIRED) {

            experienceEligible =
                    candidateExperience >= job.getMinimumExperience();
        }

        // 9. Final eligibility
        boolean eligible =
                missingRequiredSkills.isEmpty()
                && experienceEligible;

        // 10. Calculate ATS score
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

        // 11. Prepare Gemini request
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

        // 12. Call Gemini only when there are missing skills
        String suggestions = "";

        if (!missingRequiredSkills.isEmpty()
                || !missingPreferredSkills.isEmpty()) {

            suggestions =
                    aiService.generateATSSuggestions(
                            suggestionRequest);
        }

        // 13. Return ATS response
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

    // Calculate total candidate experience in years
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
                            java.time.temporal.ChronoUnit.MONTHS);

            totalMonths += Math.max(months, 0);
        }

        return totalMonths / 12.0;
    }
}