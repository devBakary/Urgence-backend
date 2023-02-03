package com.application.urgence.controllers;

import com.application.urgence.models.Gestes;
import com.application.urgence.models.User;
import com.application.urgence.repository.GesteRepository;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.services.GesteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("urgence/geste")
public class GesteController {

    @Autowired
    GesteService gesteService;

    @Autowired
    GesteRepository gesteRepository;

    @PostMapping("/creer/{id}")
    public String ajouterGeste( @PathVariable Long id, @Param(value = "img1") MultipartFile img1, @RequestParam(value = "img2", required= false ) MultipartFile img2,
                               @Param("nom") String nom, @Param("description") String description) throws IOException {



        User us = gesteService.userParId(id);

        //on verifie si l'id de l'utilisateur est null
        if (us != null){
            String imge1 = StringUtils.cleanPath(img1.getOriginalFilename());
            String imge2 = StringUtils.cleanPath(img2.getOriginalFilename());

            Gestes gestes = new Gestes();

            gestes.setNom(nom);
            gestes.setDescription(description);
            gestes.setImg1(imge1);
            gestes.setImg2(imge2);

            gestes.setUser(us);
            gesteRepository.save(gestes);

            String uploadDir = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\images";
            FileUploadUtil.saveFile(uploadDir, imge1, img1);
            FileUploadUtil.saveFile(uploadDir, imge2, img2);

            return "success!";
        }

        return "l'id user est null";
    }
    @DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id){
        gesteRepository.deleteById(id);
        return "supprimer avec succes!";
    }

    @GetMapping("/afficher")
    public List<Gestes> affichage(){
        return gesteRepository.findAll();
    }

    @GetMapping("/liste/{id}")
    public List<Gestes> affichageliste(@PathVariable Long id){
        return gesteRepository.listeGeste(id);
    }

}
