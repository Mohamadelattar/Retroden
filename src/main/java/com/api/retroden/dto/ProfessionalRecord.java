package com.api.retroden.dto;

import com.api.retroden.model.Availability;

import java.util.List;

public record ProfessionalRecord(
        String firstName,
        String lastName,
        Availability availability,
        List<String> skills,
        List<String> certefications)
{

}
