package com.application.urgence.security.services;

import com.application.urgence.models.Fiche;

import java.util.List;

public interface FicheService {

    String creer(Fiche fiche);

    List<Fiche> liste();

    Fiche modifier(Fiche fiche, Long id);

    String supprimer(Long id);
}
