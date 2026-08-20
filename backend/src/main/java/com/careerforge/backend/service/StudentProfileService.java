package com.careerforge.backend.service;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.StudentProfileRequest;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.entity.User;
import com.careerforge.backend.exception.UserNotFoundException;
import com.careerforge.backend.repository.StudentProfileRepository;
import com.careerforge.backend.repository.UserRepository;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentProfileService(
            StudentProfileRepository studentProfileRepository,
            UserRepository userRepository) {

        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    public StudentProfile createProfile(StudentProfileRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        StudentProfile profile = new StudentProfile();

        profile.setUser(user);
        profile.setCareerGoal(request.getCareerGoal());

        return studentProfileRepository.save(profile);
    }
}