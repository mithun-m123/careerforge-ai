package com.careerforge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}