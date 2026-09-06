package com.careerforge.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ATSResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    private Integer score;

    private Boolean eligible;

    @ElementCollection
    @CollectionTable(
            name = "ats_matched_required_skills",
            joinColumns = @JoinColumn(name = "ats_result_id")
    )
    private List<String> matchedRequiredSkills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "ats_missing_required_skills",
            joinColumns = @JoinColumn(name = "ats_result_id")
    )
    private List<String> missingRequiredSkills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "ats_matched_preferred_skills",
            joinColumns = @JoinColumn(name = "ats_result_id")
    )
    private List<String> matchedPreferredSkills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "ats_missing_preferred_skills",
            joinColumns = @JoinColumn(name = "ats_result_id")
    )
    private List<String> missingPreferredSkills = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String studentSuggestions;

    private String recruiterExplanation;

    public ATSResult() {
    }

    public Long getId() {
        return id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public List<String> getMatchedRequiredSkills() {
        return matchedRequiredSkills;
    }

    public void setMatchedRequiredSkills(
            List<String> matchedRequiredSkills) {
        this.matchedRequiredSkills = matchedRequiredSkills;
    }

    public List<String> getMissingRequiredSkills() {
        return missingRequiredSkills;
    }

    public void setMissingRequiredSkills(
            List<String> missingRequiredSkills) {
        this.missingRequiredSkills = missingRequiredSkills;
    }

    public List<String> getMatchedPreferredSkills() {
        return matchedPreferredSkills;
    }

    public void setMatchedPreferredSkills(
            List<String> matchedPreferredSkills) {
        this.matchedPreferredSkills = matchedPreferredSkills;
    }

    public List<String> getMissingPreferredSkills() {
        return missingPreferredSkills;
    }

    public void setMissingPreferredSkills(
            List<String> missingPreferredSkills) {
        this.missingPreferredSkills = missingPreferredSkills;
    }

    public String getStudentSuggestions() {
        return studentSuggestions;
    }

    public void setStudentSuggestions(String studentSuggestions) {
        this.studentSuggestions = studentSuggestions;
    }

    public String getRecruiterExplanation() {
        return recruiterExplanation;
    }

    public void setRecruiterExplanation(String recruiterExplanation) {
        this.recruiterExplanation = recruiterExplanation;
    }
}