package com.api.retroden.dto.request;

public record CvRequest(
        Long id,
        String name,
        byte[] data,
        Long professionalId
) {
}
