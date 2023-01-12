package com.application.urgence.security.services;

import com.application.urgence.models.Fiche;
import com.application.urgence.repository.FicheRepository;

import java.util.List;

public class FicheServiceImpl implements FicheService{


    FicheRepository ficheRepository;

    @Override
    public String creer(Fiche fiche) {
        ficheRepository.save(fiche);
        return "enregistré!";
    }

    @Override
    public List<Fiche> liste() {
        return ficheRepository.findAll();
    }

    @Override
    public Fiche modifier(Fiche fiche, Long id) {
        Fiche ficheU = ficheRepository.findById(id).get();

        ficheU.setNom(fiche.getNom());
        ficheU.setPrenom(fiche.getPrenom());
        ficheU.setEmail(fiche.getEmail());
        ficheU.setDomicile(fiche.getDomicile());
        ficheU.setAllergie(fiche.getAllergie());
        ficheU.setGroupe(fiche.getGroupe());
        return ficheRepository.saveAndFlush(ficheU);
    }

    @Override
    public String supprimer(Long id) {
        ficheRepository.deleteById(id);
        return "supprimé";
    }
}
