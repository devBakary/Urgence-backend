package com.application.urgence.security.services;

import com.application.urgence.models.Forum;
import com.application.urgence.repository.ForumRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService{


    private ForumRepository forumRepository;

    @Override
    public Forum addForum(Forum forum) {
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
    public String delete(Long id) {
        Forum forum1 = forumRepository.findById(id).get();
        forumRepository.deleteById(forum1.getId());
        return "supprimer avec success !";
    }
}
