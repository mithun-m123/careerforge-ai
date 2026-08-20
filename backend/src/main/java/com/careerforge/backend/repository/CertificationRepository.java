package com.careerforge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Certification;

public interface CertificationRepository
        extends JpaRepository<Certification, Long> {

    List<Certification> findByProfileId(Long profileId);
}