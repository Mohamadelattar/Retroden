package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.model.Professionel;
import com.api.retroden.model.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class SkillMapperTest {
    private SkillMapper skillMapper;
    private final Long idSkill = 1L;
    private final String skillName = "Skill";
    private final Professionel professionel = mock(Professionel.class);

    @BeforeEach
    void setUp() {
        skillMapper = new SkillMapper();
    }
    @Test
    void testToSkill() {
        SkillRequest request = new SkillRequest(idSkill, skillName);
        Skill result = skillMapper.toSkill(request);
        assertNotNull(request);
        assertEquals(idSkill,result.getIdSkill());
        assertEquals(skillName,result.getSkillName());
    }
    @Test
    void testToSkillResponse() {
        Skill skill = Skill.builder()
                .idSkill(idSkill)
                .skillName(skillName)
                .build();
        SkillResponse response = skillMapper.toSkillResponse(skill);
        assertNotNull(response);
        assertEquals(skillName,response.getSkillName());
    }
}
