package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import com.api.retroden.model.Professionel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class CertificationMapperTest {
    private CertificationMapper certificationMapper;
    private  final Long idCertification = 1L;
    private final String name = "Java Cert";
    private final Professionel professionel = mock(Professionel.class);
    private final byte[] data = {1,2,3};

    @BeforeEach
    void setUp() {
        certificationMapper = new CertificationMapper();
    }
    @Test
    void testToCertification(){
        CertificationRequest request = new CertificationRequest(idCertification,
                name,
                data,
                professionel.getIdProfessionel());
        Certification result = certificationMapper.toCertification(request);
        assertNotNull(result);
        assertEquals(idCertification,result.getIdCertification());
        assertEquals(name,result.getName());
        assertEquals(data,result.getData());
    }

    @Test
    void testToCertificationResponse(){
        Certification certification = Certification.builder()
                .idCertification(idCertification)
                .name(name)
                .build();
        CertificationResponse response = certificationMapper.toCertificationResponse(certification);
        assertNotNull(response);
        assertEquals(name,response.getName());
    }
}
