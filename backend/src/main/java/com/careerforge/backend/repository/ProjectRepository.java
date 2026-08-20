package com.careerforge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByProfileId(Long profileId);
}