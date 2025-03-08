package com.api.retroden.service;

import com.api.retroden.dto.mapper.CertificationMapper;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import com.api.retroden.repository.CertificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final CertificationMapper certificationMapper;

    public CertificationService(CertificationRepository certificationRepository, CertificationMapper certificationMapper) {
        this.certificationRepository = certificationRepository;
        this.certificationMapper = certificationMapper;
    }
    public Certification create(Certification certification){
        return certificationRepository.save(certification);
    }
    public List<Certification> findAll(){
        return certificationRepository.findAll();
    }

    public CertificationResponse findById(Long id){
        return certificationRepository.findById(id)
                .map(certificationMapper::toCertificationResponse)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with id " + id));
    }
    public Certification update(Long id, Certification certification){
        Optional<Certification> certifOptional = certificationRepository.findById(id);
        if(certifOptional.isPresent()){
            Certification existingCertification = certifOptional.get();
            existingCertification.setName(certification.getName());
            existingCertification.setData(certification.getData());
            return certificationRepository.save(existingCertification);
        } else {
            throw new RuntimeException("Certification not found with id: " + id);
        }
    }
    public void delete(Long id){
        certificationRepository.deleteById(id);
    }


}
