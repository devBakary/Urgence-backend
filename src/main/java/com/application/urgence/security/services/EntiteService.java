package com.application.urgence.security.services;

import com.application.urgence.models.Entite;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EntiteService {

    String creer(Entite entite);

    List<Entite> liste();

    Entite modifier(Entite entite, Long id);

    String supprimer(Long id);
}
