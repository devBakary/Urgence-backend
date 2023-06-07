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
    @PostMapping("/creer/{iduser}/{idstruct}")
    public Object creer(@PathVariable Long iduser, @PathVariable Long idstruct, @RequestBody Conseil conseil){
        if (conseil.getLibelle().isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }
        conseil.setUser(new User(iduser));
        conseil.setStructure(new Structure(idstruct));

        conseilService.creer(conseil);
        return Message.set("Avis poster avec succès",true);
    }

    @GetMapping("/liste")
    public List<Conseil> liste(){

        return conseilService.liste();
    }

    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody Conseil conseil){
        conseilService.modifier(id,conseil);
        return Message.set("Modifier avec succès",true);
    }

}
