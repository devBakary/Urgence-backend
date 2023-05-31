package com.application.urgence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idforum")
    private Forum forum;

}
