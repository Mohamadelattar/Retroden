package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public class CompanyMapper {
    public Company toCompany(CompanyRequest companyRequest) {
        return Company.builder()
                .idCompany(companyRequest.id())
                .name(companyRequest.name())
                .build();
    }

    public CompanyResponse toCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .name(company.getName())
                .build();
    }
}
