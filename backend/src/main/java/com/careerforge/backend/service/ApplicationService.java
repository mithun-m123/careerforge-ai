package com.careerforge.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.dto.ApplicationRequest;
import com.careerforge.backend.dto.ApplicationStatusRequest;
import com.careerforge.backend.dto.RecruiterApplicationResponse;
import com.careerforge.backend.dto.StudentApplicationResponse;
import com.careerforge.backend.entity.ATSResult;
import com.careerforge.backend.entity.Application;
import com.careerforge.backend.entity.ApplicationStatus;
import com.careerforge.backend.entity.Job;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.ApplicationRepository;
import com.careerforge.backend.repository.JobRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentProfileRepository profileRepository;
    private final JobRepository jobRepository;
    private final ATSService atsService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentProfileRepository profileRepository,
            JobRepository jobRepository,
            ATSService atsService) {

        this.applicationRepository = applicationRepository;
        this.profileRepository = profileRepository;
        this.jobRepository = jobRepository;
        this.atsService = atsService;
    }

    /*
     * Recruiter:
     * Update the status of an application.
     */
    public RecruiterApplicationResponse updateStatus(
            Long applicationId,
            ApplicationStatusRequest request) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Application not found: "
                                                + applicationId
                                ));

        application.setStatus(request.getStatus());

        Application savedApplication =
                applicationRepository.save(application);

        return toRecruiterResponse(savedApplication);
    }

    /*
     * Recruiter:
     * Returns all candidates for a job ordered by ATS score.
     */
    public List<RecruiterApplicationResponse> getRankedApplications(
            Long jobId) {

        List<Application> applications =
                applicationRepository
                        .findByJobIdOrderByAtsResultScoreDesc(jobId);

        return applications.stream()
                .map(this::toRecruiterResponse)
                .toList();
    }

    /*
     * Student:
     * Apply for a job and receive only student-facing information.
     */
    @Transactional
    public StudentApplicationResponse apply(
            ApplicationRequest request) {

        StudentProfile profile =
                profileRepository.findById(
                        request.getProfileId()
                ).orElseThrow(() ->
                        new IllegalArgumentException(
                                "Profile not found: "
                                        + request.getProfileId()
                        ));

        Job job =
                jobRepository.findById(
                        request.getJobId()
                ).orElseThrow(() ->
                        new IllegalArgumentException(
                                "Job not found: "
                                        + request.getJobId()
                        ));

        // Prevent duplicate applications
        if (applicationRepository
                .findByProfileIdAndJobId(
                        request.getProfileId(),
                        request.getJobId()
                )
                .isPresent()) {

            throw new IllegalArgumentException(
                    "You have already applied to this job"
            );
        }

        // Prepare ATS request
        ATSRequest atsRequest = new ATSRequest();

        atsRequest.setProfileId(
                request.getProfileId()
        );

        atsRequest.setJobId(
                request.getJobId()
        );

        // Run ATS analysis
        ATSResponse atsResponse =
                atsService.analyze(atsRequest);

        // Create application
        Application application =
                new Application();

        application.setProfile(profile);
        application.setJob(job);
        application.setStatus(
                ApplicationStatus.APPLIED
        );
        application.setAppliedAt(
                LocalDateTime.now()
        );

        // Create ATS result
        ATSResult atsResult =
                new ATSResult();

        atsResult.setApplication(
                application
        );

        atsResult.setScore(
                atsResponse.getScore()
        );

        atsResult.setEligible(
                atsResponse.isEligible()
        );

        atsResult.setMatchedRequiredSkills(
                atsResponse.getMatchedRequiredSkills()
        );

        atsResult.setMissingRequiredSkills(
                atsResponse.getMissingRequiredSkills()
        );

        atsResult.setMatchedPreferredSkills(
                atsResponse.getMatchedPreferredSkills()
        );

        atsResult.setMissingPreferredSkills(
                atsResponse.getMissingPreferredSkills()
        );

        // Store student-facing AI suggestions
        atsResult.setStudentSuggestions(
                atsResponse.getStudentSuggestions()
        );

        // Store recruiter-facing AI explanation
        atsResult.setRecruiterExplanation(
                atsResponse.getRecruiterExplanation()
        );

        application.setAtsResult(
                atsResult
        );

        // Save complete application + ATS result
        Application savedApplication =
                applicationRepository.save(
                        application
                );

        // Student receives student-facing response only
        return toStudentResponse(
                savedApplication
        );
    }

    /*
     * Student:
     * View their application details.
     */
    public StudentApplicationResponse getApplication(
            Long id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Application not found: "
                                                + id
                                ));

        return toStudentResponse(
                application
        );
    }

    /*
     * Converts Application entity into student-facing DTO.
     *
     * IMPORTANT:
     * recruiterExplanation is intentionally NOT included.
     */
    private StudentApplicationResponse toStudentResponse(
            Application application) {

        ATSResult atsResult =
                application.getAtsResult();

        StudentApplicationResponse response =
                new StudentApplicationResponse();

        response.setId(
                application.getId()
        );

        response.setProfileId(
                application.getProfile().getId()
        );

        response.setJobId(
                application.getJob().getId()
        );

        response.setStatus(
                application.getStatus().name()
        );

        response.setAppliedAt(
                application.getAppliedAt()
        );

        response.setAtsScore(
                atsResult.getScore()
        );

        response.setEligible(
                atsResult.getEligible()
        );

        response.setMatchedRequiredSkills(
                atsResult.getMatchedRequiredSkills()
        );

        response.setMissingRequiredSkills(
                atsResult.getMissingRequiredSkills()
        );

        response.setMatchedPreferredSkills(
                atsResult.getMatchedPreferredSkills()
        );

        response.setMissingPreferredSkills(
                atsResult.getMissingPreferredSkills()
        );

        response.setStudentSuggestions(
                atsResult.getStudentSuggestions()
        );

        return response;
    }

    /*
     * Converts Application entity into recruiter-facing DTO.
     *
     * IMPORTANT:
     * studentSuggestions is intentionally NOT included.
     */
    private RecruiterApplicationResponse toRecruiterResponse(
            Application application) {

        ATSResult atsResult =
                application.getAtsResult();

        RecruiterApplicationResponse response =
                new RecruiterApplicationResponse();

        response.setId(
                application.getId()
        );

        response.setProfileId(
                application.getProfile().getId()
        );

        response.setJobId(
                application.getJob().getId()
        );

        response.setStatus(
                application.getStatus().name()
        );

        response.setAppliedAt(
                application.getAppliedAt()
        );

        response.setAtsScore(
                atsResult.getScore()
        );

        response.setEligible(
                atsResult.getEligible()
        );

        response.setMatchedRequiredSkills(
                atsResult.getMatchedRequiredSkills()
        );

        response.setMissingRequiredSkills(
                atsResult.getMissingRequiredSkills()
        );

        response.setMatchedPreferredSkills(
                atsResult.getMatchedPreferredSkills()
        );

        response.setMissingPreferredSkills(
                atsResult.getMissingPreferredSkills()
        );

        response.setRecruiterExplanation(
                atsResult.getRecruiterExplanation()
        );

        return response;
    }
}