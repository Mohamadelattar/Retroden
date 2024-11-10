package com.api.retroden.dto;

import java.util.List;

public record CompanyDTO(String name, Long industryID, List<String> jobs) {
}
