package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.*;

import com.application.urgence.security.services.ConseilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/conseil")

public class ConseilController {

    @Autowired
    ConseilService conseilService;

    // Methode post de creation de conseil sur une structure

    @PostMapping("/creer/{idstruct}")
    public Object creer(@PathVariable Long idstruct, @RequestBody Conseil conseil){
        if (conseil.getLibelle().isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }

        conseil.setStructure(new Structure(idstruct));

        conseilService.creer(conseil);
        return Message.set("Conseil poster avec succès",true);
    }

    // Methode get de d'affichage de conseil sur une structure

    @GetMapping("/liste")
    public List<Conseil> liste(){

        return conseilService.liste();
    }

    // Methode put de modification de conseil sur une structure

    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody Conseil conseil){
        conseilService.modifier(id,conseil);
        return Message.set("Modifier avec succès",true);
    }
    // Methode delete de suppression de conseil sur une structure
    @DeleteMapping("/supprimer/{id}")
    public Object supprimer(@PathVariable Long id){
        return conseilService.supprimer(id);
    }


}
