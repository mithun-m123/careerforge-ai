package com.careerforge.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Education;

public interface EducationRepository
        extends JpaRepository<Education, Long> {
            List<Education> findByProfileId(Long profileId);
}