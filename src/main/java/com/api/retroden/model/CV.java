package com.api.retroden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cv")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CV {
    @Id
    private Long idCV;
    @Column(name = "name")
    private String  name;
    @Column(name = "data")
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "professional_id")
    private  Professionel  professional;
}
