package com.application.urgence.security.services;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Forum;
import com.application.urgence.models.User;

import java.util.List;

public interface CommentaireService {

    Commentaire add(Commentaire commentaire, Forum forum, User user);

    Commentaire update(Commentaire commentaire, Long id);

    List<Commentaire> list();

    String delete(Long id);
}
