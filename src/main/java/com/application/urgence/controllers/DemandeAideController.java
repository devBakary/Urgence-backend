package com.application.urgence.controllers;

import com.application.urgence.Autres.MessageReponse;
import com.application.urgence.models.DemandeAide;
import com.application.urgence.security.services.DemandeAideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demande-aide")
@RequiredArgsConstructor
public class DemandeAideController {
    private final DemandeAideService demandeAideService;

    // Ajouter une demandeAide
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Object> ajouterDemandeAide(@RequestBody DemandeAide demandeAide){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.ajouterDemandeAide(demandeAide));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Modifier une demandeAide de service
    @PutMapping(value = "/modifier")
    public ResponseEntity<Object> modifierDemandeAide(@RequestBody DemandeAide demandeAide){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.modifierDemandeAide(demandeAide));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Récupérer une demandeAide par son id
    @GetMapping("recuperer")
    public ResponseEntity<Object> recuperDemandeAideParId(@RequestParam Long id){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.recuperDemandeAideParId(id));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Récupérer toutes les demandeAides par statut de suppresion
    @GetMapping(value = "/lister/{type}")
    public ResponseEntity<Object> listerDemandeAide(@PathVariable String type){

        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.listerDemandeAide(type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // Récupérer toutes les demandeAides par statut de validation
    @GetMapping(value = "/lister/{type}")
    public ResponseEntity<Object> listerDemandeAideParStatutValidation(@PathVariable String type){

        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.listerDemandeAideParValidation(type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Activer, Désactiver ou supprimer une demandeAide
    @PutMapping(value = "/{type}")
    public ResponseEntity<Object> activerDesactiverSupprimerDemandeAide(@RequestBody DemandeAide demandeAide, @PathVariable String type){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.supprimerActiverOuDesactiverDemandeAide(demandeAide.getId(), type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // Valider une demandeAide
    @PutMapping(value = "validation/{type}")
    public ResponseEntity<Object> validerDemandeAide(@RequestBody DemandeAide demandeAide, @PathVariable String type){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, demandeAideService.validerDemandeAide(demandeAide.getId(), type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
