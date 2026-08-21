package com.careerforge.backend.service;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.CertificationResponse;
import com.careerforge.backend.dto.EducationResponse;
import com.careerforge.backend.dto.ExperienceResponse;
import com.careerforge.backend.dto.ProjectResponse;
import com.careerforge.backend.dto.ResumeResponse;
import com.careerforge.backend.dto.SkillResponse;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.CertificationRepository;
import com.careerforge.backend.repository.EducationRepository;
import com.careerforge.backend.repository.ExperienceRepository;
import com.careerforge.backend.repository.ProjectRepository;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class ResumeService {

    private final StudentProfileRepository studentProfileRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final ExperienceRepository experienceRepository;
    private final CertificationRepository certificationRepository;

    public ResumeService(
            StudentProfileRepository studentProfileRepository,
            EducationRepository educationRepository,
            SkillRepository skillRepository,
            ProjectRepository projectRepository,
            ExperienceRepository experienceRepository,
            CertificationRepository certificationRepository) {

        this.studentProfileRepository = studentProfileRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.experienceRepository = experienceRepository;
        this.certificationRepository = certificationRepository;
    }

    public ResumeResponse getResume(Long profileId) {

        StudentProfile profile = studentProfileRepository
                .findById(profileId)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        return new ResumeResponse(
                profile.getId(),
                profile.getUser().getName(),
                profile.getUser().getEmail(),
                profile.getCareerGoal(),
                profile.getResumeSummary(),

                educationRepository.findByProfileId(profileId)
                        .stream()
                        .map(education -> new EducationResponse(
                                education.getId(),
                                education.getDegree(),
                                education.getCollege(),
                                education.getBranch(),
                                education.getStartYear(),
                                education.getGraduationYear(),
                                education.getCgpa()
                        ))
                        .toList(),

                skillRepository.findByProfileId(profileId)
                        .stream()
                        .map(skill -> new SkillResponse(
                                skill.getId(),
                                skill.getName(),
                                skill.getLevel()
                        ))
                        .toList(),

                projectRepository.findByProfileId(profileId)
                        .stream()
                        .map(project -> new ProjectResponse(
                                project.getId(),
                                project.getTitle(),
                                project.getDescription(),
                                project.getTechnologies(),
                                project.getRole(),
                                project.getGithubUrl(),
                                project.getLiveUrl()
                        ))
                        .toList(),

                experienceRepository.findByProfileId(profileId)
                        .stream()
                        .map(experience -> new ExperienceResponse(
                                experience.getId(),
                                experience.getCompany(),
                                experience.getRole(),
                                experience.getType(),
                                experience.getStartDate(),
                                experience.getEndDate(),
                                experience.getDescription()
                        ))
                        .toList(),

                certificationRepository.findByProfileId(profileId)
                        .stream()
                        .map(certification -> new CertificationResponse(
                                certification.getId(),
                                certification.getName(),
                                certification.getIssuer(),
                                certification.getIssueDate(),
                                certification.getCredentialId(),
                                certification.getCredentialUrl()
                        ))
                        .toList()
        );
    }
}