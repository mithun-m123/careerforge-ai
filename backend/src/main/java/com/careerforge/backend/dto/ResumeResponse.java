package com.careerforge.backend.dto;

import java.util.List;

public class ResumeResponse {

    private Long profileId;
    private String name;
    private String email;
    private String careerGoal;
    private String resumeSummary;

    private List<EducationResponse> education;
    private List<SkillResponse> skills;
    private List<ProjectResponse> projects;
    private List<ExperienceResponse> experience;
    private List<CertificationResponse> certifications;

    public ResumeResponse() {
    }

    public ResumeResponse(
            Long profileId,
            String name,
            String email,
            String careerGoal,
            String resumeSummary,
            List<EducationResponse> education,
            List<SkillResponse> skills,
            List<ProjectResponse> projects,
            List<ExperienceResponse> experience,
            List<CertificationResponse> certifications) {

        this.profileId = profileId;
        this.name = name;
        this.email = email;
        this.careerGoal = careerGoal;
        this.education = education;
        this.resumeSummary = resumeSummary;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;
        this.certifications = certifications;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public List<EducationResponse> getEducation() {
        return education;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public List<ExperienceResponse> getExperience() {
        return experience;
    }

    public List<CertificationResponse> getCertifications() {
        return certifications;
    }

    public String getResumeSummary() {
    return resumeSummary;
}
}