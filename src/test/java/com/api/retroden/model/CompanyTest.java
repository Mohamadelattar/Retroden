package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CompanyTest {
    private final Long idCompany = 1L;
    private final String name = "Company Name";
    private final Industry industry = mock(Industry.class);
    private final List<Job> jobs = Arrays.asList(mock(Job.class), mock(Job.class));

    @Test
    void shouldCreateCompanyUsingConstructor() {
        Company company = new Company(idCompany,
                                        name,
                                        industry,
                                        jobs);
        assertEquals(idCompany, company.getId());
        assertEquals(name, company.getName());
        assertEquals(industry, company.getIndustry());
        assertEquals(jobs, company.getJobs());
    }
    @Test
    void shouldCreateCompanyUsingBuilder() {
        Company company = Company.builder()
                                 .id(idCompany)
                                 .name(name)
                                 .industry(industry)
                                 .jobs(jobs)
                                 .build();

        assertEquals(idCompany, company.getId());
        assertEquals(name, company.getName());
        assertEquals(industry, company.getIndustry());
        assertEquals(jobs, company.getJobs());

    }

    @Test
    void shouldCreateCompanyUsingSetter() {
        Company company = new Company();
        company.setId(idCompany);
        company.setName(name);
        company.setIndustry(industry);
        company.setJobs(jobs);

        assertEquals(idCompany, company.getId());
        assertEquals(name, company.getName());
        assertEquals(industry, company.getIndustry());
        assertEquals(jobs, company.getJobs());

    }


}
