package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.models.Fiche;
import com.application.urgence.security.services.FicheService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FicheController {


    FicheService ficheService;

    @PostMapping("/creer")
    public String creer(@RequestBody Fiche fiche) {
        ficheService.creer(fiche);
        return "enregistrer avec succes";
    }

    @PostMapping("/liste")
    public List<Fiche> liste(){

        return ficheService.liste();
    }

    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        ficheService.supprimer(id);
        return "supprimé avec succes";
    }
}
