package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.Type_violence;

import com.application.urgence.repository.StructureRepository;
import com.application.urgence.repository.TypeViolenceRepository;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.services.TypeViolenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/typeViolence")
public class TypeViolenceController {
    @Autowired
    TypeViolenceService typeViolenceService;
    @Autowired
    StructureRepository structureRepository;

    @Autowired
    TypeViolenceRepository typeViolenceRepository;

    // Methode post de creation de type de violence
    @PostMapping("/creer/{id}")
    public Object creer(@PathVariable Long id,
                        @RequestParam("libelle") String libelle,
                        @RequestParam("numero") String numero,
                        @RequestParam("img") MultipartFile img,
                        @RequestParam("audio") MultipartFile audio) throws IOException {
        //Verication des champs
        if (libelle.isEmpty() || numero.isEmpty() || img.isEmpty() || audio.isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }
        // les que les fichiers prendront dans la base
        String image = libelle + img.getOriginalFilename();
        String aud = libelle + audio.getOriginalFilename();

        if (typeViolenceRepository.findByLibelle(libelle) == null) {

                Type_violence typeViolence = new Type_violence();
                typeViolence.setLibelle(libelle);
                typeViolence.setNumero(numero);
                typeViolence.setImage(image);
                typeViolence.setAudio(aud);
               typeViolence.getStrutures().add(structureRepository.findById(id).get());

                String uploadDir = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\images";
                String upload = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\audios";


                FileUploadUtil.saveFile(uploadDir, image, img);
                FileUploadUtil.saveFile(upload, aud, audio);

                typeViolenceService.creer(typeViolence);

                return Message.set("Type de violence " + libelle + " créé avec succès", true);


        } else {
            return Message.set("Cette violence existe déjà", false);
        }
    }
    @GetMapping("/liste")
    public List<Type_violence> liste(){
        return typeViolenceService.liste();
    }

    // Methode de modification
    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id,
                           @RequestParam("libelle") String libelle,
                           @RequestParam("numero") String numero,
                           @RequestParam("img") MultipartFile img,
                           @RequestParam("audio") MultipartFile audio) throws IOException {



        String image = libelle + img.getOriginalFilename();
        String aud = libelle + audio.getOriginalFilename();

        Type_violence existingTypeViolence = typeViolenceRepository.findByLibelle(libelle);

        if (existingTypeViolence == null || existingTypeViolence.getId().equals(id)) {
            Type_violence typeViolence = typeViolenceRepository.findById(id).get();
            if (typeViolence != null) {
                typeViolence.setLibelle(libelle);
                typeViolence.setNumero(numero);
                typeViolence.setImage(image);
                typeViolence.setAudio(aud);

                String uploadDir = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\images";
                String upload = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\audios";

                FileUploadUtil.saveFile(uploadDir, image, img);
                FileUploadUtil.saveFile(upload, aud, audio);

                typeViolenceService.modifier(typeViolence, id);

                return Message.set("Type de violence " + libelle + " modifié avec succès", true);
            } else {
                return Message.set("L'entité avec l'ID spécifié n'existe pas", false);
            }
        } else {
            return Message.set("Cette violence existe déjà", false);
        }
    }


// Lancement du processus de suppression
    @PutMapping("/modifierEtat/{id}")
    public Object modifierEtat(@PathVariable Long id){
        typeViolenceService.modifierEtat(id);
        return Message.set("Type de violence désactivé",true);
    }

    //annulation de la supppression dans le temps
    @PutMapping("/modifierEtat2/{id}")
    public Object modifierEtat2(@PathVariable Long id){
        typeViolenceService.modifierEtats(id);
        return Message.set("Type de violence activé",true);
    }

}
