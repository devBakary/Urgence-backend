package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Structure;


import java.util.List;

public interface StructureService {
    Structure creer(Structure structure);

    List<Structure> liste();

    Structure modifier(Structure structure, Long id);

    String supprimer(Long id);
}
