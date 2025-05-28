package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class JobTest {
    private final Long idJob = 1L;
    private final String title = "Job Title";
    private final String description = "Job Description";
    private final Company company = mock(Company.class);

    @Test
    void shouldCreateJobUsingConstructor() {
        Job job = new Job(idJob, title, description, company);
        assertEquals(idJob,job.getIdJob());
        assertEquals(title,job.getTitle());
        assertEquals(description,job.getDescription());
        assertEquals(company,job.getCompany());
    }

    @Test
    void shouldCreateJobUsingBuilder() {
        Job job= Job.builder()
                .idJob(idJob)
                .title(title)
                .description(description)
                .company(company)
                .build();
        assertEquals(idJob,job.getIdJob());
        assertEquals(title,job.getTitle());
        assertEquals(description,job.getDescription());
        assertEquals(company,job.getCompany());
    }

    @Test
    void shouldCreateJobUsingSetter() {
        Job job = new Job();
        job.setIdJob(idJob);
        job.setTitle(title);
        job.setDescription(description);
        job.setCompany(company);

        assertEquals(idJob,job.getIdJob());
        assertEquals(title,job.getTitle());
        assertEquals(description,job.getDescription());
        assertEquals(company,job.getCompany());
    }
}
