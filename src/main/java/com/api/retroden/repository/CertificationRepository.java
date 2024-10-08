package com.api.retroden.repository;

import com.api.retroden.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Long, Certification> {
}
