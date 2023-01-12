package com.application.urgence.security.services;

import com.application.urgence.models.Gestes;
import com.application.urgence.repository.GesteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GesteServiceImpl implements GesteService{


    @Autowired
    GesteRepository gesteRepository;


    @Override
    public Gestes creer(Gestes geste) {
        return gesteRepository.save(geste);
    }

    @Override
    public List<Gestes> afficher() {
        return gesteRepository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        gesteRepository.deleteById(id);
        return "supprimer avec succes!";
    }
}
