package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.SignalerViolence;
import com.application.urgence.repository.SignalerViolenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalerViolenceServiceImpl implements SignalerViolenceService{
    @Autowired
    SignalerViolenceRepository signalerViolenceRepository;
    @Override
    public SignalerViolence creer(SignalerViolence signalerViolence) {
        return signalerViolenceRepository.save(signalerViolence);
    }

    @Override
    public List<SignalerViolence> liste() {
        return signalerViolenceRepository.findAll();
    }

    @Override
    public SignalerViolence modifier(SignalerViolence signalerViolence, Long id) {
        SignalerViolence signalerViolence1 = signalerViolenceRepository.findById(id).get();
        signalerViolence1.setMessage(signalerViolence.getMessage());
        return signalerViolenceRepository.save(signalerViolence1);
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }
}
