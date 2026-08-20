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

import com.careerforge.backend.dto.ProjectRequest;
import com.careerforge.backend.dto.ProjectResponse;
import com.careerforge.backend.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @RequestBody ProjectRequest request) {

        return ResponseEntity.ok(
                projectService.createProject(request)
        );
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ProjectResponse>> getProjects(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                projectService.getProjectsByProfileId(profileId)
        );
    }

    @PutMapping("/{projectId}")
public ResponseEntity<ProjectResponse> updateProject(
        @PathVariable Long projectId,
        @RequestBody ProjectRequest request) {

    return ResponseEntity.ok(
            projectService.updateProject(projectId, request)
    );
}

@DeleteMapping("/{projectId}")
public ResponseEntity<Void> deleteProject(
        @PathVariable Long projectId) {

    projectService.deleteProject(projectId);

    return ResponseEntity.noContent().build();
}
}