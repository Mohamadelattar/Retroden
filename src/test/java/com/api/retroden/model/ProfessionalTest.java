package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProfessionalTest {
    private final Long idProfessional = 1L;
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
    void shouldCreatePofessionalUsingConstructor() {
        Professional professional = new Professional(idProfessional,
                                                    firstName,
                                                    lastName,
                                                    email,
                                                    age,
                                                    location,
                                                    availability,
                                                    skills,
                                                    cv,
                                                    certifications);
        assertEquals(idProfessional, professional.getIdProfessional());
        assertEquals(firstName, professional.getFirstName());
        assertEquals(lastName, professional.getLastName());
        assertEquals(email, professional.getEmail());
        assertEquals(age, professional.getAge());
        assertEquals(location, professional.getLocation());
        assertEquals(availability, professional.getAvailability());
        assertEquals(skills, professional.getSkills());
        assertEquals(cv, professional.getCv());
        assertEquals(certifications, professional.getCertifications());

    }
    @Test
    void shouldCreatePofessionalUsingBuilder() {
        Professional professional = Professional.builder()
                .idProfessional(idProfessional)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .age(age)
                .location(location)
                .availability(availability)
                .skills(skills)
                .cv(cv)
                .certifications(certifications).build();
        assertEquals(idProfessional, professional.getIdProfessional());
        assertEquals(firstName, professional.getFirstName());
        assertEquals(lastName, professional.getLastName());
        assertEquals(email, professional.getEmail());
        assertEquals(age, professional.getAge());
        assertEquals(location, professional.getLocation());
        assertEquals(availability, professional.getAvailability());
        assertEquals(skills, professional.getSkills());
        assertEquals(cv, professional.getCv());
        assertEquals(certifications, professional.getCertifications());

    }

    @Test
    void shouldCreatePofessionalUsingSetter() {
        Professional professional = new Professional();
        professional.setIdProfessional(idProfessional);
        professional.setFirstName(firstName);
        professional.setLastName(lastName);
        professional.setEmail(email);
        professional.setAge(age);
        professional.setLocation(location);
        professional.setAvailability(availability);
        professional.setSkills(skills);
        professional.setCv(cv);
        professional.setCertifications(certifications);
        assertEquals(idProfessional, professional.getIdProfessional());
        assertEquals(firstName, professional.getFirstName());
        assertEquals(lastName, professional.getLastName());
        assertEquals(email, professional.getEmail());
        assertEquals(age, professional.getAge());
        assertEquals(location, professional.getLocation());
        assertEquals(availability, professional.getAvailability());
        assertEquals(skills, professional.getSkills());
        assertEquals(cv, professional.getCv());
        assertEquals(certifications, professional.getCertifications());

    }
}
