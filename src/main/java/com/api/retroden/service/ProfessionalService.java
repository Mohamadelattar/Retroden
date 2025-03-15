package com.api.retroden.service;

import com.api.retroden.dto.mapper.ProfessionalMapper;
import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.model.CV;
import com.api.retroden.model.Certification;
import com.api.retroden.model.Professionel;
import com.api.retroden.model.Skill;
import com.api.retroden.repository.CVRepository;
import com.api.retroden.repository.CertificationRepository;
import com.api.retroden.repository.ProfessionelRepository;
import com.api.retroden.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {
    private final ProfessionelRepository professionalRepository;
    private final SkillRepository skillRepository;
    private final CertificationRepository certificationRepository;
    private final CVRepository cvRepository;
    private final ProfessionalMapper professionalMapper;


    public ProfessionalService(ProfessionelRepository professionalRepository, SkillRepository skillRepository, CertificationRepository certificationRepository, CVRepository cvRepository, ProfessionalMapper professionalMapper) {
        this.professionalRepository = professionalRepository;
        this.skillRepository = skillRepository;
        this.certificationRepository = certificationRepository;
        this.cvRepository = cvRepository;
        this.professionalMapper = professionalMapper;
    }

    public List<ProfessionelResponse> findAll() {
        return professionalRepository.findAll()
                .stream()
                .map(professionalMapper::toProfessionelResponse)
                .toList();
    }
    public ProfessionelResponse findById(Long id) {
        return  professionalRepository.findById(id)
                .map(professionalMapper::toProfessionelResponse)
                .orElseThrow(() -> new RuntimeException("Professional not found with id" + id));
    }

    public ProfessionelResponse create(ProfessionelRequest professionalRequest) {
        var professional = professionalMapper.toProfessionel(professionalRequest);
        professional.setSkills(findProfessionalSkills(professionalRequest.skills()));
        professional.setCertifications(findProfessionalCertifications(professionalRequest.certefications()));
        professional.setCv(findProfessionalCVById(professionalRequest.idCv()));
        var savedProfessional = professionalRepository.save(professional);
        return professionalMapper.toProfessionelResponse(savedProfessional);
    }

    public ProfessionelResponse update(ProfessionelRequest professionalRequest) {
        return professionalMapper.toProfessionelResponse( professionalRepository.findById(professionalRequest.id())
                .map(existingProfessional -> {
                    Optional.ofNullable(professionalRequest.firstName()).ifPresent(existingProfessional::setFirstName);
                    Optional.ofNullable(professionalRequest.lastName()).ifPresent(existingProfessional::setLastName);
                    Optional.ofNullable(professionalRequest.email()).ifPresent(existingProfessional::setEmail);
                    Optional.of(professionalRequest.age()).ifPresent(existingProfessional::setAge);
                    Optional.ofNullable(professionalRequest.availability()).ifPresent(existingProfessional::setAvailability);
                    Optional.ofNullable(findProfessionalCVById(professionalRequest.idCv())).ifPresent(existingProfessional::setCv);
                    Optional.ofNullable(findProfessionalCertifications(professionalRequest.certefications())).ifPresent(existingProfessional::setCertifications);
                    Optional.ofNullable(professionalRequest.location()).ifPresent(existingProfessional::setLocation);
                    Optional.ofNullable(findProfessionalSkills(professionalRequest.skills())).ifPresent(existingProfessional::setSkills);
                    return professionalRepository.save(existingProfessional);
                })
                .orElseThrow(() -> new RuntimeException("Professional not found with id " + professionalRequest.id())));
    }
    public void delete(Long id) {
        professionalRepository.deleteById(id);
    }

    public List<Skill> findProfessionalSkills(List<String> skills) {
        return skills.stream().map(skillRepository::findBySkillName).toList();
    }
    public List<Certification> findProfessionalCertifications(List<String> certifications) {
        return certifications.stream().map(certificationRepository::findByName).toList();
    }
    public CV findProfessionalCVById(Long id) {
        return cvRepository.findById(id).orElseThrow(() -> new RuntimeException("CV not found with id " + id));
    }
}
