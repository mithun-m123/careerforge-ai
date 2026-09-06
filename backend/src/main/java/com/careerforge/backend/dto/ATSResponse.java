package com.careerforge.backend.dto;

import java.util.List;

public class ATSResponse {

    private int score;
    private boolean eligible;

    private List<String> matchedRequiredSkills;
    private List<String> missingRequiredSkills;

    private List<String> matchedPreferredSkills;
    private List<String> missingPreferredSkills;

    private String studentSuggestions;
    private String recruiterExplanation;

    public ATSResponse() {
    }

    public ATSResponse(
            int score,
            boolean eligible,
            List<String> matchedRequiredSkills,
            List<String> missingRequiredSkills,
            List<String> matchedPreferredSkills,
            List<String> missingPreferredSkills,
            String studentSuggestions,
            String recruiterExplanation) {

        this.score = score;
        this.eligible = eligible;
        this.matchedRequiredSkills = matchedRequiredSkills;
        this.missingRequiredSkills = missingRequiredSkills;
        this.matchedPreferredSkills = matchedPreferredSkills;
        this.missingPreferredSkills = missingPreferredSkills;
        this.studentSuggestions = studentSuggestions;
        this.recruiterExplanation = recruiterExplanation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public void setStudentSuggestions(
            String studentSuggestions) {
        this.studentSuggestions = studentSuggestions;
    }

    public String getRecruiterExplanation() {
        return recruiterExplanation;
    }

    public void setRecruiterExplanation(
            String recruiterExplanation) {
        this.recruiterExplanation = recruiterExplanation;
    }
}