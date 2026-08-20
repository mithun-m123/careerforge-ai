package com.careerforge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Experience;

public interface ExperienceRepository
        extends JpaRepository<Experience, Long> {

    List<Experience> findByProfileId(Long profileId);
}