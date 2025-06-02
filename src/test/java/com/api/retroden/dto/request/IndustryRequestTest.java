package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndustryRequestTest {
    Long id = 1L;
    String name = "name";
    List<String> companies = Arrays.asList("company1", "company2");

    @Test
    public void testIndustryRequestCreation() {
        IndustryRequest request = new IndustryRequest(id, name, companies);
        assertEquals(id,request.id());
        assertEquals(name,request.name());
        assertEquals(companies,request.companies());
    }

}
