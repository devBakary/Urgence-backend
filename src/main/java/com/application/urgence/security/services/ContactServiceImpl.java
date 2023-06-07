package com.application.urgence.security.services;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Entite;
import com.application.urgence.models.User;
import com.application.urgence.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    GesteService gesteService;

    @Override
    public String creer(Contact contact) {
      contactRepository.save(contact);
      return "bien enregister";
    }





    @Override
    public Contact modifier(Long id, Contact contact) {

        Contact contactU = contactRepository.findById(id).get();

        if(!contact.getNom().trim().equals("") && contact.getNom() !=null){
            contactU.setNom(contact.getNom());
        }
        if (!contact.getPrenom().trim().equals("") && contact.getPrenom() !=null){
            contactU.setPrenom(contact.getPrenom());
        }
        if (!contact.getNumero().trim().equals("") && contact.getNumero() !=null){
            contactU.setNumero(contact.getNumero());
        }
        if (!contact.getDomicile().trim().equals("") && contact.getDomicile() !=null){
            contactU.setDomicile(contact.getDomicile());
        }
        if (!contact.getEmail().trim().equals("") && contact.getEmail() !=null){
            contactU.setEmail(contact.getEmail());
        }
        if (!contact.getLien().trim().equals("") && contact.getLien() !=null){
            contactU.setLien(contact.getLien());
        }

       return contactRepository.saveAndFlush(contact);
    }

    @Override
    public String supprimer(Long id) {
         contactRepository.deleteById(id);
        return "supprimé avec succes";
    }
}
