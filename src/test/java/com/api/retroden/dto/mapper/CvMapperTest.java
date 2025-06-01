package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.model.CV;
import com.api.retroden.model.Professionel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class CvMapperTest {
    private CvMapper cvMapper;
    private final Long idCv = 1L;
    private final String name = "Cv";
    private final byte[] data = {1,2,3,4,5,6,7,8,9};
    private final Professionel professionel = mock(Professionel.class);

    @BeforeEach
    void setUp() {
        cvMapper = new CvMapper();
    }
    @Test
    void testToCv() {
        CvRequest cvRequest = new CvRequest(idCv,
                name,
                data,
                professionel.getIdProfessionel());
        CV result = cvMapper.toCV(cvRequest);
        assertNotNull(result);
        assertEquals(idCv,result.getIdCV());
        assertEquals(name,result.getName());
        assertEquals(data,result.getData());
    }

    @Test
    void testToCvResponse() {
        CV cv = CV.builder()
                .idCV(idCv)
                .name(name)
                .data(data)
                .build();
        CVResponse response = cvMapper.toCvResponse(cv);
        assertNotNull(response);
        assertEquals(name,response.getName());
        assertEquals(data,response.getData());
    }
}
