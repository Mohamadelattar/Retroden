package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idJob;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
}
