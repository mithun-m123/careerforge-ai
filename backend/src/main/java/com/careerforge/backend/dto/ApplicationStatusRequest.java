package com.careerforge.backend.dto;

import com.careerforge.backend.entity.ApplicationStatus;

public class ApplicationStatusRequest {

    private ApplicationStatus status;

    public ApplicationStatusRequest() {
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}