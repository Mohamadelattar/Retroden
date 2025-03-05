package com.api.retroden.dto.response;

import com.api.retroden.model.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessionelResponse {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String location;
    private Availability availability;
}
