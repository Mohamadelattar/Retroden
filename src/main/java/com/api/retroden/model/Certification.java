package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "certification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certification")
    private Long idCertification;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "professionel_id")
    private Professionel professionel;


}
