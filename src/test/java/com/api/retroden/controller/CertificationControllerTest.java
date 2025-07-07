package com.api.retroden.controller;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import com.api.retroden.service.CertificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CertificationController.class)
public class CertificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CertificationService certificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        CertificationRequest request = new CertificationRequest(1L, "JAVA17", new byte[]{1, 2, 3}, 1L);
        mockMvc.perform(post("/certification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Certification created"));
    }

    @Test
    void testGetById() throws Exception {
        CertificationResponse response = new CertificationResponse();
        Mockito.when(certificationService.findById(1L)).thenReturn(response);
        mockMvc.perform(get("/certification/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<CertificationResponse> responses = Arrays.asList(new CertificationResponse(), new CertificationResponse(), new CertificationResponse());
        Mockito.when(certificationService.findAll()).thenReturn(responses);

        mockMvc.perform(get("/certification"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        CertificationRequest request = new CertificationRequest(1L, "JAVA17", new byte[]{1, 2, 3}, 1L);
        CertificationResponse updated= new CertificationResponse();
        Mockito.when(certificationService.update(request)).thenReturn(updated);

        mockMvc.perform(put("/certification")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/certification?id=1"))
                .andExpect(status().isOk());
    }


}
