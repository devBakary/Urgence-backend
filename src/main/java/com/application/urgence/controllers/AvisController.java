package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.*;
import com.application.urgence.security.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/avis")
public class AvisController {
    @Autowired
    AvisService avisService;

    // Methode post de creation de avis

    @PostMapping("/creer/{iduser}/{idstruct}")
    public Object creer(@PathVariable Long iduser,@PathVariable Long idstruct, @RequestBody Avis avis){
        if (avis.getMessage().isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }
        avis.setUser(new User(iduser));
        avis.setStructure(new Structure(idstruct));

        avisService.creer(avis);
        return Message.set("Avis poster avec succès",true);
    }

    // Methode get d'affichage' de avis

    @GetMapping("/liste")
    public List<Avis> liste(){

        return avisService.liste();
    }

    // Methode put de modification de avis

    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody Avis avis){
        avisService.modifier(avis,id);
        return Message.set("Modifier avec succès",true);
    }

    // Methode delete de suppression de avis
    @DeleteMapping("/supprimer/{id}")
    public Object supprimer(@PathVariable Long id){
        return avisService.supprimer(id);
    }

}
