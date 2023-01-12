package com.application.urgence.security.services;

import com.application.urgence.models.Entite;
import com.application.urgence.repository.EntiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntiteServiceImpl implements EntiteService{

    @Autowired
    EntiteRepository entiteRepository;

    @Override
    public String creer(Entite entite) {
        entiteRepository.save(entite);
        return "enregistrer avec succes";
    }

    @Override
    public List<Entite> liste() {
        return entiteRepository.findAll();
    }

    @Override
    public Entite modifier(Entite entite, Long id) {
        Entite entiteU = entiteRepository.findById(id).get();
        entiteU.setNom(entite.getNom());
        entiteU.setNumero(entite.getNumero());
        entiteU.setImg(entite.getImg());


        return entiteRepository.saveAndFlush(entiteU);
    }

    @Override
    public String supprimer(Long id) {
        entiteRepository.deleteById(id);
        return "Entité supprimer!";
    }
}
