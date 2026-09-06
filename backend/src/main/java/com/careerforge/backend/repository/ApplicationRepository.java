package com.careerforge.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Application;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

            List<Application> findByJobIdOrderByAtsResultScoreDesc(Long jobId);
            Optional<Application> findById(Long id);

    Optional<Application> findByProfileIdAndJobId(
            Long profileId,
            Long jobId
    );
}