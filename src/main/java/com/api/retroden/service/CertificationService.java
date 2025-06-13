package com.api.retroden.service;

import com.api.retroden.dto.mapper.CertificationMapper;
import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import com.api.retroden.model.Professionel;
import com.api.retroden.repository.CertificationRepository;
import com.api.retroden.repository.ProfessionelRepository;
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
    public CertificationResponse create(CertificationRequest certificationRequest){
        var certification = certificationMapper.toCertification(certificationRequest);
        certification.setProfessional(findCertificationProfessionelById(certificationRequest.professionalId()));
        var savedCertification = certificationRepository.save(certification);
        return certificationMapper.toCertificationResponse(savedCertification);
    }
    public List<CertificationResponse> findAll(){
        return  certificationRepository.findAll()
                .stream()
                .map(certificationMapper::toCertificationResponse)
                .toList();

    }

    public CertificationResponse findById(Long id){
        return certificationRepository.findById(id)
                .map(certificationMapper::toCertificationResponse)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with id " + id));
    }
    public CertificationResponse update(CertificationRequest certificationRequest){
        Optional<Certification> certifOptional = certificationRepository.findById(certificationRequest.id());
        if(certifOptional.isPresent()){
            Certification existingCertification = certifOptional.get();
            existingCertification.setName(certificationRequest.name());
            existingCertification.setData(certificationRequest.data());
            return certificationMapper.toCertificationResponse(certificationRepository.save(existingCertification));
        } else {
            throw new RuntimeException("Certification not found with id: " + certificationRequest.id());
        }
    }
    public void delete(Long id){
        certificationRepository.deleteById(id);
    }

    public Professionel findCertificationProfessionelById(Long id){
        return certificationRepository.findProfessionelByIdCertification(id);
    }


}
