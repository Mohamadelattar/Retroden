package com.api.retroden.dto.request;

import com.api.retroden.model.Availability;

import java.util.List;

public record ProfessionalRequest(
        String firstName,
        String lastName,
        Availability availability,
        List<String> skills,
        List<String> certefications)
{

}
