package com.careerforge.backend.dto;

public class CertificationResponse {

    private Long id;
    private String name;
    private String issuer;
    private String issueDate;
    private String credentialId;
    private String credentialUrl;

    public CertificationResponse() {
    }

    public CertificationResponse(
            Long id,
            String name,
            String issuer,
            String issueDate,
            String credentialId,
            String credentialUrl) {

        this.id = id;
        this.name = name;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.credentialId = credentialId;
        this.credentialUrl = credentialUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }
}