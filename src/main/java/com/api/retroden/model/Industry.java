package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Company> companies;


}
