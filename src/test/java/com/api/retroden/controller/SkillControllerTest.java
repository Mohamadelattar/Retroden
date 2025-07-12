package com.api.retroden.controller;

import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.service.SkillService;
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

@WebMvcTest(controllers = SkillController.class)
public class SkillControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        SkillRequest request = new SkillRequest(1L, "Java");
        mockMvc.perform(post("/skills")
                        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Skill created"));
    }

    @Test
    void testGetById() throws Exception {
        SkillResponse response = new SkillResponse();
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(response);
        mockMvc.perform(get("/skills/{id}",1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<SkillResponse> responses = List.of(new SkillResponse());
        Mockito.when(service.findAll()).thenReturn(responses);
        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        SkillRequest request = new SkillRequest(1L, "Java");
        SkillResponse response = new SkillResponse();
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(response);
        mockMvc.perform(put("/skills")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/skills?id=1"))
                .andExpect(status().isOk());
    }

}
