package com.application.urgence.security.services;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Forum;
import com.application.urgence.models.User;
import com.application.urgence.repository.CommentaireRepository;
import com.application.urgence.repository.ForumRepository;
import com.application.urgence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentaireServiceImpl implements CommentaireService{

    final private ForumRepository forumRepository;
    final private UserRepository userRepository;
    final private CommentaireRepository commentaireRepository;
    @Override
    public Commentaire add(Commentaire commentaire, Forum forum, User user) {
        Forum forums = forumRepository.findById(forum.getId()).get();
        User users = userRepository.findById(user.getId()).get();
        commentaire.setForum(forums);
        commentaire.setUser(users);
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire update(Commentaire commentaire, Long id) {
        Commentaire com = commentaireRepository.findById(id).get();
        com.setMessage(commentaire.getMessage());
        return commentaireRepository.saveAndFlush(com);
    }

    @Override
    public List<Commentaire> list() {
        return commentaireRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        Commentaire com = commentaireRepository.findById(id).get();
        commentaireRepository.deleteById(com.getId());
        return " suupprimé avec succes";
    }
}
