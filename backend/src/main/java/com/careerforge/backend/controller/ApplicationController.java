package com.careerforge.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.ApplicationRequest;
import com.careerforge.backend.dto.ApplicationResponse;
import com.careerforge.backend.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> apply(
            @RequestBody ApplicationRequest request) {

        ApplicationResponse response =
                applicationService.apply(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/job/{jobId}/ranked")
public ResponseEntity<List<ApplicationResponse>> getRankedApplications(
        @PathVariable Long jobId) {

    return ResponseEntity.ok(
            applicationService.getRankedApplications(jobId)
    );
}
}