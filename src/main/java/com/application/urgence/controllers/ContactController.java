package com.application.urgence.controllers;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Entite;
import com.application.urgence.security.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urgence/contact")
public class ContactController {


    @Autowired
    ContactService contactService;

    @PostMapping("/creer")
    public String creer(@RequestBody Contact contact) {
        contactService.creer(contact);
        return "enregistrer avec succes";
    }

    @GetMapping("/liste")
    public List<Contact> liste(){

        return contactService.liste();
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
