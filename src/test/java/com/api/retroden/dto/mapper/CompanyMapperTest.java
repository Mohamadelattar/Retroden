package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import com.api.retroden.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class CompanyMapperTest {
    private CompanyMapper companyMapper;
    private final Long idCompany = 1L;
    private final String name = "Company Name";
    private final Industry industry = mock(Industry.class);
    private final List<Job> jobs = Arrays.asList(mock(Job.class), mock(Job.class));
    private final List<String> jobTitles = Arrays.asList("Job Title 1", "Job Title 2", "Job Title 3");

    @BeforeEach
    void setUp() {
        companyMapper = new CompanyMapper();
    }
    @Test
    void testToCompany() {
        CompanyRequest request = new CompanyRequest(idCompany,
                                                    name,
                                                    industry.getIdIndustry(),
                                                    jobTitles);
        Company result = companyMapper.toCompany(request);
        assertNotNull(result);
        assertEquals(idCompany,result.getId());
        assertEquals(name,result.getName());
    }

    @Test
    void testToCompanyResponse() {
        Company company = Company.builder()
                .id(idCompany)
                .name(name)
                .build();
        CompanyResponse response = companyMapper.toCompanyResponse(company);
        assertNotNull(response);
        assertEquals(name,response.getName());

    }
}
