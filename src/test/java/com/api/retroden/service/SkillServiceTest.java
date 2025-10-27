package com.api.retroden.service;

import com.api.retroden.dto.mapper.SkillMapper;
import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.model.Skill;
import com.api.retroden.repository.ProfessionalRepository;
import com.api.retroden.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillMapper skillMapper;

    @Mock
    private ProfessionalRepository professionelRepository;

    @InjectMocks
    private SkillService skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        SkillRequest request = new SkillRequest(1L, "JAVA");
        Skill skill = new Skill();
        Skill savedSkill = new Skill();
        SkillResponse response = new SkillResponse();

        when(skillMapper.toSkill(request)).thenReturn(skill);
        when(skillRepository.save(skill)).thenReturn(savedSkill);
        when(skillMapper.toSkillResponse(savedSkill)).thenReturn(response);

        SkillResponse result = skillService.create(request);

        assertNotNull(result);
        assertEquals(response,result);
        assertEquals(response.getSkillName(), result.getSkillName());
    }

    @Test
    void testUpdate(){
        SkillRequest request = new SkillRequest(1L, "JAVA");
        Skill existingSkill = new Skill();
        Skill updatedSkill = new Skill();
        SkillResponse response = new SkillResponse();

        when(skillRepository.findById(1L)).thenReturn(Optional.of(existingSkill));
        when(skillRepository.save(existingSkill)).thenReturn(updatedSkill);
        when(skillMapper.toSkillResponse(updatedSkill)).thenReturn(response);

        SkillResponse result = skillService.update(request);

        assertNotNull(result);
        assertEquals(response, result);

    }

    @Test
    void testFindById(){
        Long id = 1L;
        Skill skill = new Skill();
        SkillResponse response = new SkillResponse();

        when(skillRepository.findById(id)).thenReturn(Optional.of(skill));
        when(skillMapper.toSkillResponse(skill)).thenReturn(response);

        SkillResponse result = skillService.findById(id);

        assertNotNull(result);
        assertEquals(response,result);
    }

    @Test
    void testDelete(){
        Long id = 5L;
        skillRepository.deleteById(id);
        verify(skillRepository).deleteById(id);
    }
}
