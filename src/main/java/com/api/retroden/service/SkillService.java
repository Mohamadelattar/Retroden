package com.api.retroden.service;

import com.api.retroden.dto.mapper.SkillMapper;
import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.model.Skill;
import com.api.retroden.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }
    public List<SkillResponse> findAll(){
        return skillRepository.findAll()
                .stream()
                .map(skillMapper::toSkillResponse)
                .toList();
    }

    public SkillResponse findById(Long id){
        return  skillRepository.findById(id)
                .map(skillMapper::toSkillResponse)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }
    public Skill create (Skill skill){
        return skillRepository.save(skill);
    }

    public SkillResponse update (SkillRequest skillRequest){
        return skillMapper.toSkillResponse(
                skillRepository.findById(skillRequest.id())
                        .map(existingSkill -> {
                            Optional.ofNullable(skillRequest.name()).ifPresent(existingSkill::setSkillName);
                            return skillRepository.save(existingSkill);
                        })
                        .orElseThrow(() -> new RuntimeException("Skill not found with id " + skillRequest.id())));


    }
    public void delete (Long id){
        skillRepository.deleteById(id);
    }
}
