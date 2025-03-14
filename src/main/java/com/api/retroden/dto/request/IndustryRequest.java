package com.api.retroden.dto.request;

import java.util.List;

public record IndustryRequest(Long id,
                              String name,
                              List<String> companies) {
}
