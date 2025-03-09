package com.api.retroden.dto.mapper;

import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.model.Professionel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProfessionalMapper {
    public Professionel toProfessionel(ProfessionelRequest professionelRequest) {
        return Professionel.builder()
                .idProfessionel(professionelRequest.id())
                .firstName(professionelRequest.firstName())
                .lastName(professionelRequest.lastName())
                .age(professionelRequest.age())
                .location(professionelRequest.location())
                .availability(professionelRequest.availability())
                .build();

    }

    public ProfessionelResponse toProfessionelResponse(Professionel professionel) {
        return ProfessionelResponse.builder()
                .firstName(professionel.getFirstName())
                .lastName(professionel.getLastName())
                .age(professionel.getAge())
                .location(professionel.getLocation())
                .build();
    }
}
