package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ExperienceRequest;
import com.careerforge.backend.dto.ExperienceResponse;
import com.careerforge.backend.entity.Experience;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.ExperienceRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final StudentProfileRepository studentProfileRepository;

    public ExperienceService(
            ExperienceRepository experienceRepository,
            StudentProfileRepository studentProfileRepository) {

        this.experienceRepository = experienceRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public ExperienceResponse createExperience(
            ExperienceRequest request) {

        StudentProfile profile = studentProfileRepository
                .findById(request.getProfileId())
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        Experience experience = new Experience();

        experience.setCompany(request.getCompany());
        experience.setRole(request.getRole());
        experience.setType(request.getType());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setDescription(request.getDescription());
        experience.setProfile(profile);

        Experience savedExperience =
                experienceRepository.save(experience);

        return toResponse(savedExperience);
    }

    public List<ExperienceResponse> getExperiencesByProfileId(
            Long profileId) {

        return experienceRepository.findByProfileId(profileId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExperienceResponse updateExperience(
            Long experienceId,
            ExperienceRequest request) {

        Experience experience = experienceRepository
                .findById(experienceId)
                .orElseThrow(() ->
                        new RuntimeException("Experience not found"));

        experience.setCompany(request.getCompany());
        experience.setRole(request.getRole());
        experience.setType(request.getType());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setDescription(request.getDescription());

        Experience updatedExperience =
                experienceRepository.save(experience);

        return toResponse(updatedExperience);
    }

    public void deleteExperience(Long experienceId) {

        if (!experienceRepository.existsById(experienceId)) {
            throw new RuntimeException("Experience not found");
        }

        experienceRepository.deleteById(experienceId);
    }

    private ExperienceResponse toResponse(Experience experience) {

        return new ExperienceResponse(
                experience.getId(),
                experience.getCompany(),
                experience.getRole(),
                experience.getType(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription()
        );
    }
}