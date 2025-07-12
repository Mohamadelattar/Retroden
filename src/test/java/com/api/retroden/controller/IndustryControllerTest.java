package com.api.retroden.controller;

import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.service.IndustryService;
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

@WebMvcTest(IndustryController.class)
public class IndustryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndustryService industryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        IndustryRequest industryRequest = new IndustryRequest(1l, "Info", List.of("HP"));
        mockMvc.perform(post("/industry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(industryRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Industry created"));
    }

    @Test
    void testGetById() throws Exception {
        IndustryResponse industryResponse = new IndustryResponse();
        Mockito.when(industryService.findById(Mockito.anyLong())).thenReturn(industryResponse);

        mockMvc.perform(get("/industry/{id}",1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<IndustryResponse> responses = List.of(new IndustryResponse(), new IndustryResponse(), new IndustryResponse());
        Mockito.when(industryService.findAll()).thenReturn(responses);
        mockMvc.perform(get("/industry"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        IndustryRequest industryRequest = new IndustryRequest(1l, "Info", List.of("HP"));
        IndustryResponse industryResponse = new IndustryResponse();
        Mockito.when(industryService.findById(Mockito.anyLong())).thenReturn(industryResponse);

        mockMvc.perform(put("/industry")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(industryRequest)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/industry?id=1"))
                .andExpect(status().isOk());
    }



}
