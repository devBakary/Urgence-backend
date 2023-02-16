package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.models.Notification;
import com.application.urgence.models.User;
import com.application.urgence.repository.EntiteRepository;
import com.application.urgence.repository.NotificationRepository;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.services.EntiteService;
import com.application.urgence.security.services.GesteService;
import com.application.urgence.security.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/entite")
public class EntiteController {

    @Autowired
    EntiteService entiteService;

    @Autowired
    EntiteRepository entiteRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    GesteService gesteService;

    @PostMapping("/creer/{id}")
    public String creer(@PathVariable Long id, @Param("nom") String nom, @Param("numero") String numero, @Param("img")MultipartFile img, @Param("audio") MultipartFile audio) throws IOException {

        User us = gesteService.userParId(id);

        String imge = nom +img.getOriginalFilename();
        String audi = nom +img.getOriginalFilename();

        Entite entite = new Entite();
                entite.setNom(nom);
                entite.setNumero(numero);
                entite.setImg(imge);
                entite.setAudio(audi);

                entite.setUser(us);
        entiteRepository.save(entite);
        if ( entiteRepository.save(entite)!=null){

            Notification notif = new Notification();

            notif.setMessage("Une nouvelle entite "+ entite.getNom() + " vient d'être créer, le numero est " + entite.getNumero());
             notificationRepository.save(notif);
            String uploadDir = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\images";
            String upload = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\audios";
            FileUploadUtil.saveFile(uploadDir, imge, img);
            FileUploadUtil.saveFile(upload, audi, audio);
             return "entite creer avec succes!";
        }


        return "enregistrer avec succes";

    }

    @GetMapping("/liste")
    public List<Entite> liste(){

        return entiteService.liste();
    }

    //liste de notif
    @GetMapping("/listeNotif")
    public List<Notification> listes(){

        return notificationRepository.findAll();
    }
    //liste de notif non lu
        @GetMapping("/listeNotification")
        public List<Notification> listeNotification(){

        return notificationRepository.listeNotif();
    }

    //modification de la notif
    @PutMapping("/update/{id}")
    public Notification updateNotif(@PathVariable Long id){
        Notification uNotif = notificationRepository.findById(id).get();
        uNotif.setEtat(1);
        return notificationRepository.saveAndFlush(uNotif);
    }


    @PutMapping("/modifier/{id}")
    public Entite modifier(@Param("nom") String nom, @Param("numero") String numero, @Param("img") MultipartFile img, @PathVariable Long id) throws IOException {

        String imgeU = StringUtils.cleanPath(img.getOriginalFilename());

        Entite entiteU = entiteRepository.findById(id).get();

        entiteU.setNom(nom);
        entiteU.setNumero(numero);
        entiteU.setImg(imgeU);
        return entiteService.modifier(entiteU, id);
    }

    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        entiteService.supprimer(id);
        return "supprimé avec succes";
    }
}
