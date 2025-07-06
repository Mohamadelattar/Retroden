package com.api.retroden.service;

import com.api.retroden.dto.mapper.IndustryMapper;
import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.IndustryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class IndustryServiceTest {

    @Mock
    private IndustryMapper industryMapper;

    @Mock
    private IndustryRepository industryRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private IndustryService industryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        IndustryRequest request = new IndustryRequest(1L, "Informatique", List.of("HP", "Intel"));
        Industry industry = new Industry();
        Industry savedIndustry = new Industry();
        IndustryResponse response = new IndustryResponse("Informatique");
        Company someCompany = new Company();

        // Mapping from request to entity
        when(industryMapper.toIndustry(request)).thenReturn(industry);

        // Mocking industry repository
        when(companyRepository.findById(1L)).thenReturn(Optional.of(someCompany));
        when(industryRepository.save(industry)).thenReturn(savedIndustry);
        when(industryMapper.toIndustryResponse(savedIndustry)).thenReturn(response);

        // Testing create method
        IndustryResponse result = industryService.create(request);

        assertNotNull(result);
        verify(industryMapper).toIndustry(request);
        verify(industryRepository).save(industry);
        verify(industryMapper).toIndustryResponse(savedIndustry);
        assertEquals(response, result);
        verify(industryMapper).toIndustryResponse(savedIndustry);
        verify(industryRepository).save(industry);

    }

    @Test
    void testUpdate(){
        IndustryRequest request = new IndustryRequest(1L, "Informatique", List.of("HP", "Intel"));
        Industry existingIndustry = new Industry();
        Industry updatedIndustry = new Industry();
        IndustryResponse response = new IndustryResponse("Informatique");
        Company someCompany = new Company();

        // Mocking repository and mapper behavior
        when(industryRepository.findById(1L)).thenReturn(Optional.of(existingIndustry));
        when(companyRepository.findById(1L)).thenReturn(Optional.of(someCompany));
        when(industryRepository.save(existingIndustry)).thenReturn(updatedIndustry);
        when(industryMapper.toIndustryResponse(updatedIndustry)).thenReturn(response);

        // Testing update method
        IndustryResponse result = industryService.update(request);
    }

    @Test
    void testDelete(){
        Long id = 5L;
        industryRepository.deleteById(id);
        verify(industryRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById(){
        Long id = 1L;

        Industry industry = new Industry();
        IndustryResponse response = new IndustryResponse("Informatique");

        when(industryRepository.findById(id)).thenReturn(Optional.of(industry));
        when(industryMapper.toIndustryResponse(industry)).thenReturn(response);

        IndustryResponse result = industryService.findById(id);

        assertNotNull(result);
        assertEquals(response, result);
        verify(industryRepository).findById(id);
        verify(industryMapper).toIndustryResponse(industry);
    }

    @Test
    void testFindByIdNotFound(){
        Long id = 99L;
        when(industryRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> industryService.findById(id));
        assertEquals("Industry not found with id " + id, exception.getMessage());
    }
}
