package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobRequestTest {
    Long id = 1L;
    String title = "title";
    String description = "description";
    Long companyId = 1L;

    @Test
    public void testJobRequestCreation() {
        JobRequest request = new JobRequest(id, title, description, companyId);
        assertEquals(id,request.id());
        assertEquals(title,request.title());
        assertEquals(description,request.description());
        assertEquals(companyId,request.companyId());
    }
}
