package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CvRequestTest {
    Long id = 1L;
    String name = "CvRequestTest";
    byte[] data = {1,2,3,4};
    Long professionalId = 1L;

    @Test
    public void testCvRequestCreation() {
        CvRequest request = new CvRequest(id, name, data, professionalId);
        assertEquals(id,request.id());
        assertEquals(name,request.name());
        assertEquals(data,request.data());
        assertEquals(professionalId,request.professionalId());
    }
}
