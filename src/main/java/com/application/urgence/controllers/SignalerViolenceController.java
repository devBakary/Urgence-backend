package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.*;
import com.application.urgence.security.services.SignalerViolenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/signalerViolence")
public class SignalerViolenceController {

    // appel des services et repository pour acceder aux differentes methodes

    @Autowired
    SignalerViolenceService signalerViolenceService;

    // Methode post de creation de signaler une violence
    @PostMapping("/creer/{idtype}/{iduser}")
    public Object  creer(@PathVariable Long idtype, @PathVariable Long iduser,
                         @RequestBody SignalerViolence signalerViolence){
        if (signalerViolence.getMessage().isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }
        signalerViolence.setUser(new User(iduser));
        signalerViolence.setTypeViolence(new Type_violence(idtype));

        signalerViolenceService.creer(signalerViolence);
        return Message.set("Violence signaler avec succees",true);
    }

    // Methode put de modification de signaler une violence
    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody SignalerViolence signalerViolence){
        signalerViolenceService.modifier(signalerViolence,id);
        return Message.set("Modifier avec succès",true);
    }

    // Methode get d'affichage de signaler une violence

    @GetMapping("/liste")
    public List<SignalerViolence> liste(){

        return signalerViolenceService.liste();
    }

    // Methode delete de suppression de signaler une violence

    @DeleteMapping("/supprimer/{id}")
    public Object supprimer(@PathVariable Long id){
        return signalerViolenceService.supprimer(id);
    }

}
