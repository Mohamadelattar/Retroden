package com.api.retroden.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificationRequestTest {
    Long id = 1L;
    String name = "Certification Request";
    byte[] data = {1,2,3,4};
    Long professionelId = 42L;
    @Test
    public void testCertificationRequestCreation() {
        CertificationRequest request = new CertificationRequest(id, name, data, professionelId);
        assertEquals(id,request.id());
        assertEquals(name,request.name());
        assertEquals(data,request.data());
        assertEquals(professionelId,request.professionalId());
    }

}
