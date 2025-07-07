package com.api.retroden.controller;

import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.service.CVService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CvController.class)
public class CVControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CVService cvService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        CvRequest cvRequest = new CvRequest(1L, "CV", new byte[]{1, 2, 2}, 1L);
        mockMvc.perform(post("/cv")
                        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cvRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("CV created"));
    }

    @Test
    void testGetById() throws Exception {
        CVResponse cvResponse = new CVResponse();
        Mockito.when(cvService.findById(Mockito.anyLong())).thenReturn(cvResponse);

        mockMvc.perform(get("/cv/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<CVResponse> responses = List.of(new CVResponse());
        Mockito.when(cvService.findAll()).thenReturn(responses);
        mockMvc.perform(get("/cv"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        CvRequest cvRequest = new CvRequest(1L, "CV", new byte[]{1, 2, 2}, 1L);
        CVResponse cvResponse = new CVResponse();

        Mockito.when(cvService.findById(Mockito.anyLong())).thenReturn(cvResponse);

        mockMvc.perform(put("/cv" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cvRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/cv?id=1" ))
                .andExpect(status().isOk());
    }
}
