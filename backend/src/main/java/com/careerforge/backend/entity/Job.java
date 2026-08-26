package com.careerforge.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Job {
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
private List<JobSkill> jobSkills = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExperienceType experienceType;

    private Integer minimumExperience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeniorityLevel seniority;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }public List<JobSkill> getJobSkills() {
    return jobSkills;
}

public void setJobSkills(List<JobSkill> jobSkills) {
    this.jobSkills = jobSkills;
}
}