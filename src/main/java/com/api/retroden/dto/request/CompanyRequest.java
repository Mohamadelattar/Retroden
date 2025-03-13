package com.api.retroden.dto.request;

import com.api.retroden.model.Job;

import java.util.List;

public record CompanyRequest(Long id,String name, Long industryID, List<String> jobs) {
}
