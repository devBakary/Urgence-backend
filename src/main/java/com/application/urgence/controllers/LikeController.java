package com.application.urgence.controllers;

import com.application.urgence.models.*;
import com.application.urgence.repository.CommentaireRepository;
import com.application.urgence.repository.LikeRepository;
import com.application.urgence.security.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/like")
public class LikeController {

    @Autowired
    LikeService likeService;
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    LikeRepository likeRepository;

    @PostMapping("/add/{commentaire}/{user}")
    public Likees addLike(@RequestBody Likees like, @PathVariable Commentaire commentaire, @PathVariable User user){
        return likeService.add(like, commentaire, user);
    }

    @GetMapping("/liste/{id}")
    public List<Likees> list(@PathVariable Long id){
        Commentaire com = commentaireRepository.findById(id).get();
        return likeRepository.findLikeesByCommentaireId(com.getId());
    }

    @GetMapping("/listeLike/{id}")
    public List<Likees> listLike(@PathVariable Long id){
        Commentaire com = commentaireRepository.findById(id).get();
        return likeRepository.listeLike(com.getId());
    }

    @GetMapping("/listeDislike/{id}")
    public List<Likees> listDislike(@PathVariable Long id){
        Commentaire com = commentaireRepository.findById(id).get();
        return likeRepository.listeDislike(com.getId());
    }
}
