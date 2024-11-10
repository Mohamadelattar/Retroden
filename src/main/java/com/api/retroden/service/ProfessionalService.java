package com.api.retroden.service;

import com.api.retroden.model.Professionel;
import com.api.retroden.repository.ProfessionelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {
    private final ProfessionelRepository professionalRepository;

    public ProfessionalService(ProfessionelRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public List<Professionel> findAll() {
        return professionalRepository.findAll();
    }
    public Professionel findById(Long id) {
        return  professionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found with id" + id));
    }

    public Professionel create(Professionel professional) {
        return professionalRepository.save(professional);
    }

    public Professionel update(Long id,Professionel professional) {
        return professionalRepository.findById(id)
                .map(existingProfessional -> {
                    Optional.ofNullable(professional.getFirstName()).ifPresent(existingProfessional::setFirstName);
                    Optional.ofNullable(professional.getLastName()).ifPresent(existingProfessional::setLastName);
                    Optional.ofNullable(professional.getEmail()).ifPresent(existingProfessional::setEmail);
                    Optional.ofNullable(professional.getAge()).ifPresent(existingProfessional::setAge);
                    Optional.ofNullable(professional.getCv()).ifPresent(existingProfessional::setCv);
                    Optional.ofNullable(professional.getCertifications()).ifPresent(existingProfessional::setCertifications);
                    Optional.ofNullable(professional.getLocation()).ifPresent(existingProfessional::setLocation);
                    Optional.ofNullable(professional.getSkills()).ifPresent(existingProfessional::setSkills);
                    return professionalRepository.save(existingProfessional);
                })
                .orElseThrow(() -> new RuntimeException("Professional not found with id " + id));
    }
    public void delete(Long id) {
        professionalRepository.deleteById(id);
    }
}
