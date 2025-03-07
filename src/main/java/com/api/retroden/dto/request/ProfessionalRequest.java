package com.api.retroden.dto.request;

import com.api.retroden.model.Availability;

import java.util.List;

public record ProfessionalRequest(
        Long  id,
        String firstName,
        String lastName,
        int age,
        String location,
        Availability availability,
        List<String> skills,
        List<String> certefications,
        int idCv)
{

}
