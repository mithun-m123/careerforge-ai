package com.careerforge.backend.dto;

public class StudentProfileResponse {

    private Long id;
    private Long userId;
    private String careerGoal;

    public StudentProfileResponse() {
    }

    public StudentProfileResponse(Long id, Long userId, String careerGoal) {
        this.id = id;
        this.userId = userId;
        this.careerGoal = careerGoal;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCareerGoal() {
        return careerGoal;
    }
}