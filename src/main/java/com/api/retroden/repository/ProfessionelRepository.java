package com.api.retroden.repository;

import com.api.retroden.model.Professionel;
import com.api.retroden.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionelRepository extends JpaRepository<Professionel, Long> {

}
