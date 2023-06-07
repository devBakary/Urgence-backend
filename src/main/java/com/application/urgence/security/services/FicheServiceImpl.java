package com.application.urgence.security.services;

import com.application.urgence.models.Fiche;
import com.application.urgence.repository.FicheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
        if(!fiche.getNom().trim().equals("")) {
            ficheU.setNom(fiche.getNom());
        }
        if(!fiche.getPrenom().trim().equals("")){
            ficheU.setPrenom(fiche.getPrenom());
        }
        if (!fiche.getAdresse().trim().equals("")){
            ficheU.setAdresse(fiche.getAdresse());
        }
        if(!fiche.getGroupe().trim().equals("")){
            ficheU.setGroupe(fiche.getGroupe());
        }
        if (!fiche.getAllergie().trim().equals("")){
            ficheU.setAllergie(fiche.getAllergie());
        }


        return ficheRepository.saveAndFlush(ficheU);
    }

    @Override
    public String supprimer(Long id) {
        ficheRepository.deleteById(id);
        return "supprimé";
    }
}
