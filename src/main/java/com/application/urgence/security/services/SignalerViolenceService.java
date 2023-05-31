package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.SignalerViolence;

import java.util.List;

public interface SignalerViolenceService {
    SignalerViolence creer(SignalerViolence signalerViolence);

    List<SignalerViolence> liste();

    SignalerViolence modifier(SignalerViolence signalerViolence, Long id);

    String supprimer(Long id);
}
