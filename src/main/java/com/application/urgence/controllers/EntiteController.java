package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.security.services.EntiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urgence/entite")
public class EntiteController {

    @Autowired
    EntiteService entiteService;

    @PostMapping("/creer")
    public String creer(@RequestBody Entite entite) {
        entiteService.creer(entite);
        return "enregistrer avec succes";
    }

    @PostMapping("/liste")
    public List<Entite> liste(){

        return entiteService.liste();
    }

    @PutMapping("/modifier/{id}")
    public Entite modifier(@RequestBody Entite entite, @PathVariable Long id) {

        return entiteService.modifier(entite, id);
    }

    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        entiteService.supprimer(id);
        return "supprimé avec succes";
    }
}
