package com.careerforge.backend.dto;

import com.careerforge.backend.entity.SkillRequirementType;

public class JobSkillRequest {

    private Long skillId;

    private SkillRequirementType type;

    public JobSkillRequest() {
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public SkillRequirementType getType() {
        return type;
    }

    public void setType(SkillRequirementType type) {
        this.type = type;
    }
}