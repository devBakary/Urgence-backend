package com.application.urgence.models;

import com.application.urgence.Autres.GestionDate;
import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Lob
    private String contenu;
    private String image;
    private String numero;
    @Embedded
    private GestionDate gestionDate;
    @Enumerated(EnumType.ORDINAL)
    private Statut statutPublication;
    @Enumerated(EnumType.ORDINAL)
    private EtatValidation validationPublication;
    private LocalDateTime datePublication;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;

}
