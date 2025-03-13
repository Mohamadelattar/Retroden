package com.api.retroden.service;

import com.api.retroden.dto.mapper.CompanyMapper;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.model.Company;
import com.api.retroden.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyResponse> findAll(){
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toCompanyResponse)
                .toList();
    }

    public CompanyResponse findById(Long id){
        return companyRepository.findById(id)
                .map(companyMapper::toCompanyResponse)
                .orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }
    // Complete Company Service
    public Company create(Company company){
        return companyRepository.save(company);
    }

    public Company update(Long id, Company company){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company existingCompany = companyOptional.get();
            existingCompany.setJobs(company.getJobs());
            existingCompany.setName(company.getName());
            existingCompany.setIndustry(company.getIndustry());
            return companyRepository.save(existingCompany);
        } else {
            throw new RuntimeException("Company not found with id : " + id);
        }
    }

    public void delete(Long id){
        companyRepository.deleteById(id);
    }
}
