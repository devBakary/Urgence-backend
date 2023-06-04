package com.application.urgence.models;

import com.application.urgence.Autres.GestionDate;
import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Lob
    private String description;
    private String image;
    private String pieceIdentitite;
    private String numero;
    @Embedded
    private GestionDate gestionDate;
    @Enumerated(EnumType.ORDINAL)
    private Statut statutDemandeAide;
    @Enumerated(EnumType.ORDINAL)
    private EtatValidation validationDemandeAide;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;

}
