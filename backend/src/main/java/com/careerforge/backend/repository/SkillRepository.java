package com.careerforge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByProfileId(Long profileId);
}