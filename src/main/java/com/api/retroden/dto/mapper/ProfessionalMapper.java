package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.ProfessionalRequest;
import com.api.retroden.dto.response.ProfessionalResponse;
import com.api.retroden.model.Professional;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class ProfessionalMapper {
    public Professional toProfessional(ProfessionalRequest professionalRequest) {
        return Professional.builder()
                .idProfessional(professionalRequest.id())
                .firstName(professionalRequest.firstName())
                .lastName(professionalRequest.lastName())
                .email(professionalRequest.email())
                .age(professionalRequest.age())
                .location(professionalRequest.location())
                .availability(professionalRequest.availability())
                .build();

    }

    public ProfessionalResponse toProfessionalResponse(Professional professional) {
        return ProfessionalResponse.builder()
                .firstName(professional.getFirstName())
                .lastName(professional.getLastName())
                .email(professional.getEmail())
                .age(professional.getAge())
                .location(professional.getLocation())
                .availability(professional.getAvailability())
                .build();
    }
}
