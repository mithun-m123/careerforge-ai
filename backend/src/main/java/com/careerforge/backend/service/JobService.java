package com.careerforge.backend.service;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.JobRequest;
import com.careerforge.backend.dto.JobResponse;
import com.careerforge.backend.entity.ExperienceType;
import com.careerforge.backend.entity.Job;
import com.careerforge.backend.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
public JobResponse createJob(JobRequest request) {

    if (request.getExperienceType() == ExperienceType.REQUIRED
            && request.getMinimumExperience() == null) {

        throw new IllegalArgumentException(
                "Minimum experience is required"
        );
    }

    Job job = new Job();

    job.setTitle(request.getTitle());
    job.setDescription(request.getDescription());
    job.setExperienceType(request.getExperienceType());
    job.setMinimumExperience(request.getMinimumExperience());
    job.setSeniority(request.getSeniority());

    Job savedJob = jobRepository.save(job);

    JobResponse response = new JobResponse();

    response.setId(savedJob.getId());
    response.setTitle(savedJob.getTitle());
    response.setDescription(savedJob.getDescription());
    response.setExperienceType(savedJob.getExperienceType());
    response.setMinimumExperience(savedJob.getMinimumExperience());
    response.setSeniority(savedJob.getSeniority());

    return response;
}
    
}