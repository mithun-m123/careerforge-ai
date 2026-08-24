package com.careerforge.backend.dto;

import java.util.List;

public class ATSResponse {

    private int score;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private int totalSkills;
    private String suggestions;

    public ATSResponse() {
    }


    public ATSResponse(
        int score,
        int totalSkills,
        List<String> matchedSkills,
        List<String> missingSkills) {

    this.score = score;
    this.totalSkills = totalSkills;
    this.matchedSkills = matchedSkills;
    this.missingSkills = missingSkills;
}
public ATSResponse(
        int score,
        int totalSkills,
        List<String> matchedSkills,
        List<String> missingSkills,
        String suggestions) {

    this.score = score;
    this.totalSkills = totalSkills;
    this.matchedSkills = matchedSkills;
    this.missingSkills = missingSkills;
    this.suggestions = suggestions;
}

    public int getScore() {
        return score;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }
    public int getTotalSkills() {
    return totalSkills;
}
public String getSuggestions() {
    return suggestions;
}
}