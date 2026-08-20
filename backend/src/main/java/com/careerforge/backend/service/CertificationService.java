package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.CertificationRequest;
import com.careerforge.backend.dto.CertificationResponse;
import com.careerforge.backend.entity.Certification;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.CertificationRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final StudentProfileRepository studentProfileRepository;

    public CertificationService(
            CertificationRepository certificationRepository,
            StudentProfileRepository studentProfileRepository) {

        this.certificationRepository = certificationRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public CertificationResponse createCertification(
            CertificationRequest request) {

        StudentProfile profile = studentProfileRepository
                .findById(request.getProfileId())
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        Certification certification = new Certification();

        certification.setName(request.getName());
        certification.setIssuer(request.getIssuer());
        certification.setIssueDate(request.getIssueDate());
        certification.setCredentialId(request.getCredentialId());
        certification.setCredentialUrl(request.getCredentialUrl());
        certification.setProfile(profile);

        Certification saved =
                certificationRepository.save(certification);

        return toResponse(saved);
    }

    public List<CertificationResponse> getCertificationsByProfileId(
            Long profileId) {

        return certificationRepository.findByProfileId(profileId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CertificationResponse updateCertification(
            Long certificationId,
            CertificationRequest request) {

        Certification certification =
                certificationRepository.findById(certificationId)
                        .orElseThrow(() ->
                                new RuntimeException("Certification not found"));

        certification.setName(request.getName());
        certification.setIssuer(request.getIssuer());
        certification.setIssueDate(request.getIssueDate());
        certification.setCredentialId(request.getCredentialId());
        certification.setCredentialUrl(request.getCredentialUrl());

        Certification updated =
                certificationRepository.save(certification);

        return toResponse(updated);
    }

    public void deleteCertification(Long certificationId) {

        if (!certificationRepository.existsById(certificationId)) {
            throw new RuntimeException("Certification not found");
        }

        certificationRepository.deleteById(certificationId);
    }

    private CertificationResponse toResponse(
            Certification certification) {

        return new CertificationResponse(
                certification.getId(),
                certification.getName(),
                certification.getIssuer(),
                certification.getIssueDate(),
                certification.getCredentialId(),
                certification.getCredentialUrl()
        );
    }
}