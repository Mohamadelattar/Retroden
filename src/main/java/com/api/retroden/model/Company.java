package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompany;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "company" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Industry industry;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;
}
