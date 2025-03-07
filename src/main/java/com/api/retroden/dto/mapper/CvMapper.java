package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.model.CV;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class CvMapper {
    public CV toCV(CvRequest cvRequest) {
        return CV.builder()
                .idCV(cvRequest.id())
                .name(cvRequest.name())
                .data(cvRequest.data())
                .build();
    }

    public CVResponse toCvResponse(CV cv) {
        return CVResponse.builder()
                .name(cv.getName())
                .data(cv.getData())
                .build();
    }
}
