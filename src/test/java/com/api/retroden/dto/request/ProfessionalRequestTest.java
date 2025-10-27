package com.api.retroden.dto.request;

import com.api.retroden.model.Availability;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessionalRequestTest {
    Long  id = 1L;
    String firstName = "John";
    String lastName = "Smith";
    String email= "john.smith@gmail.com";
    int age= 22;
    String location ="Agadir";
    Availability availability = Availability.FULL_TIME;
    List<String> skills = Arrays.asList("skill1", "skill2");
    Long idCv = 1L;
    List<String> certefications = Arrays.asList("certefication1", "certefication2");

    @Test
    public void testProfessionelRequestCreation() {
        ProfessionalRequest request = new ProfessionalRequest(id, firstName, lastName, email, age, location, availability, skills, idCv, certefications);

        assertEquals(id,request.id());
        assertEquals(firstName,request.firstName());
        assertEquals(lastName,request.lastName());
        assertEquals(email,request.email());
        assertEquals(age,request.age());
        assertEquals(location,request.location());
        assertEquals(availability,request.availability());
        assertEquals(skills,request.skills());
        assertEquals(idCv,request.idCv());
        assertEquals(certefications,request.certefications());

    }
}
