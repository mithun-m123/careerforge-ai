package com.careerforge.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.ResumeSummaryRequest;
import com.careerforge.backend.dto.StudentProfileRequest;
import com.careerforge.backend.dto.StudentProfileResponse;
import com.careerforge.backend.dto.StudentProfileUpdateRequest;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.service.StudentProfileService;

@RestController
@RequestMapping("/api/profiles")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

@PostMapping
public ResponseEntity<StudentProfileResponse> createProfile(
        @RequestBody StudentProfileRequest request) {

    StudentProfileResponse profile =
            studentProfileService.createProfile(request);

    return ResponseEntity.ok(profile);
}
@GetMapping("/{userId}")
public ResponseEntity<StudentProfileResponse> getProfile(
        @PathVariable Long userId) {

    return studentProfileService.getProfileByUserId(userId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
@PutMapping("/{userId}")
public ResponseEntity<StudentProfileResponse> updateProfile(
        @PathVariable Long userId,
        @RequestBody StudentProfileUpdateRequest request) {

    StudentProfileResponse profile =
            studentProfileService.updateProfile(userId, request);

    return ResponseEntity.ok(profile);
}

@PutMapping("/{profileId}/resume-summary")
public ResponseEntity<StudentProfile> updateResumeSummary(
        @PathVariable Long profileId,
        @RequestBody ResumeSummaryRequest request) {

    return ResponseEntity.ok(
            studentProfileService.updateResumeSummary(
                    profileId,
                    request.getResumeSummary()
            )
    );
}
}