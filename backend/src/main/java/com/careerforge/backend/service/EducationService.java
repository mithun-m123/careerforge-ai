package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.EducationRequest;
import com.careerforge.backend.dto.EducationResponse;
import com.careerforge.backend.entity.Education;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.EducationRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final StudentProfileRepository studentProfileRepository;

    public EducationService(
            EducationRepository educationRepository,
            StudentProfileRepository studentProfileRepository) {

        this.educationRepository = educationRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

public EducationResponse createEducation(EducationRequest request) {

    StudentProfile profile = studentProfileRepository.findById(request.getProfileId())
            .orElseThrow(() -> new RuntimeException("Student profile not found"));

    Education education = new Education();

    education.setDegree(request.getDegree());
    education.setCollege(request.getCollege());
    education.setBranch(request.getBranch());
    education.setStartYear(request.getStartYear());
    education.setGraduationYear(request.getGraduationYear());
    education.setCgpa(request.getCgpa());

    education.setProfile(profile);

    Education savedEducation = educationRepository.save(education);

    return new EducationResponse(
            savedEducation.getId(),
            savedEducation.getDegree(),
            savedEducation.getCollege(),
            savedEducation.getBranch(),
            savedEducation.getStartYear(),
            savedEducation.getGraduationYear(),
            savedEducation.getCgpa()
    );
}

public List<EducationResponse> getEducationByProfileId(Long profileId) {

    return educationRepository.findByProfileId(profileId)
            .stream()
            .map(education -> new EducationResponse(
                    education.getId(),
                    education.getDegree(),
                    education.getCollege(),
                    education.getBranch(),
                    education.getStartYear(),
                    education.getGraduationYear(),
                    education.getCgpa()
            ))
            .toList();
}

public EducationResponse updateEducation(
        Long educationId,
        EducationRequest request) {

    Education education = educationRepository.findById(educationId)
            .orElseThrow(() -> new RuntimeException("Education not found"));

    education.setDegree(request.getDegree());
    education.setCollege(request.getCollege());
    education.setBranch(request.getBranch());
    education.setStartYear(request.getStartYear());
    education.setGraduationYear(request.getGraduationYear());
    education.setCgpa(request.getCgpa());

    Education updatedEducation = educationRepository.save(education);

    return new EducationResponse(
            updatedEducation.getId(),
            updatedEducation.getDegree(),
            updatedEducation.getCollege(),
            updatedEducation.getBranch(),
            updatedEducation.getStartYear(),
            updatedEducation.getGraduationYear(),
            updatedEducation.getCgpa()
    );
}

public void deleteEducation(Long educationId) {

    if (!educationRepository.existsById(educationId)) {
        throw new RuntimeException("Education not found");
    }

    educationRepository.deleteById(educationId);
}
}