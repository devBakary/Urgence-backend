package com.application.urgence.security.services;

import com.application.urgence.models.SignalerDanger;

public interface SignaleService {

    SignalerDanger ajouter(SignalerDanger signalerDanger);

    String supprimer(Long id);
}
