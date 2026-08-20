package com.careerforge.backend.dto;

public class ExperienceResponse {

    private Long id;
    private String company;
    private String role;
    private String type;
    private String startDate;
    private String endDate;
    private String description;

    public ExperienceResponse() {
    }

    public ExperienceResponse(
            Long id,
            String company,
            String role,
            String type,
            String startDate,
            String endDate,
            String description) {

        this.id = id;
        this.company = company;
        this.role = role;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }
}