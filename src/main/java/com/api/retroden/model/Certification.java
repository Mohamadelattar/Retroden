package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "certification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCertification;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professionel professional;


}
