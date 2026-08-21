package com.careerforge.backend.service;

import org.springframework.stereotype.Service;

import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.EducationRepository;
import com.careerforge.backend.repository.ExperienceRepository;
import com.careerforge.backend.repository.ProjectRepository;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.repository.StudentProfileRepository;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class AIService {

    private final Client client;
    private final StudentProfileRepository studentProfileRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final ExperienceRepository experienceRepository;

    public AIService(
            StudentProfileRepository studentProfileRepository,
            EducationRepository educationRepository,
            SkillRepository skillRepository,
            ProjectRepository projectRepository,
            ExperienceRepository experienceRepository) {

        this.studentProfileRepository = studentProfileRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.experienceRepository = experienceRepository;

        this.client = new Client();
    }

    public String generateResumeSummary(Long profileId) {

        StudentProfile profile = studentProfileRepository
                .findById(profileId)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        String prompt = """
                You are CareerForge AI, a professional career assistant.

                Generate a concise and professional resume summary
                for the student based ONLY on the information provided.

                Do not invent skills, experience, projects,
                certifications, achievements, or qualifications.

                Career Goal:
                %s

                Education:
                %s

                Skills:
                %s

                Projects:
                %s

                Experience:
                %s

                Return only the final resume summary.
                """
                .formatted(
                        profile.getCareerGoal(),
                        getEducation(profileId),
                        getSkills(profileId),
                        getProjects(profileId),
                        getExperience(profileId)
                );

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.5-flash-lite",
                        prompt,
                        null
                );

        return response.text();
    }

    private String getEducation(Long profileId) {

        return educationRepository.findByProfileId(profileId)
                .stream()
                .map(education ->
                        education.getDegree() + " - " +
                        education.getCollege() + " - " +
                        education.getBranch() +
                        " - CGPA: " +
                        education.getCgpa())
                .toList()
                .toString();
    }

    private String getSkills(Long profileId) {

        return skillRepository.findByProfileId(profileId)
                .stream()
                .map(skill ->
                        skill.getName() + " (" +
                        skill.getLevel() + ")")
                .toList()
                .toString();
    }

    private String getProjects(Long profileId) {

        return projectRepository.findByProfileId(profileId)
                .stream()
                .map(project ->
                        project.getTitle() + ": " +
                        project.getDescription() +
                        " | Technologies: " +
                        project.getTechnologies())
                .toList()
                .toString();
    }

    private String getExperience(Long profileId) {

        return experienceRepository.findByProfileId(profileId)
                .stream()
                .map(experience ->
                        experience.getRole() + " at " +
                        experience.getCompany() + ": " +
                        experience.getDescription())
                .toList()
                .toString();
    }
}