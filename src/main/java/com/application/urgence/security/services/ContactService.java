package com.application.urgence.security.services;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Entite;

import java.util.List;

public interface ContactService {

    String creer(Contact contact);



    Contact modifier(Long id, Contact contact);

    String supprimer(Long id);
}
