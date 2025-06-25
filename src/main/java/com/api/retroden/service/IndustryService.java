package com.api.retroden.service;

import com.api.retroden.dto.mapper.IndustryMapper;
import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.IndustryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryService {
    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;
    private final CompanyRepository companyRepository;
    public IndustryService(IndustryRepository industryRepository, IndustryMapper industryMapper, CompanyRepository companyRepository) {
        this.industryRepository = industryRepository;
        this.industryMapper = industryMapper;
        this.companyRepository = companyRepository;
    }

    public List<IndustryResponse> findAll() {
        return industryRepository.findAll()
                .stream()
                .map(industryMapper::toIndustryResponse)
                .toList();
    }
    public IndustryResponse findById(Long id) {
        return industryRepository.findById(id)
                .map(industryMapper::toIndustryResponse)
                .orElseThrow(() -> new EntityNotFoundException("Industry not found with id " + id));
    }
    public IndustryResponse create(IndustryRequest industryRequest) {
        var industry = industryMapper.toIndustry(industryRequest);
        industry.setCompanies(findCompaniesIndustry(industryRequest.companies()));
        var savedIndustry = industryRepository.save(industry);
        return industryMapper.toIndustryResponse(savedIndustry);
    }

    public IndustryResponse update(IndustryRequest industryRequest) {
        return industryMapper.toIndustryResponse(industryRepository.findById(industryRequest.id())
                .map(existingIndustry -> {
                    Optional.ofNullable(industryRequest.name()).ifPresent(existingIndustry::setName);
                    Optional.ofNullable(findCompaniesIndustry(industryRequest.companies())).ifPresent(existingIndustry::setCompanies);
                    return industryRepository.save(existingIndustry);
                }).orElseThrow(() -> new EntityNotFoundException("Industry not found with id " + industryRequest.id())));
    }
    public void delete(Long id) {
        industryRepository.deleteById(id);
    }

    private List<Company> findCompaniesIndustry(List<String> companies) {
        return companies.stream().map(companyRepository::findByName).toList();
    }

}
