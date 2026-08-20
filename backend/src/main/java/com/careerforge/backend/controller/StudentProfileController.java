package com.careerforge.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.StudentProfileRequest;
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
    public ResponseEntity<StudentProfile> createProfile(
            @RequestBody StudentProfileRequest request) {

        StudentProfile profile =
                studentProfileService.createProfile(request);

        return ResponseEntity.ok(profile);
    }
}