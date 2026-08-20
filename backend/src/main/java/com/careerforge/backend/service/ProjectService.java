package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ProjectRequest;
import com.careerforge.backend.dto.ProjectResponse;
import com.careerforge.backend.entity.Project;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.ProjectRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentProfileRepository studentProfileRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            StudentProfileRepository studentProfileRepository) {

        this.projectRepository = projectRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public ProjectResponse createProject(ProjectRequest request) {

        StudentProfile profile = studentProfileRepository
                .findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        Project project = new Project();

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies());
        project.setRole(request.getRole());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setProfile(profile);

        Project savedProject = projectRepository.save(project);

        return new ProjectResponse(
                savedProject.getId(),
                savedProject.getTitle(),
                savedProject.getDescription(),
                savedProject.getTechnologies(),
                savedProject.getRole(),
                savedProject.getGithubUrl(),
                savedProject.getLiveUrl()
        );
    }

    public List<ProjectResponse> getProjectsByProfileId(Long profileId) {

        return projectRepository.findByProfileId(profileId)
                .stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getTechnologies(),
                        project.getRole(),
                        project.getGithubUrl(),
                        project.getLiveUrl()
                ))
                .toList();
    }

    public ProjectResponse updateProject(
        Long projectId,
        ProjectRequest request) {

    Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

    project.setTitle(request.getTitle());
    project.setDescription(request.getDescription());
    project.setTechnologies(request.getTechnologies());
    project.setRole(request.getRole());
    project.setGithubUrl(request.getGithubUrl());
    project.setLiveUrl(request.getLiveUrl());

    Project updatedProject = projectRepository.save(project);

    return new ProjectResponse(
            updatedProject.getId(),
            updatedProject.getTitle(),
            updatedProject.getDescription(),
            updatedProject.getTechnologies(),
            updatedProject.getRole(),
            updatedProject.getGithubUrl(),
            updatedProject.getLiveUrl()
    );
}

public void deleteProject(Long projectId) {

    if (!projectRepository.existsById(projectId)) {
        throw new RuntimeException("Project not found");
    }

    projectRepository.deleteById(projectId);
}
}