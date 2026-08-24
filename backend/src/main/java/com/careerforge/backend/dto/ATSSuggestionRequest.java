package com.careerforge.backend.dto;

import java.util.List;

public class ATSSuggestionRequest {

    private int score;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public ATSSuggestionRequest() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}