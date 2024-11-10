package com.api.retroden.service;

import com.api.retroden.model.Industry;
import com.api.retroden.repository.IndustryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryService {
    private final IndustryRepository industryRepository;

    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public List<Industry> findAll() {
        return industryRepository.findAll();
    }
    public Industry findById(Long id) {

        Optional<Industry> industryOptional = industryRepository.findById(id);
        if(industryOptional.isPresent()) {
            return industryOptional.get();
        } else {
            throw new RuntimeException("Industry not found with id: " + id);
        }
    }
    public Industry create(Industry industry) {
        return industryRepository.save(industry);
    }

    public Industry update(Long id,Industry industry) {
        Optional<Industry> industryOptional = industryRepository.findById(id);
        if(industryOptional.isPresent()) {
            Industry existingIndustry = industryOptional.get();
            existingIndustry.setName(industry.getName());
            existingIndustry.setCompany(industry.getCompany());
        return industryRepository.save(existingIndustry);
        } else {
            throw new RuntimeException("Industry not found with id: " + id);
        }
    }
    public void delete(Long id) {
        industryRepository.deleteById(id);
    }

}
