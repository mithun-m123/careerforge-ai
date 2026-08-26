package com.careerforge.backend.service;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.JobRequest;
import com.careerforge.backend.entity.Job;
import com.careerforge.backend.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setExperienceType(request.getExperienceType());
        job.setMinimumExperience(request.getMinimumExperience());
        job.setSeniority(request.getSeniority());

        return jobRepository.save(job);
    }
}