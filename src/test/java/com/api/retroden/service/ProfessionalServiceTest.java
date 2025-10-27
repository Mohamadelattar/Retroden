package com.api.retroden.service;

import com.api.retroden.dto.mapper.ProfessionalMapper;
import com.api.retroden.dto.request.ProfessionalRequest;
import com.api.retroden.dto.response.ProfessionalResponse;
import com.api.retroden.model.*;
import com.api.retroden.repository.CVRepository;
import com.api.retroden.repository.CertificationRepository;
import com.api.retroden.repository.ProfessionalRepository;
import com.api.retroden.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfessionalServiceTest {
    @Mock
    private ProfessionalRepository professionelRepository;

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

        ProfessionalRequest request = new ProfessionalRequest(1L,
                                                        "firstName",
                                                        "lastName",
                                                            "email@email.com",
                                                                20,
                                                            "Agadir",Availability.FULL_TIME,List.of("JAVA"),1L,List.of("JAVA17"));
        Skill skill = mock(Skill.class);
        CV cv = mock(CV.class);
        Certification certification = new Certification();
        Professional professional = new Professional();
        Professional savedProfessional = new Professional();
        ProfessionalResponse response = new ProfessionalResponse();




        // Mapping from request to entity
        when(professionalMapper.toProfessional(request)).thenReturn(professional);

        // Mocking cv - skills - certification repositories
        when(cvRepository.findById(1L)).thenReturn(Optional.of(cv));
        when(skillRepository.findBySkillName("JAVA")).thenReturn(skill);
        when(certificationRepository.findByName("JAVA17")).thenReturn(certification);

        // Saving and mapping back to response
        when(professionelRepository.save(professional)).thenReturn(savedProfessional);
        when(professionalMapper.toProfessionalResponse(savedProfessional)).thenReturn(response);

        // Testing create method
        ProfessionalResponse result = professionalService.create(request);

        assertNotNull(result);
        assertEquals(response,result);
        verify(professionalMapper).toProfessionalResponse(savedProfessional);

    }

    @Test
    void testUpdate(){
        ProfessionalRequest request = new ProfessionalRequest(1L,
                "firstName",
                "lastName",
                "email@email.com",
                20,
                "Agadir",Availability.FULL_TIME,List.of("JAVA"),1L,List.of("JAVA17"));
        Professional existingProfessional = new Professional();
        Professional updatedProfessional = new Professional();
        ProfessionalResponse response = new ProfessionalResponse();
        CV savedCV = new CV();
        Certification savedCertification = new Certification();
        Skill savedSkill = new Skill();


        // Mocking repositories and mapper behaviour
        when(professionelRepository.findById(1L)).thenReturn(Optional.of(existingProfessional));
        when(cvRepository.findById(1L)).thenReturn(Optional.of(savedCV));
        when(skillRepository.findBySkillName("JAVA")).thenReturn(savedSkill);
        when(certificationRepository.findByName("JAVA17")).thenReturn(savedCertification);
        when(professionelRepository.save(existingProfessional)).thenReturn(updatedProfessional);
        when(professionalMapper.toProfessionalResponse(updatedProfessional)).thenReturn(response);

        // Testing update method
        ProfessionalResponse result = professionalService.update(request);
        assertNotNull(result);
        assertEquals(response,result);
        assertEquals(response.getAge(),result.getAge());
        assertEquals(response.getEmail(),result.getEmail());
        assertEquals(response.getFirstName(),result.getFirstName());
        assertEquals(response.getLastName(),result.getLastName());
    }

    @Test
    void testDelete(){
        Long id = 5L;
        professionelRepository.deleteById(id);
        verify(professionelRepository).deleteById(id);
    }

    @Test
    void testFindById(){
        Long id = 1L;
        Professional professional = new Professional();
        ProfessionalResponse response = new ProfessionalResponse();

        when(professionelRepository.findById(id)).thenReturn(Optional.of(professional));
        when(professionalMapper.toProfessionalResponse(professional)).thenReturn(response);

        ProfessionalResponse result = professionalService.findById(id);

        assertNotNull(result);
        assertEquals(response,result);
        assertEquals(response.getAge(),result.getAge());
        assertEquals(response.getEmail(),result.getEmail());
        assertEquals(response.getFirstName(),result.getFirstName());
        assertEquals(response.getLastName(),result.getLastName());

    }

    @Test
    void testFindByIdNotFound(){
        Long id = 99L;
        when(professionelRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> professionalService.findById(id));
        assertEquals(exception.getMessage(),"Professionel not found with id" + id);
    }

    @Test
    void testFindAll(){
        Professional professional = new Professional();
        ProfessionalResponse singleResponse = new ProfessionalResponse();
        List<Professional> professionals = List.of(professional);
        List<ProfessionalResponse> responses = List.of(singleResponse);

        when(professionelRepository.findAll()).thenReturn(professionals);
        when(professionalMapper.toProfessionalResponse(professional)).thenReturn(singleResponse);

        List<ProfessionalResponse> result = professionalService.findAll();

        assertNotNull(result);
        assertEquals(responses, result);
    }

}
