package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Avis;


import java.util.List;

public interface AvisService {
    Avis creer(Avis avis);

    List<Avis> liste();

    Avis modifier(Avis avis, Long id);

    String supprimer(Long id);
}
