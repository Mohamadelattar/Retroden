package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillRequestTest {
    Long id = 1L;
    String name = "test";

    @Test
    public void testSkillRequestCreation() {
        SkillRequest request = new SkillRequest(id, name);
        assertEquals(id,request.id());
        assertEquals(name,request.name());
    }
}
