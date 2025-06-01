package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.model.Industry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class IndustryMapperTest {
    private IndustryMapper industryMapper;
    private final Long  idIndustry = 1L;
    private final String name = "Industry";
    private final List<String> companies = Arrays.asList("Companies");
    @BeforeEach
    void setUp() {
        industryMapper = new IndustryMapper();
    }
    @Test
    void testToIndustry() {
        IndustryRequest   industryRequest = new IndustryRequest(idIndustry,
                                                                name,
                                                                companies);
        Industry result = industryMapper.toIndustry(industryRequest);
        assertNotNull(result);
        assertEquals(idIndustry,result.getIdIndustry());
        assertEquals(name,result.getName());
    }
    @Test
    void testToIndustryResponse() {
        Industry industry = Industry.builder()
                .idIndustry(idIndustry)
                .name(name)
                .build();
        IndustryResponse response = industryMapper.toIndustryResponse(industry);
        assertNotNull(response);
        assertEquals(name,response.getName());
    }
}
