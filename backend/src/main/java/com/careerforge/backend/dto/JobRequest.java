package com.careerforge.backend.dto;

import com.careerforge.backend.entity.ExperienceType;
import com.careerforge.backend.entity.SeniorityLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private ExperienceType experienceType;

    private Integer minimumExperience;

    @NotNull
    private SeniorityLevel seniority;

    public JobRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public Integer getMinimumExperience() {
        return minimumExperience;
    }

    public void setMinimumExperience(Integer minimumExperience) {
        this.minimumExperience = minimumExperience;
    }

    public SeniorityLevel getSeniority() {
        return seniority;
    }

    public void setSeniority(SeniorityLevel seniority) {
        this.seniority = seniority;
    }
}