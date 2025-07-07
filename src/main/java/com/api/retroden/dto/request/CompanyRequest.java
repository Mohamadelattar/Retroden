package com.api.retroden.dto.request;

import java.util.List;

public record CompanyRequest(Long id,
                             String name,
                             Long industryID,
                             List<String> jobs) {

}
