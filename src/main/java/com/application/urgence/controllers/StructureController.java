package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.Structure;
import com.application.urgence.models.User;
import com.application.urgence.repository.StructureRepository;
import com.application.urgence.security.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/structure")
public class StructureController {
    @Autowired
    StructureService structureService;
    @Autowired
    StructureRepository structureRepository;

    @PostMapping("/creer/{iduser}")
    public Object creer(@PathVariable Long iduser,@RequestBody Structure structure){
        if (structure.getNom().isEmpty() || structure.getNumero().isEmpty() || structure.getLocalite().isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }
        structure.setUser(new User(iduser));
        if (structureRepository.findByNom(structure.getNom()) == null) {
            structureService.creer(structure);
            return  Message.set("Structure "+structure.getNom() +" créé avec succès",true);
        }

        return  Message.set("Le structure "+structure.getNom() +" existe déjà",false);
    }

    // les structures actives
    @GetMapping("/liste")
    public List<Structure> liste(){
        return structureService.liste();
    }

    // les structures désactivées
    @GetMapping("/listeDesactive")
    public List<Structure> liste1(){
        return structureService.liste1();
    }
    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id, @RequestBody Structure structure){
        return   structureService.modifier(structure,id);

    }

    // Désactivation de la structure
    @PutMapping("/modifierEtat/{id}")
    public Object modifierEtat(@PathVariable Long id){
        structureService.modifierEtat(id);
        return Message.set("Structure désactivée avec succès",true);
    }

    //activation de la structure
    @PutMapping("/modifierEtat2/{id}")
    public Object modifierEtat2(@PathVariable Long id){
        structureService.modifierEtats(id);
        return Message.set("Structure activée avec succès",true);
    }

}
