package com.application.urgence.security.services;

import com.application.urgence.models.Gestes;

import java.util.List;

public interface GesteService {

    Gestes creer(Gestes geste);

    List<Gestes> afficher();

    String supprimer(Long id);
}
