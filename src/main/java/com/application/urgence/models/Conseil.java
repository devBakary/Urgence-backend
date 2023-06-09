package com.application.urgence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conseil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private String libelle;
    private Boolean etat=true;
    @ManyToOne
    @JoinColumn(name = "idstructure")
    private Structure structure;

}
