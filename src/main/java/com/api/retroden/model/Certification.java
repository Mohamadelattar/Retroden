package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    private Long idCertification;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professionel professional;
}
