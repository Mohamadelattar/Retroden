package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import org.springframework.stereotype.Component;

@Component
public class CertificationMapper {
    public Certification toCertification(CertificationRequest certificationRequest) {
        return Certification.builder()
                .idCertification(certificationRequest.id())
                .name(certificationRequest.name())
                .data(certificationRequest.data())
                .build(); // Add professsionel data
    }

    public CertificationResponse toCertificationResponse(Certification certification) {
        return CertificationResponse.builder()
                .name(certification.getName())
                .build();
    }
}
