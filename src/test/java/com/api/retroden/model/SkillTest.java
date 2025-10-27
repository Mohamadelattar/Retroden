package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class SkillTest {
    private final Long idSkill = 1L;
    private final String skillName = "Skill";
    private final Professional professional = mock(Professional.class);

    @Test
    void shouldCreateSkillUsingConstructor() {
        Skill skill = new Skill(idSkill,skillName, professional);
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professional,skill.getProfessional());
    }

    @Test
    void shouldCreateSkillUsingBuilder() {
        Skill skill = Skill.builder()
                .idSkill(idSkill)
                .skillName(skillName)
                .professional(professional)
                .build();
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professional,skill.getProfessional());

    }

    @Test
    void shouldCreateSkillUsingSetter() {
        Skill skill = new Skill();
        skill.setIdSkill(idSkill);
        skill.setSkillName(skillName);
        skill.setProfessional(professional);
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professional,skill.getProfessional());
    }
}
