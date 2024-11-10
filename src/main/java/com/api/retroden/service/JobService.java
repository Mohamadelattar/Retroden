package com.api.retroden.service;

import com.api.retroden.model.Job;
import com.api.retroden.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            return jobOptional.get();
        } else {
            throw new RuntimeException("Job not found with id " + id);
        }
    }
    public Job create(Job job) {
        return jobRepository.save(job);
    }
    public Job update(Long id,Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job existingJob = jobOptional.get();
            existingJob.setTitle(job.getTitle());
            existingJob.setCompany(job.getCompany());
            existingJob.setDescription(job.getDescription());
        return jobRepository.save(existingJob);
        } else {
            throw new RuntimeException("Job not found with id " + id);
        }
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}
