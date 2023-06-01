package com.application.urgence.security.services;

import com.application.urgence.models.Structure;


import java.util.List;

public interface StructureService {
    Structure creer(Structure structure);

    List<Structure> liste();

    List<Structure> liste1();

    Object modifier(Structure structure, Long id);

    String supprimer(Long id);

    Structure modifierEtat(Long id);

    Structure modifierEtats(Long id);
}
