package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CertifacationTest {
    private  final Long idCertification = 1L;
    private final String name = "Java Cert";
    private final Professionel professionel = mock(Professionel.class);
    private final byte[] data = {1,2,3};
    @Test
    void shouldCreateCertifacationUsingConstructor() {
        Certification certification = new Certification(idCertification,
                                                        name,
                                                        data,professionel);
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professionel,certification.getProfessional());
    }

    @Test
    void shouldCreateCertifacationUsingBuilder() {
        Certification certification = Certification.builder()
                .idCertification(idCertification)
                .name(name)
                .data(data)
                .professional(professionel)
                .build();
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professionel,certification.getProfessional());

    }
    @Test
    void shouldCreateCertifacationUsingSetter() {
        Certification certification = new Certification();
        certification.setIdCertification(idCertification);
        certification.setName(name);
        certification.setData(data);
        certification.setProfessional(professionel);
        
        assertEquals(idCertification, certification.getIdCertification());
        assertEquals(name, certification.getName());
        assertEquals(data, certification.getData());
        assertEquals(professionel,certification.getProfessional());
    }
}
