package com.application.urgence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likees {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private boolean isLike = false;
    private boolean isDislike = false;

    @JsonIgnore
    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "idcom")
    private Commentaire commentaire;
}
