package com.api.retroden.repository;

import com.api.retroden.model.Certification;
import com.api.retroden.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    public Professional findProfessionelByIdCertification(long id);
    public Certification findByName(String name);
}
