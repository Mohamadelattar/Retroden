package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "industry")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
