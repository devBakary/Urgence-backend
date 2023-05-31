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

    @Autowired
    SignalerViolenceService signalerViolenceService;

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
    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody SignalerViolence signalerViolence){
        signalerViolenceService.modifier(signalerViolence,id);
        return Message.set("Modifier avec succès",true);
    }

    @GetMapping("/liste")
    public List<SignalerViolence> liste(){

        return signalerViolenceService.liste();
    }
}
