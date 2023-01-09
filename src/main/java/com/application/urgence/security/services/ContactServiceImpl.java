package com.application.urgence.security.services;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Entite;
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

    @Override
    public String creer(Contact contact) {
      contactRepository.save(contact);
      return "bien enregister";
    }

    @Override
    public List<Contact> liste() {
        return contactRepository.findAll();
    }

    @Override
    public Contact modifier(Contact contact, Long id) {


        return null;
    }

    @Override
    public String supprimer(Long id) {
         contactRepository.deleteById(id);
        return "supprimé avec succes";
    }
}
