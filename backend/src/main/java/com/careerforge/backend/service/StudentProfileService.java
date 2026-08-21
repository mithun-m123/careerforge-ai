package com.careerforge.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.StudentProfileRequest;
import com.careerforge.backend.dto.StudentProfileResponse;
import com.careerforge.backend.dto.StudentProfileUpdateRequest;
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

public StudentProfileResponse createProfile(StudentProfileRequest request) {

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

    StudentProfile profile = new StudentProfile();

    profile.setUser(user);
    profile.setCareerGoal(request.getCareerGoal());

    StudentProfile savedProfile = studentProfileRepository.save(profile);

    return new StudentProfileResponse(
            savedProfile.getId(),
            savedProfile.getUser().getId(),
            savedProfile.getCareerGoal()
    );
}
public Optional<StudentProfileResponse> getProfileByUserId(Long userId) {

    return studentProfileRepository.findByUserId(userId)
            .map(profile -> new StudentProfileResponse(
                    profile.getId(),
                    profile.getUser().getId(),
                    profile.getCareerGoal()
            ));
}
public StudentProfileResponse updateProfile(
        Long userId,
        StudentProfileUpdateRequest request) {

    StudentProfile profile = studentProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Profile not found"));

    profile.setCareerGoal(request.getCareerGoal());

    StudentProfile updatedProfile =
            studentProfileRepository.save(profile);

    return new StudentProfileResponse(
            updatedProfile.getId(),
            updatedProfile.getUser().getId(),
            updatedProfile.getCareerGoal()
    );
}

public StudentProfile updateResumeSummary(
        Long profileId,
        String resumeSummary) {

    StudentProfile profile = studentProfileRepository
            .findById(profileId)
            .orElseThrow(() ->
                    new RuntimeException("Student profile not found"));

    profile.setResumeSummary(resumeSummary);

    return studentProfileRepository.save(profile);
}
}  
