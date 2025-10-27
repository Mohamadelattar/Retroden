package com.api.retroden.dto.response;

import com.api.retroden.model.Availability;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessionalResponseTest {

    @Test
    void testNoArgsConstructor() {
        ProfessionalResponse response = new ProfessionalResponse();

        assertNotNull(response);
        assertNull(response.getFirstName());
        assertNull(response.getLastName());
        assertNull(response.getEmail());
        assertEquals(0, response.getAge());
        assertNull(response.getLocation());
        assertNull(response.getAvailability());
    }

    @Test
    void testAllArgsConstructor() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        int age = 30;
        String location = "New York";
        Availability availability = Availability.FULL_TIME;

        ProfessionalResponse response = new ProfessionalResponse(
                firstName, lastName, email, age, location, availability
        );

        assertNotNull(response);
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(email, response.getEmail());
        assertEquals(age, response.getAge());
        assertEquals(location, response.getLocation());
        assertEquals(availability, response.getAvailability());
    }

    @Test
    void testBuilder() {
        String firstName = "Jane";
        String lastName = "Smith";
        String email = "jane.smith@example.com";
        int age = 25;
        String location = "London";
        Availability availability = Availability.FULL_TIME;

        ProfessionalResponse response = ProfessionalResponse.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .age(age)
                .location(location)
                .availability(availability)
                .build();

        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(email, response.getEmail());
        assertEquals(age, response.getAge());
        assertEquals(location, response.getLocation());
        assertEquals(availability, response.getAvailability());
    }

    @Test
    void testSettersAndGetters() {
        ProfessionalResponse response = new ProfessionalResponse();

        String firstName = "Alice";
        String lastName = "Johnson";
        String email = "alice.j@example.com";
        int age = 28;
        String location = "Paris";
        Availability availability = Availability.FULL_TIME;

        response.setFirstName(firstName);
        response.setLastName(lastName);
        response.setEmail(email);
        response.setAge(age);
        response.setLocation(location);
        response.setAvailability(availability);

        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(email, response.getEmail());
        assertEquals(age, response.getAge());
        assertEquals(location, response.getLocation());
        assertEquals(availability, response.getAvailability());
    }

    @Test
    void testEqualsAndHashCode() {
        ProfessionalResponse response1 = ProfessionalResponse.builder()
                .firstName("Bob")
                .lastName("Wilson")
                .email("bob.w@example.com")
                .age(35)
                .location("Berlin")
                .availability(Availability.FULL_TIME)
                .build();

        ProfessionalResponse response2 = ProfessionalResponse.builder()
                .firstName("Bob")
                .lastName("Wilson")
                .email("bob.w@example.com")
                .age(35)
                .location("Berlin")
                .availability(Availability.FULL_TIME)
                .build();

        ProfessionalResponse response3 = ProfessionalResponse.builder()
                .firstName("Different")
                .lastName("Person")
                .email("different@example.com")
                .age(40)
                .location("Madrid")
                .availability(Availability.FULL_TIME)
                .build();

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        ProfessionalResponse response = ProfessionalResponse.builder()
                .firstName("Tom")
                .lastName("Brown")
                .email("tom.brown@example.com")
                .age(45)
                .location("Sydney")
                .availability(Availability.FULL_TIME)
                .build();

        String toString = response.toString();

        assertTrue(toString.contains("firstName=Tom"));
        assertTrue(toString.contains("lastName=Brown"));
        assertTrue(toString.contains("email=tom.brown@example.com"));
        assertTrue(toString.contains("age=45"));
        assertTrue(toString.contains("location=Sydney"));
        assertTrue(toString.contains("availability=" + Availability.FULL_TIME));
    }

    @Test
    void testBuilderWithPartialData() {
        ProfessionalResponse response = ProfessionalResponse.builder()
                .firstName("Mark")
                .lastName("Davis")
                .build();

        assertEquals("Mark", response.getFirstName());
        assertEquals("Davis", response.getLastName());
        assertNull(response.getEmail());
        assertEquals(0, response.getAge());
        assertNull(response.getLocation());
        assertNull(response.getAvailability());
    }
}