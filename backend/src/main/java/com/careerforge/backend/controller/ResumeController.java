package com.careerforge.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.ResumeResponse;
import com.careerforge.backend.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<ResumeResponse> getResume(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                resumeService.getResume(profileId)
        );
    }
}