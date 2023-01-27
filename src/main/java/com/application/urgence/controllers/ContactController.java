package com.application.urgence.controllers;

import com.application.urgence.models.Contact;
import com.application.urgence.models.User;
import com.application.urgence.repository.ContactRepository;
import com.application.urgence.security.services.ContactService;
import com.application.urgence.security.services.GesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/contact")
public class ContactController {


    @Autowired
    ContactService contactService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    GesteService gesteService;

    @PostMapping("/creer")
    public String creer(@RequestBody Contact contact) {
        contactService.creer(contact);
        return "enregistrer avec succes";
    }

    @GetMapping("/liste/{id}")
    public List<Contact> afficherCont(@PathVariable Long id) {
        return contactRepository.listeContact(id);
    }

    @PutMapping("/modifier/{id}")
    public Contact modifier(@PathVariable Long id, @RequestBody Contact contact){

        return contactService.modifier(id, contact);
    }


    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        contactService.supprimer(id);
        return "supprimé avec succes";
    }
}
