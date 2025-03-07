package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "skill")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {


    @Id
    private Long idSkill;

    @Column(name = "skillName")
    private String skillName;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professionel professional;

}
