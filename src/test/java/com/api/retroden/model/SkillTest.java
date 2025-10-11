package com.api.retroden.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class SkillTest {
    private final Long idSkill = 1L;
    private final String skillName = "Skill";
    private final Professionel professionel = mock(Professionel.class);

    @Test
    void shouldCreateSkillUsingConstructor() {
        Skill skill = new Skill(idSkill,skillName,professionel);
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professionel,skill.getProfessionel());
    }

    @Test
    void shouldCreateSkillUsingBuilder() {
        Skill skill = Skill.builder()
                .idSkill(idSkill)
                .skillName(skillName)
                .professionel(professionel)
                .build();
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professionel,skill.getProfessionel());

    }

    @Test
    void shouldCreateSkillUsingSetter() {
        Skill skill = new Skill();
        skill.setIdSkill(idSkill);
        skill.setSkillName(skillName);
        skill.setProfessionel(professionel);
        assertEquals(idSkill,skill.getIdSkill());
        assertEquals(skillName,skill.getSkillName());
        assertEquals(professionel,skill.getProfessionel());
    }
}
