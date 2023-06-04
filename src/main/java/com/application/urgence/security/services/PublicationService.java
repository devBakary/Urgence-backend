package com.application.urgence.security.services;

import com.application.urgence.models.DemandeAide;
import com.application.urgence.models.Publication;

import java.util.List;

public interface PublicationService {
    Publication ajouterPublication(Publication publication);
    Publication modifierPublication(Publication publication);
    Publication recuperPublicationParId(Long id);
    List<Publication> listerPublication(String type);
    List<Publication> listerPublicationParValidation(String type);
    String supprimerActiverOuDesactiverPublication(Long id, String type);
    String validerDemandeAide(Long id, String type);
}
