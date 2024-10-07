package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill")
public class Skill {


    @Id
    private Long idSkill;

    @Column(name = "skillName")
    private String skillName;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professionel professional;

}
