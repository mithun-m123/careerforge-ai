package com.careerforge.backend.dto;

import java.util.List;

public class ATSSuggestionRequest {

    private int score;

    private List<String> matchedRequiredSkills;
    private List<String> missingRequiredSkills;

    private List<String> matchedPreferredSkills;
    private List<String> missingPreferredSkills;

    public ATSSuggestionRequest() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
}