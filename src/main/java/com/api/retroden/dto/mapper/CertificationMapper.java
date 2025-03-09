package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring",uses={ProfessionalMapper.class})
public class CertificationMapper {
    public Certification toCertification(CertificationRequest certificationRequest) {
        return Certification.builder()
                .idCertification(certificationRequest.id())
                .name(certificationRequest.name())
                .data(certificationRequest.data())
                .build();
    }

    public CertificationResponse toCertificationResponse(Certification certification) {
        return CertificationResponse.builder()
                .name(certification.getName())
                .build();
    }
}
