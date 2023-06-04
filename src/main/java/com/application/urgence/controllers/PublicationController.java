package com.application.urgence.controllers;

import com.application.urgence.Autres.MessageReponse;
import com.application.urgence.models.Publication;
import com.application.urgence.security.services.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    // Ajouter une publication
    @PostMapping(value = "/ajouter")
    public ResponseEntity<Object> ajouterAttestationService(@RequestBody Publication publication){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.ajouterPublication(publication));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Modifier une publication de service
    @PutMapping(value = "/modifier")
    public ResponseEntity<Object> modifierAttestationService(@RequestBody Publication publication){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.modifierPublication(publication));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Récupérer une publication par son id
    @GetMapping("recuperer")
    public ResponseEntity<Object> recuperAttestationServiceParId(@RequestParam Long id){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.recuperPublicationParId(id));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Récupérer toutes les publications par statut de suppresion
    @GetMapping(value = "/lister/{type}")
    public ResponseEntity<Object> listerAttestationService(@PathVariable String type){

        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.listerPublication(type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // Récupérer toutes les publications par statut de validation
    @GetMapping(value = "/lister/{type}")
    public ResponseEntity<Object> listerPublicationParStatutValidation(@PathVariable String type){

        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.listerPublicationParValidation(type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Activer, Désactiver ou supprimer une publication
    @PutMapping(value = "/{type}")
    public ResponseEntity<Object> activerDesactiverSupprimerPublication(@RequestBody Publication publication, @PathVariable String type){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.supprimerActiverOuDesactiverPublication(publication.getId(), type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // Valider une publication
    @PutMapping(value = "validation/{type}")
    public ResponseEntity<Object> validerPublication(@RequestBody Publication publication, @PathVariable String type){
        try {
            return MessageReponse.Response("ok", HttpStatus.OK, publicationService.validerPublication(publication.getId(), type));
        }catch (Exception e){
            return MessageReponse.Response("Erreur", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
