package com.api.retroden.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobResponseTest {

    @Test
    void testNoArgsConstructor() {
        JobResponse response = new JobResponse();
        assertNotNull(response);
        assertNull(response.getTitle());
        assertNull(response.getDescription());
    }

    @Test
    void testAllArgsConstructor() {
        String title = "Software Engineer";
        String description = "Java development position";
        JobResponse response = new JobResponse(title, description);

        assertNotNull(response);
        assertEquals(title, response.getTitle());
        assertEquals(description, response.getDescription());
    }

    @Test
    void testBuilder() {
        String title = "Senior Developer";
        String description = "Lead development team";

        JobResponse response = JobResponse.builder()
                .title(title)
                .description(description)
                .build();

        assertNotNull(response);
        assertEquals(title, response.getTitle());
        assertEquals(description, response.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        JobResponse response = new JobResponse();
        String title = "Product Manager";
        String description = "Managing product roadmap";

        response.setTitle(title);
        response.setDescription(description);

        assertEquals(title, response.getTitle());
        assertEquals(description, response.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        String title = "DevOps Engineer";
        String description = "Cloud infrastructure management";

        JobResponse response1 = new JobResponse(title, description);
        JobResponse response2 = new JobResponse(title, description);
        JobResponse response3 = new JobResponse("Different Title", "Different Description");

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testPartialEquality() {
        JobResponse response1 = new JobResponse("Same Title", "Description 1");
        JobResponse response2 = new JobResponse("Same Title", "Description 2");

        assertNotEquals(response1, response2);
    }

    @Test
    void testToString() {
        String title = "QA Engineer";
        String description = "Software testing position";
        JobResponse response = new JobResponse(title, description);

        String toString = response.toString();

        assertTrue(toString.contains("title=" + title));
        assertTrue(toString.contains("description=" + description));
    }

    @Test
    void testBuilderWithPartialData() {
        String title = "Data Scientist";

        JobResponse response = JobResponse.builder()
                .title(title)
                .build();

        assertNotNull(response);
        assertEquals(title, response.getTitle());
        assertNull(response.getDescription());
    }
}