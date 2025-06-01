package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class JobMapperTest {
    private JobMapper jobMapper;
    private final Long idJob = 1L;
    private final String title = "Job Title";
    private final String description = "Job Description";
    private final Company company = mock(Company.class);

    @BeforeEach
    void setUp() {
        jobMapper = new JobMapper();
    }
    @Test
    void testToJob(){
        JobRequest request = new JobRequest(idJob,
                                            title,
                                            description,
                                            company.getIdCompany());
        Job result = jobMapper.toJob(request);
        assertNotNull(result);
        assertEquals(idJob, result.getIdJob());
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
    }

    @Test
    void testToJobResponse(){
        Job job = Job.builder()
                .idJob(idJob)
                .title(title)
                .description(description)
                .build();
        JobResponse response = jobMapper.toJobResponse(job);
        assertNotNull(response);
        assertEquals(title, response.getTitle());
        assertEquals(description, response.getDescription());
    }
}
