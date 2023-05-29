package com.application.urgence.security.services;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Like;
import com.application.urgence.models.User;
import com.application.urgence.repository.CommentaireRepository;
import com.application.urgence.repository.LikeRepository;
import com.application.urgence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService{

    final private UserRepository userRepository;
    final private LikeRepository likeRepository;
    final private CommentaireRepository commentaireRepository;

    @Override
    public Like add(Like like, Commentaire commentaire, User user) {
        User users = userRepository.findById(user.getId()).get();
        Commentaire com = commentaireRepository.findById(commentaire.getId()).get();
        like.setUser(users);
        return likeRepository.save(like);
    }

    @Override
    public Like update(Like like, Long id) {
        Like likes = likeRepository.findById(like.getId()).get();
        if (likes.isLike()){

        }
        return null;
    }

    @Override
    public List<Like> list() {
        return likeRepository.findAll();
    }

}
