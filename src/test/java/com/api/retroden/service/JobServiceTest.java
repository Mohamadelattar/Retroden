package com.api.retroden.service;

import com.api.retroden.dto.mapper.JobMapper;
import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Job;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobServiceTest {
    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobMapper jobMapper;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        JobRequest request = new JobRequest(1L, "Java", "Developer", 1L);
        Job job = new Job();
        Job savedJob = new Job();
        JobResponse response = new JobResponse();
        Company savedCompany = new Company();

        when(jobMapper.toJob(request)).thenReturn(job);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(savedCompany));
        when(jobRepository.save(job)).thenReturn(savedJob);
        when(jobMapper.toJobResponse(savedJob)).thenReturn(response);

        JobResponse result = jobService.create(request);

        assertNotNull(result);
        assertEquals(response, result);
        verify(jobMapper).toJob(request);
        verify(companyRepository).findById(1L);
    }

    @Test
    void testUpdate(){
        JobRequest request = new JobRequest(1L, "Java", "Developer", 1L);
        Job existingJob = new Job();
        Job updatedJob = new Job();
        JobResponse response = new JobResponse();
        Company savedCompany = new Company();

        // Mocking repository and mapper behaviour
        when(jobRepository.findById(1L)).thenReturn(Optional.of(existingJob));
        when(companyRepository.findById(1L)).thenReturn(Optional.of(savedCompany));
        when(jobRepository.save(existingJob)).thenReturn(updatedJob);
        when(jobMapper.toJobResponse(updatedJob)).thenReturn(response);

        // Testing update method
        JobResponse result = jobService.update(request);

        assertNotNull(result);
        assertEquals(response, result);
        verify(jobRepository).findById(1L);
        verify(companyRepository).findById(1L);
        verify(jobRepository).save(existingJob);
        verify(jobMapper).toJobResponse(updatedJob);
    }

    @Test
    void testDelete(){
        Long id = 5L;
        jobService.delete(id);
        verify(jobRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById(){
        Long id = 1L;

        Job job = new Job();
        JobResponse response = new JobResponse();

        when(jobRepository.findById(id)).thenReturn(Optional.of(job));
        when(jobMapper.toJobResponse(job)).thenReturn(response);

        JobResponse result = jobService.findById(id);

        assertNotNull(result);
        assertEquals(response, result);
        verify(jobRepository).findById(id);
        verify(jobMapper).toJobResponse(job);
    }

    @Test
    void testFindByIdNotFound(){
        Long id = 99L;
        when(jobRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> jobService.findById(id));
        assertEquals("Job not found with id " + id, exception.getMessage());
    }


}
