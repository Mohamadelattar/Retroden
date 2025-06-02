package com.api.retroden.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyResonseTest {
    @Test
    void testNoArgsConstructor(){
        CompanyResponse response = new CompanyResponse();
        assertNotNull(response);
        assertNull(response.getName());
    }

    @Test
    void testAllArgsConstructor(){
        String name = "Adidas";
        CompanyResponse response = new CompanyResponse(name);
        assertNotNull(response);
        assertEquals(name,response.getName());
    }

    @Test
    void testBuilde() {
        String name ="Company";
        CompanyResponse response = CompanyResponse.builder()
                .name(name)
                .build();
        assertNotNull(response);
        assertEquals(name,response.getName());
    }
    @Test
    void testSetterAndGetter() {
        CompanyResponse response = new CompanyResponse();
        String name = "Company";
        response.setName(name);
        assertEquals(name,response.getName());
    }

    @Test
    void testEqualsAndHashCode(){
        String name = "Company";
        CompanyResponse response1 = new CompanyResponse(name);
        CompanyResponse response2 = new CompanyResponse(name);
        assertEquals(response1,response2);
        assertEquals(response1.hashCode(),response2.hashCode());
    }

    @Test
    void testToString(){
        String name = "Company";
        CompanyResponse response = new CompanyResponse(name);
        String toString = response.toString();
        assertTrue(toString.contains("name=" + name));
    }

}
