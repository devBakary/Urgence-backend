package com.application.urgence.controllers;

import com.application.urgence.models.Entite;
import com.application.urgence.models.Notification;
import com.application.urgence.repository.EntiteRepository;
import com.application.urgence.repository.NotificationRepository;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.services.EntiteService;
import com.application.urgence.security.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @PostMapping("/creer")
    public String creer(@Param("nom") String nom, @Param("numero") String numero, @Param("img")MultipartFile img) throws IOException {

        String imge = StringUtils.cleanPath(img.getOriginalFilename());

        Entite entite = new Entite();
                entite.setNom(nom);
                entite.setNumero(numero);
                entite.setImg(imge);

        entiteRepository.save(entite);
        if ( entiteRepository.save(entite)!=null){

            Notification notif = new Notification();

            notif.setMessage("Une nouvelle entite "+ entite.getNom() + " vient d'être créer, le numero est " + entite.getNumero());
             notificationRepository.save(notif);
             return "entite creer avec succes!";
        }

        String uploadDir = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\images";
        FileUploadUtil.saveFile(uploadDir, imge, img);
        return "enregistrer avec succes";

    }

    @GetMapping("/liste")
    public List<Entite> liste(){

        return entiteService.liste();
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
