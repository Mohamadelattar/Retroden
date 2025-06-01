package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class ProfessionelMapperTest {
    private ProfessionalMapper professionalMapper;
    private final Long idProfessionel = 1L;
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "email@email.com";
    private final int age = 18;
    private final String location = "location";
    private final Availability availability = Availability.FULL_TIME;
    private final List<String> skills = Arrays.asList("skills");
    private CV cv = mock(CV.class);
    private List<String> certifications = Arrays.asList("certification");

    @BeforeEach
    void setUp() {
        professionalMapper = new ProfessionalMapper();
    }
    @Test
    void testToProfessional() {
        ProfessionelRequest request = new ProfessionelRequest(idProfessionel,
                firstName,
                lastName,
                email,
                age,
                location,
                availability,
                skills,
                cv.getIdCV(),
                certifications
        );
        Professionel result = professionalMapper.toProfessionel(request);
        assertNotNull(result);
        assertEquals(idProfessionel,result.getIdProfessionel());
        assertEquals(firstName,result.getFirstName());
        assertEquals(lastName,result.getLastName());
        assertEquals(email,result.getEmail());
        assertEquals(age,result.getAge());
        assertEquals(location,result.getLocation());
        assertEquals(availability,result.getAvailability());
    }

    @Test
    void testToProfessionelResponse() {
        Professionel professionel = Professionel.builder()
                .idProfessionel(idProfessionel)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .age(age)
                .location(location)
                .availability(availability)
                .build();
        ProfessionelResponse response = professionalMapper.toProfessionelResponse(professionel);
        assertNotNull(response);
        assertEquals(firstName,response.getFirstName());
        assertEquals(lastName,response.getLastName());
        assertEquals(email,response.getEmail());
        assertEquals(age,response.getAge());
        assertEquals(location,response.getLocation());
        assertEquals(availability,response.getAvailability());
    }
}
