package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Structure;
import com.application.urgence.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureServiceImpl implements StructureService {
    @Autowired
    StructureRepository structureRepository;
    @Override
    public Structure creer(Structure structure) {
        return structureRepository.save(structure);
    }

    @Override
    public List<Structure> liste() {
        return structureRepository.findAll();
    }

    @Override
    public Structure modifier(Structure structure, Long id) {
        Structure structure1 = structureRepository.findById(id).get();
        structure1.setNom(structure.getNom());
        structure1.setLocalite(structure.getLocalite());
        structure1.setNumero(structure.getNumero());
        return structureRepository.save(structure1);
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }
}
