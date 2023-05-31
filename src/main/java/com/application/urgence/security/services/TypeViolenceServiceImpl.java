package com.application.urgence.security.services;

import com.application.urgence.models.Type_violence;
import com.application.urgence.repository.SignalerViolenceRepository;
import com.application.urgence.repository.TypeViolenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        return typeViolenceRepository.findAll();
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

    @EnableScheduling
    @Component
    public class TypeViolenceScheduler {

        private TypeViolenceManager typeViolenceManager; // Injecter le manager

        @Autowired
        public TypeViolenceScheduler(TypeViolenceManager typeViolenceManager) {
            this.typeViolenceManager = typeViolenceManager;
        }

        @Scheduled(cron = "0 59 2 * * ?") // Exécution quotidienne à 01h40
        public void verifierEtSupprimerTypeViolence() {
            List<Type_violence> typeViolences = typeViolenceManager.getTypeViolences();

            for (Type_violence typeViolence : typeViolences) {
                if (!typeViolence.getEtat()) {
                    typeViolenceManager.verifierEtSupprimerTypeViolence(typeViolence);
                }
            }
        }
    }

    @Component
    public class TypeViolenceManager {

        private TypeViolenceRepository typeViolenceRepository; // Injecter le repository

        @Autowired
        public TypeViolenceManager(TypeViolenceRepository typeViolenceRepository) {
            this.typeViolenceRepository = typeViolenceRepository;
        }

        public List<Type_violence> getTypeViolences() {
            return typeViolenceRepository.findAll();
        }

        public void verifierEtSupprimerTypeViolence(Type_violence typeViolence) {
            if (!typeViolence.getEtat()) {
                Date currentDate = new Date();
                long difference = currentDate.getTime() - typeViolence.getDate().getTime();
                long thirtyDaysInMillis = 30 * 24 * 60 * 60 * 1000;

                if (difference >= thirtyDaysInMillis) {
                    typeViolenceRepository.delete(typeViolence);
                }
            }
        }



    }
}
