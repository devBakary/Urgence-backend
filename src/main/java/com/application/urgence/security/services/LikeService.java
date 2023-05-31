package com.application.urgence.security.services;

import com.application.urgence.models.*;

import java.util.List;

public interface LikeService {

    Likees add(Likees like, Commentaire commentaire, User user);

    //Non implemente
    Likees update(Likees like, Long id);


}
