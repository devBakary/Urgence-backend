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
public class Type_violence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String numero;
    private String image;
    private String audio;
    private Boolean etat=true;
    private Date date = new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "type_violence_structure",
            joinColumns = @JoinColumn(name = "idtype_violence"),
            inverseJoinColumns = @JoinColumn(name = "idstructure"))
    private Set<Structure> struture = new HashSet<>();


    public Type_violence(Long idtype) {
        this.id=idtype;
    }
}
