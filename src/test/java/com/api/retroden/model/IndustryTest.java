package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class IndustryTest {
    private final Long  idIndustry = 1L;
    private final String name = "Industry";
    private final List<Company> companies = Arrays.asList(mock(Company.class), mock(Company.class));

    @Test
    void shouldCreateIndustryUsingConstructor() {
        Industry industry = new Industry(idIndustry, name, companies);
        assertEquals(idIndustry,industry.getIdIndustry());
        assertEquals(name,industry.getName());
        assertEquals(companies,industry.getCompanies());
    }
    @Test
    void shouldCreateIndustryUsingBuilder(){
        Industry industry = Industry.builder()
                                     .idIndustry(idIndustry)
                                     .name(name).companies(companies)
                                     .build();
        assertEquals(idIndustry,industry.getIdIndustry());
        assertEquals(name,industry.getName());
        assertEquals(companies,industry.getCompanies());

    }

    @Test
    void shouldCreateIndustryUsingSetter() {
        Industry industry = new Industry();
        industry.setIdIndustry(idIndustry);
        industry.setName(name);
        industry.setCompanies(companies);
        assertEquals(idIndustry,industry.getIdIndustry());
        assertEquals(name,industry.getName());
        assertEquals(companies,industry.getCompanies());

    }
}
