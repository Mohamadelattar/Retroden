package com.api.retroden.service;

import com.api.retroden.dto.mapper.ProfessionalMapper;
import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.model.*;
import com.api.retroden.repository.CVRepository;
import com.api.retroden.repository.CertificationRepository;
import com.api.retroden.repository.ProfessionelRepository;
import com.api.retroden.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProfessionelServiceTest {
    @Mock
    private ProfessionelRepository professionelRepository;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CertificationRepository certificationRepository;

    @Mock
    private CVRepository cvRepository;

    @Mock
    private ProfessionalMapper professionalMapper;

    @InjectMocks
    private ProfessionalService professionalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){

        ProfessionelRequest request = new ProfessionelRequest(1L,
                                                        "firstName",
                                                        "lastName",
                                                            "email@email.com",
                                                                20,
                                                            "Agadir",Availability.FULL_TIME,List.of("JAVA"),1L,List.of("JAVA17"));
        Skill skill = mock(Skill.class);
        CV cv = mock(CV.class);
        Certification certification = new Certification();
        Professionel professionel = new Professionel();
        Professionel savedProfessionel = new Professionel();
        ProfessionelResponse response = new ProfessionelResponse();




        // Mapping from request to entity
        when(professionalMapper.toProfessionel(request)).thenReturn(professionel);

        // Mocking cv - skills - certification repositories
        when(cvRepository.findById(1L)).thenReturn(Optional.of(cv));
        when(skillRepository.findBySkillName("JAVA")).thenReturn(skill);
        when(certificationRepository.findByName("JAVA17")).thenReturn(certification);

        // Saving and mapping back to response
        when(professionelRepository.save(professionel)).thenReturn(savedProfessionel);
        when(professionalMapper.toProfessionelResponse(savedProfessionel)).thenReturn(response);

        // Testing create method
        ProfessionelResponse result = professionalService.create(request);

        assertNotNull(result);
        assertEquals(response,result);
        verify(professionalMapper).toProfessionelResponse(savedProfessionel);



    }
}
