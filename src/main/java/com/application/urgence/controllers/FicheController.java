package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.models.Fiche;
import com.application.urgence.models.User;
import com.application.urgence.repository.FicheRepository;
import com.application.urgence.security.services.FicheService;
import com.application.urgence.security.services.GesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/fiche")
public class FicheController {


    @Autowired
    FicheService ficheService;

    @Autowired
    FicheRepository ficheRepository;

    @Autowired
    GesteService gesteService;

    @PostMapping("/creer/{id}")
    public String creer(@RequestBody Fiche fiche, @PathVariable Long id) {
        User us = gesteService.userParId(id);
        fiche.setUser(us);
        ficheService.creer(fiche);
        return "enregistrer avec succes";
    }

    @PutMapping("/modifier/{id}")
    public Fiche modifier(@RequestBody Fiche fiche, @PathVariable Long id){

        return ficheService.modifier(fiche, id);
    }

    @GetMapping("/liste/{id}")
    public List<Fiche> liste(@PathVariable Long id){

        return ficheRepository.listeFiche(id);
    }

    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        ficheService.supprimer(id);
        return "supprimé avec succes";
    }
}
