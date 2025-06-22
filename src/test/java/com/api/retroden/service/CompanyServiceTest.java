package com.api.retroden.service;

import com.api.retroden.dto.mapper.CompanyMapper;
import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import com.api.retroden.model.Job;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.IndustryRepository;
import com.api.retroden.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private IndustryRepository industryRepository;
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        CompanyRequest request = mock(CompanyRequest.class);
        Company company = new Company();
        Industry industry = new Industry();
        Job job = new Job();
        Company savedCompany = new Company();
        CompanyResponse response = new CompanyResponse("Java");

        // Mocking request values
        when(request.industryID()).thenReturn(1L);
        when(request.jobs()).thenReturn(List.of("Developer"));

        // Mapping from request to entity
        when(companyMapper.toCompany(request)).thenReturn(company);

        // Mocking industry and job repositories
        when(industryRepository.findById(1L)).thenReturn(Optional.of(industry));
        when(jobRepository.findByTitle("Developer")).thenReturn(job);

        // Saving and mapping back to response
        when(companyRepository.save(company)).thenReturn(savedCompany);
        when(companyMapper.toCompanyResponse(savedCompany)).thenReturn(response);

        // Testing create method
        CompanyResponse result = companyService.create(request);

        assertNotNull(result);
        verify(companyMapper).toCompany(request);
        verify(industryRepository).findById(1L);
        verify(jobRepository).findByTitle("Developer");
        verify(companyRepository).save(company);
        verify(companyMapper).toCompanyResponse(savedCompany);


    }

    @Test
    void testUpdate(){
        CompanyRequest request = mock(CompanyRequest.class);
        Company existingCompany = new Company();
        Industry industry = new Industry();
        Job job = new Job();
        List<Job> jobList = List.of(job);
        Company savedCompany = new Company();
        CompanyResponse response = new CompanyResponse("Java");

        // Mock request values
        when(request.id()).thenReturn(1L);
        when(request.name()).thenReturn("New Name");
        when(request.industryID()).thenReturn(2L);
        when(request.jobs()).thenReturn(List.of("Developer"));

        // Mock repository and mapper behavior
        when(companyRepository.findById(1L)).thenReturn(Optional.of(existingCompany));
        when(industryRepository.findById(2L)).thenReturn(Optional.of(industry));
        when(jobRepository.findByTitle("Developer")).thenReturn(job);
        when(companyRepository.save(existingCompany)).thenReturn(savedCompany);
        when(companyMapper.toCompanyResponse(savedCompany)).thenReturn(response);

        // Test update method
        CompanyResponse result = companyService.update(request);

        assertNotNull(result);
        assertEquals(response, result);
        verify(companyRepository).findById(1L);
        verify(industryRepository).findById(2L);
        verify(jobRepository).findByTitle("Developer");
        verify(companyRepository).save(existingCompany);
        verify(companyMapper).toCompanyResponse(savedCompany);

    }

    @Test
    void testDelete(){
        Long id = 5L;
        companyService.delete(id);
        verify(companyRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById(){
        Long id = 1L;

        Company company = new Company();
        CompanyResponse response = new CompanyResponse("Java");

        when(companyRepository.findById(id)).thenReturn(Optional.of(company));
        when(companyMapper.toCompanyResponse(company)).thenReturn(response);

        CompanyResponse result = companyService.findById(id);

        assertNotNull(result);
        assertEquals(response, result);
        verify(companyRepository).findById(id);
        verify(companyMapper).toCompanyResponse(company);
    }

    @Test
    void testFindByIdNotFound(){
        Long id = 99L;
        when(companyRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> companyService.findById(id));
        assertEquals("Company not found with id " + id, exception.getMessage());
        verify(companyRepository).findById(id);

    }
}
