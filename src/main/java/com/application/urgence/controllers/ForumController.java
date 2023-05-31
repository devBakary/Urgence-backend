package com.application.urgence.controllers;

import com.application.urgence.models.Forum;
import com.application.urgence.models.User;
import com.application.urgence.repository.ForumRepository;
import com.application.urgence.security.services.ForumService;
import com.application.urgence.security.services.GesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/forum")
public class ForumController {

    @Autowired
    ForumService forumService;

    @Autowired
    GesteService gesteService;

    @Autowired
    ForumRepository forumRepository;

    @PostMapping("/add/{user}")
    public Forum addForum(@RequestBody Forum forum, @PathVariable User user){
        return forumService.addForum(forum, user);
    }

    @PutMapping("/update/{id}")
    public Forum update(@RequestBody Forum forum, @PathVariable Long id){
        return forumService.updateForum(forum, id);
    }

    @GetMapping("/liste")
    public List<Forum> list(){
        return forumService.list();
    }

    @GetMapping("/liste/{id}")
    public List<Forum> listForumByIdUser(@PathVariable Long id){
        User us = gesteService.userParId(id);
        return forumRepository.findForumByUserId(us.getId());
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return forumService.delete(id);
    }
}
