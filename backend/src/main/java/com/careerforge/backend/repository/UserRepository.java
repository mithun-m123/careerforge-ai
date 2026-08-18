package com.careerforge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}