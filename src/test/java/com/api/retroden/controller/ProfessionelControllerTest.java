package com.api.retroden.controller;

import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.model.Availability;
import com.api.retroden.service.ProfessionalService;
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

@WebMvcTest(controllers = ProfessionelController.class)
public class ProfessionelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionalService professionalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        ProfessionelRequest request = new ProfessionelRequest(1L,
                "firstName",
                "lastName",
                "email@email.com",
                20,
                "Agadir", Availability.FULL_TIME, List.of("JAVA"),1L,List.of("JAVA17"));
        mockMvc.perform(post("/professionel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Professionel created"));
    }

    @Test
    void testGetById() throws Exception {
        ProfessionelResponse response = new ProfessionelResponse();
        Mockito.when(professionalService.findById(Mockito.anyLong())).thenReturn(response);

        mockMvc.perform(get("/professionel/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAll() throws Exception {
        List<ProfessionelResponse> responses = List.of(new ProfessionelResponse());
        Mockito.when(professionalService.findAll()).thenReturn(responses);
        mockMvc.perform(get("/professionel"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        ProfessionelRequest request = new ProfessionelRequest(1L,
                "firstName",
                "lastName",
                "email@email.com",
                20,
                "Agadir", Availability.FULL_TIME, List.of("JAVA"),1L,List.of("JAVA17"));
        ProfessionelResponse response = new ProfessionelResponse();
        Mockito.when(professionalService.findById(Mockito.anyLong())).thenReturn(response);

        mockMvc.perform(put("/professionel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/professionel?id=1"))
                .andExpect(status().isOk());
    }
}
