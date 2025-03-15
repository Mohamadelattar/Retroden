package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.model.Industry;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class IndustryMapper {
    public Industry toIndustry(IndustryRequest industryRequest) {
        return Industry.builder()
                .idIndustry(industryRequest.id())
                .name(industryRequest.name())
                .build();
    }

    public IndustryResponse toIndustryResponse(Industry industry) {
        return IndustryResponse.builder()
                .name(industry.getName())
                .build();
    }
}
