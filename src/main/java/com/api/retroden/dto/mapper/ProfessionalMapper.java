package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.ProfessionalRequest;
import com.api.retroden.model.Professionel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProfessionalMapper {
    public Professionel toProfessionel(ProfessionalRequest professionalRequest) {
        return Professionel.builder()
                .idProfessionel(professionalRequest.id())
                .firstName(professionalRequest.firstName())
                .lastName(professionalRequest.lastName())
                .age(professionalRequest.age())
                .

    }
}
