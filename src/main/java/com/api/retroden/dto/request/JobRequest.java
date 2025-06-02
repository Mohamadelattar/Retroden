package com.api.retroden.dto.request;

public record JobRequest(Long id,
                         String title,
                         String description,
                         Long companyId) {
}
