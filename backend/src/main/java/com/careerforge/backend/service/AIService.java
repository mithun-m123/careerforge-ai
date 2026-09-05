package com.careerforge.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ATSSuggestionRequest;
import com.careerforge.backend.entity.ProfileSkill;
import com.careerforge.backend.repository.ProfileSkillRepository;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class AIService {

    private final ProfileSkillRepository profileSkillRepository;

    private final Client client;

    @Value("${google.genai.model:gemini-2.5-flash}")
    private String model;

    public AIService(
            ProfileSkillRepository profileSkillRepository) {

        this.profileSkillRepository = profileSkillRepository;

        this.client = Client.builder()
                .build();
    }

    public String generateResumeSummary(Long profileId) {

        String skills = getSkills(profileId);

        String prompt = """
                Generate a professional resume summary for a student.

                Student Profile ID: %d

                Skills:
                %s

                Keep the summary concise, professional, and suitable
                for a software engineering resume.

                Do not invent qualifications, experience, projects,
                certifications, or achievements that were not provided.
                """.formatted(profileId, skills);

        GenerateContentResponse response =
                client.models.generateContent(
                        model,
                        prompt,
                        null
                );

        return response.text();
    }

    public String generateATSSuggestions(
            ATSSuggestionRequest request) {

        String prompt = """
                You are an ATS career assistant for CareerForge.

                ATS Score: %d

                Matched Required Skills:
                %s

                Missing Required Skills:
                %s

                Matched Preferred Skills:
                %s

                Missing Preferred Skills:
                %s

                Provide concise, actionable suggestions to improve
                the candidate's match for this job.

                Rules:
                - Do not invent qualifications.
                - Do not change the ATS score.
                - Do not claim that a missing skill is present.
                - Clearly distinguish required skills from preferred skills.
                - Focus on realistic improvement suggestions.
                """.formatted(
                request.getScore(),
                request.getMatchedRequiredSkills(),
                request.getMissingRequiredSkills(),
                request.getMatchedPreferredSkills(),
                request.getMissingPreferredSkills()
        );

        GenerateContentResponse response =
                client.models.generateContent(
                        model,
                        prompt,
                        null
                );

        return response.text();
    }

    private String getSkills(Long profileId) {

        List<ProfileSkill> profileSkills =
                profileSkillRepository.findByProfileId(profileId);

        return profileSkills.stream()
                .map(profileSkill ->
                        profileSkill.getSkill().getName()
                                + " ("
                                + profileSkill.getLevel()
                                + ")")
                .toList()
                .toString();
    }
}