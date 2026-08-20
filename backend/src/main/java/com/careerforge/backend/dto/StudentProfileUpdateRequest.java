package com.careerforge.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class StudentProfileUpdateRequest {

    @NotBlank
    private String careerGoal;

    public StudentProfileUpdateRequest() {
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public void setCareerGoal(String careerGoal) {
        this.careerGoal = careerGoal;
    }
}