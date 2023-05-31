package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Conseil;
import com.application.urgence.repository.ConseilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConseilServiceImpl implements ConseilService{
    @Autowired
    ConseilRepository conseilRepository;
    @Override
    public Conseil creer(Conseil conseil) {
        return conseilRepository.save(conseil);
    }

    @Override
    public List<Conseil> liste() {
        return conseilRepository.findAll();
    }

    @Override
    public Conseil modifier(Long id,Conseil conseil) {
        Conseil conseil1 = conseilRepository.findById(id).get();
        conseil1.setLibelle(conseil.getLibelle());
        conseil1.setStructure(conseil.getStructure());
        return conseilRepository.save(conseil);
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }
}
