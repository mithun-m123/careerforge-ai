package com.careerforge.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.CertificationRequest;
import com.careerforge.backend.dto.CertificationResponse;
import com.careerforge.backend.service.CertificationService;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(
            CertificationService certificationService) {

        this.certificationService = certificationService;
    }

    @PostMapping
    public ResponseEntity<CertificationResponse> createCertification(
            @RequestBody CertificationRequest request) {

        return ResponseEntity.ok(
                certificationService.createCertification(request)
        );
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<CertificationResponse>> getCertifications(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                certificationService
                        .getCertificationsByProfileId(profileId)
        );
    }

    @PutMapping("/{certificationId}")
    public ResponseEntity<CertificationResponse> updateCertification(
            @PathVariable Long certificationId,
            @RequestBody CertificationRequest request) {

        return ResponseEntity.ok(
                certificationService.updateCertification(
                        certificationId,
                        request
                )
        );
    }

    @DeleteMapping("/{certificationId}")
    public ResponseEntity<Void> deleteCertification(
            @PathVariable Long certificationId) {

        certificationService.deleteCertification(certificationId);

        return ResponseEntity.noContent().build();
    }
}