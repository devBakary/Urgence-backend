package com.application.urgence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String localite;
    private String numero;
    private Boolean etat=true;
    private Date date = new Date();


    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;


    public Structure(Long idstruct) {
        this.id=idstruct;
    }
}
