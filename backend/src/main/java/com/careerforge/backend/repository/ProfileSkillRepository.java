package com.careerforge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.ProfileSkill;

public interface ProfileSkillRepository
        extends JpaRepository<ProfileSkill, Long> {

    List<ProfileSkill> findByProfileId(Long profileId);
}