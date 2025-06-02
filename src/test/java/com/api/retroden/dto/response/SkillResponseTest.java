package com.api.retroden.dto.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SkillResponseTest {

    @Test
    void testNoArgsConstructor() {
        SkillResponse response = new SkillResponse();
        assertNotNull(response);
        assertNull(response.getSkillName());
    }

    @Test
    void testAllArgsConstructor() {
        String skillName = "Java";
        SkillResponse response = new SkillResponse(skillName);
        assertNotNull(response);
        assertEquals(skillName, response.getSkillName());
    }

    @Test
    void testBuilder() {
        String skillName = "Spring Boot";
        SkillResponse response = SkillResponse.builder()
                .skillName(skillName)
                .build();
        assertNotNull(response);
        assertEquals(skillName, response.getSkillName());
    }

    @Test
    void testSetterAndGetter() {
        SkillResponse response = new SkillResponse();
        String skillName = "Docker";
        response.setSkillName(skillName);
        assertEquals(skillName, response.getSkillName());
    }

    @Test
    void testEqualsAndHashCode() {
        String skillName = "Python";
        SkillResponse response1 = new SkillResponse(skillName);
        SkillResponse response2 = new SkillResponse(skillName);
        SkillResponse response3 = new SkillResponse("JavaScript");

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        String skillName = "React";
        SkillResponse response = new SkillResponse(skillName);
        String toString = response.toString();

        assertTrue(toString.contains("skillName=" + skillName));
    }
}