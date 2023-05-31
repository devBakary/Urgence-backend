package com.application.urgence.controllers;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Forum;
import com.application.urgence.models.User;
import com.application.urgence.security.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @PostMapping("/add/{user}/{forum}")
    public Commentaire add(@RequestBody Commentaire commentaire, @PathVariable Forum forum, @PathVariable User user){
        return commentaireService.add(commentaire, forum, user);
    }

    @PutMapping("/update/{id}")
    public Commentaire update(@RequestBody Commentaire commentaire, @PathVariable Long id){
        return commentaireService.update(commentaire, id);
    }

    @GetMapping("/liste")
    public List<Commentaire> list() {
        return commentaireService.list();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return commentaireService.delete(id);
    }
}
