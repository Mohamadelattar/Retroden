package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "industry")
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIndustry;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "company")
    private Company company;


}
