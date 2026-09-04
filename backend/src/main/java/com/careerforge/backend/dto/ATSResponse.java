package com.careerforge.backend.dto;

import java.util.List;

public class ATSResponse {

    private int score;
    private String suggestions;
    private boolean eligible;

    private List<String> matchedRequiredSkills;
    private List<String> missingRequiredSkills;

    private List<String> matchedPreferredSkills;
    private List<String> missingPreferredSkills;

    public ATSResponse() {
    }

    public ATSResponse(
            int score,
            boolean eligible,
            List<String> matchedRequiredSkills,
            List<String> missingRequiredSkills,
            List<String> matchedPreferredSkills,
            List<String> missingPreferredSkills,
            String suggestions) {

        this.score = score;
        this.eligible = eligible;

        this.matchedRequiredSkills = matchedRequiredSkills;
        this.missingRequiredSkills = missingRequiredSkills;

        this.matchedPreferredSkills = matchedPreferredSkills;
        this.missingPreferredSkills = missingPreferredSkills;

        this.suggestions = suggestions;
    }

    public int getScore() {
        return score;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public boolean isEligible() {
        return eligible;
    }

    public List<String> getMatchedRequiredSkills() {
        return matchedRequiredSkills;
    }

    public List<String> getMissingRequiredSkills() {
        return missingRequiredSkills;
    }

    public List<String> getMatchedPreferredSkills() {
        return matchedPreferredSkills;
    }

    public List<String> getMissingPreferredSkills() {
        return missingPreferredSkills;
    }
}