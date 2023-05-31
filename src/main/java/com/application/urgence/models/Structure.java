package com.application.urgence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;
    private String localite;
    @Column(unique = true)
    private String numero;
    private Boolean etat=true;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;


    public Structure(Long idstruct) {
        this.id=idstruct;
    }
}
