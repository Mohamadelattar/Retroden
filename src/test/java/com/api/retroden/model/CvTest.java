package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CvTest {
    private final Long idCv = 1L;
    private final String name = "Cv";
    private final byte[] data = {1,2,3,4,5,6,7,8,9};
    private final Professional professional = mock(Professional.class);

    @Test
    void shouldCreateCvUsingConstructor(){
        CV cv = new CV(idCv,
                        name,
                        data,
                professional);
        assertEquals(idCv,cv.getIdCV());
        assertEquals(name,cv.getName());
        assertEquals(data,cv.getData());
        assertEquals(professional,cv.getProfessional());
    }
    @Test
    void shouldCreateCvUsingBuilder(){
        CV cv = CV.builder().idCV(idCv)
                .name(name)
                .data(data)
                .professional(professional)
                .build();
        assertEquals(idCv,cv.getIdCV());
        assertEquals(name,cv.getName());
        assertEquals(data,cv.getData());
        assertEquals(professional,cv.getProfessional());
    }

    @Test
    void shouldCreateCvUsingSetter(){
        CV cv = new CV();
        cv.setIdCV(idCv);
        cv.setName(name);
        cv.setData(data);
        cv.setProfessional(professional);

        assertEquals(idCv,cv.getIdCV());
        assertEquals(name,cv.getName());
        assertEquals(data,cv.getData());
        assertEquals(professional,cv.getProfessional());
    }

}
