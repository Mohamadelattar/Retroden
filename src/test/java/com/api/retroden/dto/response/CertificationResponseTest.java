package com.api.retroden.dto.response;

import com.api.retroden.model.Certification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CertificationResponseTest {
    @Test
    void testNoArgsConstructor(){
        CertificationResponse response = new CertificationResponse();
        assertNotNull(response);
        assertNull(response.getName());
    }

    @Test
    void testAllArgsConstructor(){
        String name = "Java Certification";
        CertificationResponse response = new CertificationResponse(name);
        assertNotNull(response);
        assertEquals(name,response.getName());
    }

    @Test
    void testBuilder(){
        String name = "Spring Certification";
        CertificationResponse response = CertificationResponse.builder()
                .name(name)
                .build();
        assertNotNull(response);
        assertEquals(name,response.getName());
    }

    @Test
    void testSetterAndGetter() {
        CertificationResponse response = new CertificationResponse();
        String name = "AWS Certification";
        response.setName(name);
        assertEquals(name, response.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        String name = "Docker Certification";
        CertificationResponse response1 = new CertificationResponse(name);
        CertificationResponse response2 = new CertificationResponse(name);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
    }


    @Test
    void testToString(){
        String name = "name";
        CertificationResponse response = new CertificationResponse(name);
        String toString = response.toString();
        assertTrue(toString.contains("name=" + name));
    }
}
