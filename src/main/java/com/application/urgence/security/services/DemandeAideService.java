package com.application.urgence.security.services;

import com.application.urgence.models.DemandeAide;

import java.util.List;

public interface DemandeAideService {
    DemandeAide ajouterDemandeAide(DemandeAide demande);
    DemandeAide modifierDemandeAide(DemandeAide demande);
    DemandeAide recuperDemandeAideParId(Long id);
    List<DemandeAide> listerDemandeAide(String type);
    List<DemandeAide> listerDemandeAideParValidation(String type);
    String supprimerActiverOuDesactiverDemandeAide(Long id, String type);
    String validerDemandeAide(Long id, String type);
}
