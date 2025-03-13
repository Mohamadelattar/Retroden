package com.api.retroden.service;

import com.api.retroden.dto.mapper.CompanyMapper;
import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import com.api.retroden.model.Job;
import com.api.retroden.repository.CompanyRepository;
import com.api.retroden.repository.IndustryRepository;
import com.api.retroden.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final IndustryRepository industryRepository;
    private final JobRepository jobRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, IndustryRepository industryRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.industryRepository = industryRepository;
        this.jobRepository = jobRepository;
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

    public CompanyResponse create(CompanyRequest companyRequest){
        var company = companyMapper.toCompany(companyRequest);
        company.setIndustry(findCompanyIndustry(companyRequest.industryID()));
        company.setJobs(findCompanyJob(companyRequest.jobs()));
        var savedCompany = companyRepository.save(company);
        return companyMapper.toCompanyResponse(savedCompany);
    }

    public CompanyResponse update(CompanyRequest companyRequest){
        return companyMapper.toCompanyResponse(
                companyRepository.findById(companyRequest.id())
                        .map(existingCompany -> {
                            Optional.ofNullable(companyRequest.name()).ifPresent(existingCompany::setName);
                            Optional.ofNullable(findCompanyIndustry(companyRequest.industryID())).ifPresent(existingCompany::setIndustry);
                            Optional.ofNullable(findCompanyJob(companyRequest.jobs())).ifPresent(existingCompany::setJobs);
                            return companyRepository.save(existingCompany);
                        })
                        .orElseThrow(() -> new RuntimeException("Company not found with id " + companyRequest.id()))
        );
    }

    public void delete(Long id){
        companyRepository.deleteById(id);
    }

    public Industry findCompanyIndustry(Long id){
        return industryRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found with id : " + id));
    }
    public List<Job> findCompanyJob(List<String> jobs){
        return jobs.stream().map(jobRepository::findByTitle).toList();
    }

}
