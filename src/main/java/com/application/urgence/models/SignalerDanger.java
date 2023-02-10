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
public class SignalerDanger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private double longitude;
    private double latitude;


    @ManyToOne
    @JoinColumn(name = "entiteid")
    private Entite entite;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
}
