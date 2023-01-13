package com.application.urgence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gestes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String description;
    @NotBlank
    private String img1;

    @Column(nullable = true)
    private String img2;
    @Column(nullable = true)
    private String img3;
    @Column(nullable = true)
    private String img4;


    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
}
