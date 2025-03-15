package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.model.Skill;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class SkillMapper {
    public Skill toSkill(SkillRequest skillRequest) {
        return  Skill.builder()
                .idSkill(skillRequest.id())
                .skillName(skillRequest.name())
                .build();
    }
    public SkillResponse toSkillResponse(Skill skill) {
        return SkillResponse.builder()
                .skillName(skill.getSkillName())
                .build();
    }
}
