package com.api.retroden.service;

import com.api.retroden.dto.mapper.JobMapper;
import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Job;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, JobMapper jobMapper, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.companyRepository = companyRepository;
    }

    public List<JobResponse> findAll() {

        return jobRepository.findAll().stream()
                .map(jobMapper::toJobResponse)
                .toList();
    }

    public JobResponse findById(Long id) {
        return jobRepository.findById(id)
                .map(jobMapper::toJobResponse)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id " + id));
    }
    public JobResponse create(JobRequest jobRequest) {
        var job = jobMapper.toJob(jobRequest);
        job.setCompany(findCompanyJob(jobRequest.companyId()));
        var savedJob = jobRepository.save(job);
        return jobMapper.toJobResponse(savedJob);
    }
    public JobResponse update(JobRequest jobRequest) {
        return jobMapper.toJobResponse(
                jobRepository.findById(jobRequest.id())
                        .map(existingJob -> {
                            Optional.ofNullable(jobRequest.title()).ifPresent(existingJob::setTitle);
                            Optional.ofNullable(findCompanyJob(jobRequest.companyId())).ifPresent(existingJob::setCompany);
                            return jobRepository.save(existingJob);
                        }).orElseThrow(() -> new EntityNotFoundException("Job not found with id " + jobRequest.id()))
        );
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
    public Company findCompanyJob(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }
}
