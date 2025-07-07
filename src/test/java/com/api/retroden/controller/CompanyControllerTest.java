package com.api.retroden.controller;

import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        CompanyRequest request = new CompanyRequest(1L, "HP", 1L, List.of("Software Enginner"));
        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Company created"));
    }

    @Test
    void testGetById() throws Exception {
        CompanyResponse response = new CompanyResponse();
        Mockito.when(companyService.findById(Mockito.anyLong())).thenReturn(response);

        mockMvc.perform(get("/company/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<CompanyResponse> responses = List.of(new CompanyResponse());
        Mockito.when(companyService.findAll()).thenReturn(responses);
        mockMvc.perform(get("/company"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        CompanyRequest request = new CompanyRequest(1L, "HP", 1L, List.of("Software Enginner"));
        CompanyResponse updated = new CompanyResponse();
        Mockito.when(companyService.findById(Mockito.anyLong())).thenReturn(updated);

        mockMvc.perform(put("/company")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/company?id=1"))
                .andExpect(status().isOk());
    }
}
