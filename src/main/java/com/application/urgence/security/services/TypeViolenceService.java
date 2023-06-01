package com.application.urgence.security.services;

import com.application.urgence.models.Type_violence;

import java.util.List;

public interface TypeViolenceService {


    Type_violence creer(Type_violence typeViolence);

    List<Type_violence> liste();

   Type_violence modifier(Type_violence type_violence, Long id);

    Type_violence modifierEtat(Long id);

    Type_violence modifierEtats(Long id);

    String supprimer(Long id);
}
