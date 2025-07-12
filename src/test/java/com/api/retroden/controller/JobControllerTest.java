package com.api.retroden.controller;

import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        JobRequest request = new JobRequest(1L, "Job", "Job description", 1L);
        mockMvc.perform(post("/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        JobResponse jobResponse = new JobResponse();
        Mockito.when(jobService.findById(Mockito.anyLong())).thenReturn(jobResponse);
        mockMvc.perform(get("/job/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<JobResponse> responses = List.of(new JobResponse());
        Mockito.when(jobService.findAll()).thenReturn(responses);
        mockMvc.perform(get("/job"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        JobRequest request = new JobRequest(1L, "Job", "Job description", 1L);
        JobResponse jobResponse = new JobResponse();
        Mockito.when(jobService.findById(Mockito.anyLong())).thenReturn(jobResponse);
        mockMvc.perform(put("/job")
        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/job?id=1"))
                .andExpect(status().isOk());
    }

}
