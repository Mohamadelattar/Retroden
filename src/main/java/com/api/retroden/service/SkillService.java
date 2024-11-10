package com.api.retroden.service;

import com.api.retroden.model.Skill;
import com.api.retroden.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    public Skill findById(Long id){
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if(skillOptional.isPresent()){
            return skillOptional.get();
        } else {
            throw new RuntimeException("Skill not found with id: " + id);
        }
    }
    public Skill create (Skill skill){
        return skillRepository.save(skill);
    }

    public Skill update (Long id, Skill skill){
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if(skillOptional.isPresent()){
            Skill existingSkill = skillOptional.get();
            existingSkill.setSkillName(skill.getSkillName());
            existingSkill.setProfessional(skill.getProfessional());
         return skillRepository.save(existingSkill);
        } else {
            throw new RuntimeException("Skill not found with id: " + id);
        }
    }
    public void delete (Long id){
        skillRepository.deleteById(id);
    }
}
