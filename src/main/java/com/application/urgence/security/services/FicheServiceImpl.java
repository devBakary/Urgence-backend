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
        if(fiche.getNom() != null || !fiche.getNom().isEmpty()) {
            ficheU.setNom(fiche.getNom());
        }

        else if(fiche.getPrenom() != null || !fiche.getPrenom().isEmpty()) ficheU.setPrenom(fiche.getPrenom());

        else if(fiche.getEmail() != null || !fiche.getEmail().isEmpty()) ficheU.setEmail(fiche.getEmail());

        else if(fiche.getAdresse() != null || !fiche.getAdresse().isEmpty()) ficheU.setAdresse(fiche.getAdresse());

        else if(fiche.getAllergie() != null || !fiche.getAllergie().isEmpty()) ficheU.setAllergie(fiche.getAllergie());

        else if(fiche.getGroupe() != null || !fiche.getGroupe().isEmpty()) ficheU.setGroupe(fiche.getGroupe());

        return ficheRepository.saveAndFlush(ficheU);
    }

    @Override
    public String supprimer(Long id) {
        ficheRepository.deleteById(id);
        return "supprimé";
    }
}
