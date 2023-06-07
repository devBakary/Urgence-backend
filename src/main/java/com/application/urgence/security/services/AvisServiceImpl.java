package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Avis;
import com.application.urgence.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisServiceImpl implements AvisService{
    @Autowired
    AvisRepository avisRepository;
    @Override
    public Avis creer(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public List<Avis> liste() {
        return avisRepository.findAll();
    }

    @Override
    public Avis modifier(Avis avis, Long id) {
        Avis avis1 = avisRepository.findById(id).get();
        avis1.setMessage(avis.getMessage());
        return avisRepository.save(avis1);
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }
}
