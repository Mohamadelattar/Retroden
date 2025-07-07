package com.api.retroden.dto.request;

public record CertificationRequest(Long id,
                                   String name,
                                   byte[] data,
                                   Long professionalId)
{


}
