package com.application.urgence.security.services;

import com.application.urgence.models.SignalerDanger;
import com.application.urgence.repository.SignaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignaleServiceImpl implements SignaleService{

    @Autowired
    SignaleRepository signaleRepository;

    @Override
    public SignalerDanger ajouter(SignalerDanger signalerDanger) {
        return signaleRepository.save(signalerDanger);
    }

    @Override
    public String supprimer(Long id) {
         signaleRepository.deleteById(id);
        return "supprimer avec succes";
    }
}
