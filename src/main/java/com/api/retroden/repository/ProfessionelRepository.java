package com.api.retroden.repository;

import com.api.retroden.model.Professionel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionelRepository extends JpaRepository<Professionel, Long> {
}
