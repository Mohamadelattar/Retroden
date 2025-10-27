package com.api.retroden.service;

import com.api.retroden.dto.mapper.CvMapper;
import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.model.Professional;
import com.api.retroden.repository.CVRepository;
import com.api.retroden.repository.ProfessionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CVService {
    private final CVRepository cvRepository;
    private final CvMapper cvMapper;
    private final ProfessionalRepository professionelRepository;
    public CVService(CVRepository cvRepository, CvMapper cvMapper, ProfessionalRepository professionelRepository) {
        this.cvRepository = cvRepository;
        this.cvMapper = cvMapper;
        this.professionelRepository = professionelRepository;
    }

    public List<CVResponse> findAll() {
        return cvRepository.findAll()
                .stream()
                .map(cvMapper::toCvResponse)
                .toList();

    }

    public CVResponse findById(Long id) {
        return   cvRepository.findById(id)
                .map(cvMapper::toCvResponse)
                .orElseThrow(() -> new EntityNotFoundException("CV not found with id: " + id));
    }
    public CVResponse create(CvRequest cvRequest) {
        var cv = cvMapper.toCV(cvRequest);
        cv.setProfessional(findCvProfessionelById(cvRequest.id()));
        var savedCv = cvRepository.save(cv);
        return cvMapper.toCvResponse(savedCv);
    }

    public CVResponse update(CvRequest cvRequest) {
        return cvMapper.toCvResponse( cvRepository.findById(cvRequest.id())
                .map(existingCV -> {
                    Optional.ofNullable(cvRequest.name()).ifPresent(existingCV::setName);
                    Optional.ofNullable(cvRequest.data()).ifPresent(existingCV::setData);
                    Optional.ofNullable(findCvProfessionelById(cvRequest.id())).ifPresent(existingCV::setProfessional);
                    return cvRepository.save(existingCV);
                }).orElseThrow(() -> new RuntimeException("CV not found with id: " + cvRequest.id())));
    }

    public void delete(Long id) {
        cvRepository.deleteById(id);
    }
    public Professional findCvProfessionelById(Long id) {
        return professionelRepository.findById(id).orElseThrow(() -> new RuntimeException("Professionel not found with id: " + id));
    }

}
