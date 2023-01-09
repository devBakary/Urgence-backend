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
        return null;
    }

    @Override
    public String supprimer(Long id) {
        ficheRepository.deleteById(id);
        return "supprimé";
    }
}
