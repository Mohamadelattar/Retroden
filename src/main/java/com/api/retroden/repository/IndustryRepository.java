package com.api.retroden.repository;

import com.api.retroden.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Long, Industry> {
}
