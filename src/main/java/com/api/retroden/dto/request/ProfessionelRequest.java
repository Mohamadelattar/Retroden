package com.api.retroden.dto.request;

import com.api.retroden.model.Availability;

import java.util.List;

public record ProfessionelRequest(
        Long  id,
        String firstName,
        String lastName,
        String email,
        int age,
        String location,
        Availability availability,
        List<String> skills,
        List<String> certefications,
        Long idCv)
{

}
