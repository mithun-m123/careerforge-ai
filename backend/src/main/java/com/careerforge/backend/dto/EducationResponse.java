package com.careerforge.backend.dto;

public class EducationResponse {

    private Long id;
    private String degree;
    private String college;
    private String branch;
    private Integer startYear;
    private Integer graduationYear;
    private Double cgpa;

    public EducationResponse() {
    }

    public EducationResponse(
            Long id,
            String degree,
            String college,
            String branch,
            Integer startYear,
            Integer graduationYear,
            Double cgpa) {

        this.id = id;
        this.degree = degree;
        this.college = college;
        this.branch = branch;
        this.startYear = startYear;
        this.graduationYear = graduationYear;
        this.cgpa = cgpa;
    }

    public Long getId() {
        return id;
    }

    public String getDegree() {
        return degree;
    }

    public String getCollege() {
        return college;
    }

    public String getBranch() {
        return branch;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public Double getCgpa() {
        return cgpa;
    }
}