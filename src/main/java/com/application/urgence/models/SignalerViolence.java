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
public class SignalerViolence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private String message;
    private Boolean etat=true;
    @ManyToOne
    @JoinColumn(name = "idtype_violence")
    private Type_violence typeViolence;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
}
