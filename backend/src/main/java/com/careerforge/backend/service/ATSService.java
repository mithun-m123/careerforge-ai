package com.careerforge.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.dto.ATSSuggestionRequest;
import com.careerforge.backend.entity.Skill;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.util.SkillCatalog;

@Service
public class ATSService {

    private final SkillRepository skillRepository;
    private final AIService aiService;

    public ATSService(
            SkillRepository skillRepository,
            AIService aiService) {

        this.skillRepository = skillRepository;
        this.aiService = aiService;
    }

    public ATSResponse analyze(ATSRequest request) {

        List<Skill> resumeSkills =
                skillRepository.findByProfileId(request.getProfileId());

        String jobDescription =
                request.getJobDescription().toLowerCase();

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : SkillCatalog.SKILLS) {

            if (Pattern.compile(
                    "\\b" + Pattern.quote(skill) + "\\b",
                    Pattern.CASE_INSENSITIVE)
                    .matcher(jobDescription)
                    .find()) {

                boolean presentInResume = resumeSkills.stream()
                        .anyMatch(resumeSkill ->
                                resumeSkill.getName()
                                        .equalsIgnoreCase(skill));

                if (presentInResume) {
                    matchedSkills.add(skill);
                } else {
                    missingSkills.add(skill);
                }
            }
        }

        int totalSkills =
                matchedSkills.size() + missingSkills.size();

       int score = totalSkills == 0
        ? 0
        : (matchedSkills.size() * 100) / totalSkills;

ATSSuggestionRequest suggestionRequest =
        new ATSSuggestionRequest();

suggestionRequest.setScore(score);
suggestionRequest.setMatchedSkills(matchedSkills);
suggestionRequest.setMissingSkills(missingSkills);

String suggestions =
        aiService.generateATSSuggestions(suggestionRequest);

return new ATSResponse(
        score,
        totalSkills,
        matchedSkills,
        missingSkills,
        suggestions
);
    }
}