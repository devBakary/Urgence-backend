package com.application.urgence.security.services;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Likees;
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
    public Likees add(Likees like, Commentaire commentaire, User user) {
        User users = userRepository.findById(user.getId()).get();
        Commentaire com = commentaireRepository.findById(commentaire.getId()).get();
        like.setUser(users);
        like.setCommentaire(com);
        return likeRepository.save(like);
    }

    @Override
    public Likees update(Likees like, Long id) {
        Likees likes = likeRepository.findById(like.getId()).get();
        if (likes.isLike() && !likes.isDislike()){
            likes.setLike(false);
            return likeRepository.saveAndFlush(likes);
        }
        if(!likes.isLike() && likes.isDislike()){
            likes.setDislike(false);
            return likeRepository.saveAndFlush(likes);
        }
        if(!likes.isLike() && !likes.isDislike()){
            likes.setDislike(true);
            return likeRepository.saveAndFlush(likes);
        }
        if(!likes.isLike() && !likes.isDislike()){
            likes.setLike(true);
            return likeRepository.saveAndFlush(likes);
        }
        return null;
    }


}
