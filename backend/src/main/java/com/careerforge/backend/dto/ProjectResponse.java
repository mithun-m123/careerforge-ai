package com.careerforge.backend.dto;

public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String role;
    private String githubUrl;
    private String liveUrl;

    public ProjectResponse() {
    }

    public ProjectResponse(
            Long id,
            String title,
            String description,
            String technologies,
            String role,
            String githubUrl,
            String liveUrl) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.role = role;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getRole() {
        return role;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getLiveUrl() {
        return liveUrl;
    }
}