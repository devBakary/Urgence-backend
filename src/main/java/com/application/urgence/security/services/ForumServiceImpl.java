package com.application.urgence.security.services;

import com.application.urgence.models.Forum;
import com.application.urgence.models.User;
import com.application.urgence.repository.ForumRepository;
import com.application.urgence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService{


    private ForumRepository forumRepository;
    final private UserRepository userRepository;
    final private GesteService gesteService;

    @Override
    public Forum addForum(Forum forum, User user) {
        User users = userRepository.findById(user.getId()).get();
        forum.setUser(users);
        return forumRepository.save(forum);
    }

    @Override
    public Forum updateForum(Forum forum, Long id) {
        Forum forum1 = forumRepository.findById(id).get();
        forum1.setTitre(forum.getTitre());
        return forumRepository.saveAndFlush(forum1);
    }

    @Override
    public List<Forum> list() {
        return forumRepository.findAll();
    }

    @Override
    public List<Forum> listForumByIdUser(Long id) {
        return forumRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        Forum forum1 = forumRepository.findById(id).get();
        forumRepository.deleteById(forum1.getId());
        return "supprimer avec success !";
    }
}
