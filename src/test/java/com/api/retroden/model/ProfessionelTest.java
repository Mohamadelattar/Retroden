package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProfessionelTest {
    private final Long idProfessionel = 1L;
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "email@email.com";
    private final int age = 18;
    private final String location = "location";
    private final Availability availability = Availability.FULL_TIME;
    private List<Skill> skills = Arrays.asList(mock(Skill.class), mock(Skill.class));
    private CV cv = mock(CV.class);
    private final List<Certification> certifications = Arrays.asList(mock(Certification.class), mock(Certification.class));

    @Test
    void shouldCreateProfessionelUsingConstructor() {
        Professionel professionel = new Professionel(idProfessionel,
                                                    firstName,
                                                    lastName,
                                                    email,
                                                    age,
                                                    location,
                                                    availability,
                                                    skills,
                                                    cv,
                                                    certifications);
        assertEquals(idProfessionel,professionel.getIdProfessionel());
        assertEquals(firstName,professionel.getFirstName());
        assertEquals(lastName,professionel.getLastName());
        assertEquals(email,professionel.getEmail());
        assertEquals(age,professionel.getAge());
        assertEquals(location,professionel.getLocation());
        assertEquals(availability,professionel.getAvailability());
        assertEquals(skills,professionel.getSkills());
        assertEquals(cv,professionel.getCv());
        assertEquals(certifications,professionel.getCertifications());

    }
    @Test
    void shouldCreateProfessionelUsingBuilder() {
        Professionel professionel = Professionel.builder()
                .idProfessionel(idProfessionel)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .age(age)
                .location(location)
                .availability(availability)
                .skills(skills)
                .cv(cv)
                .certifications(certifications).build();
        assertEquals(idProfessionel,professionel.getIdProfessionel());
        assertEquals(firstName,professionel.getFirstName());
        assertEquals(lastName,professionel.getLastName());
        assertEquals(email,professionel.getEmail());
        assertEquals(age,professionel.getAge());
        assertEquals(location,professionel.getLocation());
        assertEquals(availability,professionel.getAvailability());
        assertEquals(skills,professionel.getSkills());
        assertEquals(cv,professionel.getCv());
        assertEquals(certifications,professionel.getCertifications());

    }

    @Test
    void shouldCreateProfessionelUsingSetter() {
        Professionel professionel = new Professionel();
        professionel.setIdProfessionel(idProfessionel);
        professionel.setFirstName(firstName);
        professionel.setLastName(lastName);
        professionel.setEmail(email);
        professionel.setAge(age);
        professionel.setLocation(location);
        professionel.setAvailability(availability);
        professionel.setSkills(skills);
        professionel.setCv(cv);
        professionel.setCertifications(certifications);
        assertEquals(idProfessionel,professionel.getIdProfessionel());
        assertEquals(firstName,professionel.getFirstName());
        assertEquals(lastName,professionel.getLastName());
        assertEquals(email,professionel.getEmail());
        assertEquals(age,professionel.getAge());
        assertEquals(location,professionel.getLocation());
        assertEquals(availability,professionel.getAvailability());
        assertEquals(skills,professionel.getSkills());
        assertEquals(cv,professionel.getCv());
        assertEquals(certifications,professionel.getCertifications());

    }
}
