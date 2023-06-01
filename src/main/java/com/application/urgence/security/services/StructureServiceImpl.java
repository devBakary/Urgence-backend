package com.application.urgence.security.services;


import com.application.urgence.message.Message;
import com.application.urgence.models.Structure;
import com.application.urgence.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StructureServiceImpl implements StructureService {

    @Autowired
    StructureRepository structureRepository;




    @Override
    public Structure creer(Structure structure) {
        return structureRepository.save(structure);
    }

    //les structures non desactives
    @Override
    public List<Structure> liste() {
        List<Structure> structures = structureRepository.findAll();
        List<Structure> structuresActives = new ArrayList<>();

        for (Structure structure : structures) {
            if (structure.getEtat()) { // Vérifie si l'état est vrai (true)
                structuresActives.add(structure);
            }
        }

        return structuresActives;
    }

    //les structures  desactives
    @Override
    public List<Structure> liste1() {
        List<Structure> structures = structureRepository.findAll();
        List<Structure> structuresActives = new ArrayList<>();

        for (Structure structure : structures) {
            if (!structure.getEtat()) { // Vérifie si l'état est vrai (true)
                structuresActives.add(structure);
            }
        }

        return structuresActives;
    }


    @Override
    public Object modifier(Structure structure, Long id) {
        Structure structure1 = structureRepository.findById(id).get();
        // Vérification si le libellé existe déjà
        if (structureRepository.existsByNom(structure.getNom())) {
            return Message.set("Ce nom existe déjà", false);
        }
        if (structure1.getEtat()) { // Vérifie si l'état est true
            structure1.setNom(structure.getNom());
            structure1.setLocalite(structure.getLocalite());
            structure1.setNumero(structure.getNumero());
            return structureRepository.save(structure1);
        } else {
            // L'état n'est pas true, vous pouvez décider de renvoyer une erreur ou effectuer une autre action
            return Message.set("Cette structure est désactivée", false); // Exemple : retourne null si l'état n'est pas true
        }
    }


    @Override
    public String supprimer(Long id) {
        return null;
    }

    // desactiver la structure

    @Override
    public Structure modifierEtat(Long id) {
        Structure structure = structureRepository.findById(id).get();
        structure.setEtat(false);
        structure.setDate(new Date());
        return structureRepository.save(structure);
    }

    // activer la structure
    @Override
    public Structure modifierEtats(Long id) {
        Structure structure = structureRepository.findById(id).get();
        structure.setEtat(true);
        return structureRepository.save(structure);
    }

}
