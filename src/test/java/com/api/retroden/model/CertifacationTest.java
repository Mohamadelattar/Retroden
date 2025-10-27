package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CertifacationTest {
    private  final Long idCertification = 1L;
    private final String name = "Java Cert";
    private final Professional professional = mock(Professional.class);
    private final byte[] data = {1,2,3};
    @Test
    void shouldCreateCertifacationUsingConstructor() {
        Certification certification = new Certification(idCertification,
                                                        name,
                                                        data, professional);
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professional,certification.getProfessional());
    }

    @Test
    void shouldCreateCertifacationUsingBuilder() {
        Certification certification = Certification.builder()
                .idCertification(idCertification)
                .name(name)
                .data(data)
                .professional(professional)
                .build();
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professional,certification.getProfessional());

    }
    @Test
    void shouldCreateCertifacationUsingSetter() {
        Certification certification = new Certification();
        certification.setIdCertification(idCertification);
        certification.setName(name);
        certification.setData(data);
        certification.setProfessional(professional);
        
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professional,certification.getProfessional());
    }
}
