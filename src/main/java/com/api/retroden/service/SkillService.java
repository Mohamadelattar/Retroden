package com.api.retroden.service;

import com.api.retroden.dto.mapper.SkillMapper;
import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.model.Professionel;
import com.api.retroden.model.Skill;
import com.api.retroden.repository.ProfessionelRepository;
import com.api.retroden.repository.SkillRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final ProfessionelRepository professionelRepository;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper, ProfessionelRepository professionelRepository) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
        this.professionelRepository = professionelRepository;
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
    public SkillResponse create (SkillRequest skillRequest){
        var skill = skillMapper.toSkill(skillRequest);
        var savedSkill = skillRepository.save(skill);
        return skillMapper.toSkillResponse(savedSkill);
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

    public Professionel findProfessionnel(Long id){
        return professionelRepository.findById(id).orElseThrow(() -> new RuntimeException("Professionel not found with id " + id));
    }
}
