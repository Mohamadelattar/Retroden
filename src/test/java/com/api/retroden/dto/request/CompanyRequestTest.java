package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyRequestTest {
    Long id = 1L;
    String name = "Company Request";
    Long industryId = 1L;
    List<String> jobs = Arrays.asList("Job1", "Job2", "Job3");

    @Test
    public void testCompanyRequestCreation() {
        CompanyRequest request = new CompanyRequest(id, name, industryId, jobs);
        assertEquals(id,request.id());
        assertEquals(name,request.name());
        assertEquals(industryId,request.industryID());
        assertEquals(jobs,request.jobs());
    }

}
