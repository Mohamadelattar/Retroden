package com.api.retroden.repository;

import com.api.retroden.model.Company;
import com.api.retroden.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {
    public Company findByName(String name);

    public  Industry findIndustryById(long id);
}
