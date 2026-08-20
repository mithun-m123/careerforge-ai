package com.careerforge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.StudentProfile;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {
}