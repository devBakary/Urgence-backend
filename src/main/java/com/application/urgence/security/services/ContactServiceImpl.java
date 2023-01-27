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

       contactU.setDomicile(contact.getDomicile());
       contactU.setEmail(contact.getEmail());
       contactU.setLien(contact.getLien());
       contactU.setNom(contact.getNom());
       contactU.setPrenom(contact.getPrenom());
       contactU.setNumero(contact.getNumero());

       return contactRepository.saveAndFlush(contact);
    }

    @Override
    public String supprimer(Long id) {
         contactRepository.deleteById(id);
        return "supprimé avec succes";
    }
}
