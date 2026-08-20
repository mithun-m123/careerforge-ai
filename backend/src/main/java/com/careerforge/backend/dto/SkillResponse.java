package com.careerforge.backend.dto;

public class SkillResponse {

    private Long id;
    private String name;
    private String level;

    public SkillResponse() {
    }

    public SkillResponse(Long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}