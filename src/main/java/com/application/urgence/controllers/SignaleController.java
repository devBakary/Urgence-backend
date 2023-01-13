package com.application.urgence.controllers;

import com.application.urgence.models.SignalerDanger;
import com.application.urgence.repository.SignaleRepository;
import com.application.urgence.security.services.SignaleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/urgence/signale")
public class SignaleController {

    @Autowired
    SignaleService signaleService;

    @Autowired
    SignaleRepository signaleRepository;

    @PostMapping("/creer")
    public SignalerDanger creer(@RequestBody SignalerDanger signalerDanger){
        return signaleService.ajouter(signalerDanger);
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
