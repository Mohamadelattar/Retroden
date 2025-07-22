package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "professionel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Professionel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProfessionel;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "location")
    private String location;

    @Column(name = "availability")
    private Availability availability;

    @OneToMany(mappedBy = "professionel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills;

    @OneToOne(mappedBy = "professionel", cascade = CascadeType.ALL)
    private CV cv;

    @OneToMany(mappedBy = "professionel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certification> certifications;

}
