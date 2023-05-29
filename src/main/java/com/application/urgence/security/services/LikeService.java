package com.application.urgence.security.services;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Forum;
import com.application.urgence.models.Like;
import com.application.urgence.models.User;

import java.util.List;

public interface LikeService {

    Like add(Like like, Commentaire commentaire, User user);

    Like update(Like like, Long id);

    List<Like> list();

}
