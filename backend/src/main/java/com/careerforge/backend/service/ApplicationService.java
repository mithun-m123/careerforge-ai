package com.careerforge.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.dto.ApplicationRequest;
import com.careerforge.backend.dto.ApplicationResponse;
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

    public List<ApplicationResponse> getRankedApplications(Long jobId) {

    List<Application> applications =
            applicationRepository
                    .findByJobIdOrderByAtsResultScoreDesc(jobId);

    return applications.stream()
            .map(this::toResponse)
            .toList();
}

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

    public ApplicationResponse apply(ApplicationRequest request) {

        StudentProfile profile =
                profileRepository.findById(request.getProfileId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Profile not found: "
                                                + request.getProfileId()
                                ));

        Job job =
                jobRepository.findById(request.getJobId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Job not found: "
                                                + request.getJobId()
                                ));

        if (applicationRepository
                .findByProfileIdAndJobId(
                        request.getProfileId(),
                        request.getJobId())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "You have already applied to this job"
            );
        }

        ATSRequest atsRequest = new ATSRequest();

        atsRequest.setProfileId(request.getProfileId());
        atsRequest.setJobId(request.getJobId());

        ATSResponse atsResponse =
                atsService.analyze(atsRequest);

        Application application = new Application();

        application.setProfile(profile);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());

        ATSResult atsResult = new ATSResult();

        atsResult.setApplication(application);
        atsResult.setScore(atsResponse.getScore());
        atsResult.setEligible(atsResponse.isEligible());

        atsResult.setMatchedRequiredSkills(
                atsResponse.getMatchedRequiredSkills());

        atsResult.setMissingRequiredSkills(
                atsResponse.getMissingRequiredSkills());

        atsResult.setMatchedPreferredSkills(
                atsResponse.getMatchedPreferredSkills());

        atsResult.setMissingPreferredSkills(
                atsResponse.getMissingPreferredSkills());

        atsResult.setSuggestions(
                atsResponse.getSuggestions());

        application.setAtsResult(atsResult);

        Application savedApplication =
                applicationRepository.save(application);

        return toResponse(savedApplication);
    }

    private ApplicationResponse toResponse(
            Application application) {

        ApplicationResponse response =
                new ApplicationResponse();

        response.setId(application.getId());

        response.setProfileId(
                application.getProfile().getId());

        response.setJobId(
                application.getJob().getId());

        response.setStatus(
                application.getStatus());

        response.setAppliedAt(
                application.getAppliedAt());

        ATSResult atsResult =
                application.getAtsResult();

        if (atsResult != null) {

            response.setAtsScore(
                    atsResult.getScore());

            response.setEligible(
                    atsResult.getEligible());

            response.setMatchedRequiredSkills(
                    atsResult.getMatchedRequiredSkills());

            response.setMissingRequiredSkills(
                    atsResult.getMissingRequiredSkills());

            response.setMatchedPreferredSkills(
                    atsResult.getMatchedPreferredSkills());

            response.setMissingPreferredSkills(
                    atsResult.getMissingPreferredSkills());

            response.setSuggestions(
                    atsResult.getSuggestions());
        }

        return response;
    }
}