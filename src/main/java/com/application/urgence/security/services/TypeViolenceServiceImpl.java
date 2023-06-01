package com.application.urgence.security.services;


import com.application.urgence.models.Type_violence;
import com.application.urgence.repository.SignalerViolenceRepository;
import com.application.urgence.repository.TypeViolenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TypeViolenceServiceImpl implements TypeViolenceService {

    @Autowired
    TypeViolenceRepository typeViolenceRepository;

    @Autowired
    SignalerViolenceRepository signalerViolenceRepository;

    @Override
    public Type_violence creer(Type_violence typeViolence) {
        return typeViolenceRepository.save(typeViolence);
    }

    @Override
    public List<Type_violence> liste() {
        List<Type_violence> typeViolences = typeViolenceRepository.findAll();
        List<Type_violence> activeTypeViolences = new ArrayList<>();

        for (Type_violence type_violence : typeViolences) {
            if (type_violence.getEtat()) {
                activeTypeViolences.add(type_violence);
            }
        }

        return activeTypeViolences;
    }




    @Override
    public Type_violence modifier(Type_violence type_violence, Long id) {
        Type_violence type_violence1 = typeViolenceRepository.findById(id).get();
        type_violence1.setImage(type_violence.getImage());
        type_violence1.setLibelle(type_violence.getLibelle());
        type_violence1.setNumero(type_violence.getNumero());
        type_violence1.setAudio(type_violence.getAudio());
        return typeViolenceRepository.save(type_violence1);
    }

    @Override
    public Type_violence modifierEtat(Long id) {
        Type_violence type_violence1 = typeViolenceRepository.findById(id).get();
        type_violence1.setEtat(false);
        type_violence1.setDate(new Date());
        return typeViolenceRepository.save(type_violence1);
    }
    @Override
    public Type_violence modifierEtats(Long id) {
        Type_violence type_violence1 = typeViolenceRepository.findById(id).get();
        type_violence1.setEtat(true);
        //   type_violence1.setDate(new Date());
        return typeViolenceRepository.save(type_violence1);
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }

}
