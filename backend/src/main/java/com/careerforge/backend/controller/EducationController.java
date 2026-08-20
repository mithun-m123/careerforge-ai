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

import com.careerforge.backend.dto.EducationRequest;
import com.careerforge.backend.dto.EducationResponse;
import com.careerforge.backend.service.EducationService;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public ResponseEntity<EducationResponse> createEducation(
            @RequestBody EducationRequest request) {

        EducationResponse education =
        educationService.createEducation(request);
        return ResponseEntity.ok(education);
    }

    @GetMapping("/profile/{profileId}")
public ResponseEntity<List<EducationResponse>> getEducation(
        @PathVariable Long profileId) {

    List<EducationResponse> education =
            educationService.getEducationByProfileId(profileId);

    return ResponseEntity.ok(education);
}

@PutMapping("/{educationId}")
public ResponseEntity<EducationResponse> updateEducation(
        @PathVariable Long educationId,
        @RequestBody EducationRequest request) {

    EducationResponse education =
            educationService.updateEducation(educationId, request);

    return ResponseEntity.ok(education);
}

@DeleteMapping("/{educationId}")
public ResponseEntity<Void> deleteEducation(
        @PathVariable Long educationId) {

    educationService.deleteEducation(educationId);

    return ResponseEntity.noContent().build();
}
}