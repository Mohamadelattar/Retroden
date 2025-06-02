package com.api.retroden.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IndustryResponseTest {

    @Test
    void testNoArgsConstructor() {
        IndustryResponse response = new IndustryResponse();
        assertNotNull(response);
        assertNull(response.getName());
    }

    @Test
    void testAllArgsConstructor() {
        String name = "Technology";
        IndustryResponse response = new IndustryResponse(name);
        assertNotNull(response);
        assertEquals(name, response.getName());
    }

    @Test
    void testBuilder() {
        String name = "Healthcare";
        IndustryResponse response = IndustryResponse.builder()
                .name(name)
                .build();
        assertNotNull(response);
        assertEquals(name, response.getName());
    }

    @Test
    void testSetterAndGetter() {
        IndustryResponse response = new IndustryResponse();
        String name = "Finance";
        response.setName(name);
        assertEquals(name, response.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        String name = "Manufacturing";
        IndustryResponse response1 = new IndustryResponse(name);
        IndustryResponse response2 = new IndustryResponse(name);
        IndustryResponse response3 = new IndustryResponse("Retail");

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        String name = "Education";
        IndustryResponse response = new IndustryResponse(name);
        String toString = response.toString();

        assertTrue(toString.contains("name=" + name));
    }
}