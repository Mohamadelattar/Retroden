package com.api.retroden.service;

import com.api.retroden.model.CV;
import com.api.retroden.repository.CVRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CVService {
    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public List<CV> findAll() {
        return cvRepository.findAll();
    }

    public CV findById(Long id) {
        return   cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV not found with id: " + id));
    }
    public CV create(CV cv) {
        return cvRepository.save(cv);
    }

    public CV update(Long id,CV cv) {
        return cvRepository.findById(id)
                .map(existingCV -> {
                    Optional.ofNullable(cv.getName()).ifPresent(existingCV::setName);
                    Optional.ofNullable(cv.getData()).ifPresent(existingCV::setData);
                    Optional.ofNullable(cv.getProfessional()).ifPresent(existingCV::setProfessional);
                    return cvRepository.save(existingCV);
                }).orElseThrow(() -> new RuntimeException("CV not found with id: " + id));
    }

    public void delete(Long id) {
        cvRepository.deleteById(id);
    }

}
