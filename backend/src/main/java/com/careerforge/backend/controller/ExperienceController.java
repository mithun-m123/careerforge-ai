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

import com.careerforge.backend.dto.ExperienceRequest;
import com.careerforge.backend.dto.ExperienceResponse;
import com.careerforge.backend.service.ExperienceService;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(
            ExperienceService experienceService) {

        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<ExperienceResponse> createExperience(
            @RequestBody ExperienceRequest request) {

        return ResponseEntity.ok(
                experienceService.createExperience(request)
        );
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ExperienceResponse>> getExperiences(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                experienceService
                        .getExperiencesByProfileId(profileId)
        );
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity<ExperienceResponse> updateExperience(
            @PathVariable Long experienceId,
            @RequestBody ExperienceRequest request) {

        return ResponseEntity.ok(
                experienceService
                        .updateExperience(experienceId, request)
        );
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<Void> deleteExperience(
            @PathVariable Long experienceId) {

        experienceService.deleteExperience(experienceId);

        return ResponseEntity.noContent().build();
    }
}