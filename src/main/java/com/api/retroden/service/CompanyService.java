package com.api.retroden.service;

import com.api.retroden.model.Company;
import com.api.retroden.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id){
        return companyRepository.findById(id);
    }

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
