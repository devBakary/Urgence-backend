package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.models.Notification;
import com.application.urgence.models.SignalerDanger;
import com.application.urgence.models.User;
import com.application.urgence.repository.EntiteRepository;
import com.application.urgence.repository.NotificationRepository;
import com.application.urgence.repository.SignaleRepository;
import com.application.urgence.security.services.GesteService;
import com.application.urgence.security.services.SignaleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/urgence/signale")
public class SignaleController {

    @Autowired
    SignaleService signaleService;

    @Autowired
    SignaleRepository signaleRepository;

    @Autowired
    GesteService gesteService;

    @Autowired
    EntiteRepository entiteRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @PostMapping("/creer/{iduser}/{id}")
    public SignalerDanger creer(@RequestBody SignalerDanger signalerDanger, @PathVariable Long iduser, @PathVariable Long id){
        User us = gesteService.userParId(iduser);
        Entite ett = entiteRepository.findById(id).get();
        signalerDanger.setUser(us);
        signalerDanger.setEntite(ett);
        Notification notif = new Notification();

        notif.setMessage("Un nouveau signale de "+ signalerDanger.getEntite().getNom() + " en provenance de  " + signalerDanger.getUser().getNumero() + " à l'adresse " + signalerDanger.getUser().getAdresse());
        notificationRepository.save(notif);
        return signaleService.ajouter(signalerDanger);
    }

    @GetMapping("/liste")
    public List<SignalerDanger> liste(){
        return signaleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id){

        SignalerDanger dg = signaleRepository.findById(id).get();
        if (dg == null){
            return " ce danger n'existe pas!";
        }
        else {
            signaleService.supprimer(id);
            return "suprimé avec succes!";
        }

    }
}
