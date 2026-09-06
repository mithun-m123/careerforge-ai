package com.careerforge.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class StudentApplicationResponse {

    private Long id;
    private Long profileId;
    private Long jobId;
    private String status;
    private LocalDateTime appliedAt;

    private int atsScore;
    private boolean eligible;

    private List<String> matchedRequiredSkills;
    private List<String> missingRequiredSkills;
    private List<String> matchedPreferredSkills;
    private List<String> missingPreferredSkills;

    private String studentSuggestions;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public List<String> getMatchedRequiredSkills() {
        return matchedRequiredSkills;
    }

    public void setMatchedRequiredSkills(List<String> matchedRequiredSkills) {
        this.matchedRequiredSkills = matchedRequiredSkills;
    }

    public List<String> getMissingRequiredSkills() {
        return missingRequiredSkills;
    }

    public void setMissingRequiredSkills(List<String> missingRequiredSkills) {
        this.missingRequiredSkills = missingRequiredSkills;
    }

    public List<String> getMatchedPreferredSkills() {
        return matchedPreferredSkills;
    }

    public void setMatchedPreferredSkills(List<String> matchedPreferredSkills) {
        this.matchedPreferredSkills = matchedPreferredSkills;
    }

    public List<String> getMissingPreferredSkills() {
        return missingPreferredSkills;
    }

    public void setMissingPreferredSkills(List<String> missingPreferredSkills) {
        this.missingPreferredSkills = missingPreferredSkills;
    }

    public String getStudentSuggestions() {
        return studentSuggestions;
    }

    public void setStudentSuggestions(String studentSuggestions) {
        this.studentSuggestions = studentSuggestions;
    }
}