package com.application.urgence.controllers;

import com.application.urgence.message.Message;
import com.application.urgence.models.Avis;
import com.application.urgence.models.Entite;
import com.application.urgence.models.Structure;
import com.application.urgence.models.Type_violence;

import com.application.urgence.repository.StructureRepository;
import com.application.urgence.repository.TypeViolenceRepository;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.services.TypeViolenceService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @PostMapping("/creer/{id}")
    public Object creer(@PathVariable Long id,
                        @RequestParam("libelle") String libelle,
                        @RequestParam("numero") String numero,
                        @RequestParam("img") MultipartFile img,
                        @RequestParam("audio") MultipartFile audio) throws IOException {
        if (libelle.isEmpty() || numero.isEmpty() || img.isEmpty() || audio.isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }

        String image = libelle + img.getOriginalFilename();
        String aud = libelle + audio.getOriginalFilename();

        if (typeViolenceRepository.findByLibelle(libelle) == null) {
            try {
                Type_violence typeViolence = new Type_violence();
                typeViolence.setLibelle(libelle);
                typeViolence.setNumero(numero);
                typeViolence.setImage(image);
                typeViolence.setAudio(aud);
                typeViolence.getStruture().add(structureRepository.findById(id).get());

                String uploadDir = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\images";
                String upload = "C:\\Users\\LENOVO\\Desktop\\urgence\\Urgence-Frontend\\assets\\audios";

                // Vérification de la taille du fichier image
                if (img.getSize() >  20 * 1024 * 1024) {
                   new Message("La taille du fichier dépasse la limite autorisée",false);
                }

                // Vérification de la taille du fichier audio
                if (audio.getSize() > 20 * 1024 * 1024) {
                     new Message("La taille du fichier dépasse la limite autorisée",false);
                }

                FileUploadUtil.saveFile(uploadDir, image, img);
                FileUploadUtil.saveFile(upload, aud, audio);

                typeViolenceService.creer(typeViolence);

                return Message.set("Type de violence " + libelle + " créé avec succès", true);
            } catch (SizeLimitExceededException ex) {
                return Message.set(ex.getMessage(), false);
            } catch (Exception ex) {
                return Message.set("Une erreur s'est produite lors de la création du type de violence", false);
            }
        } else {
            return Message.set("Cette violence existe déjà", false);
        }
    }
    @GetMapping("/liste")
    public List<Type_violence> liste(){
        return typeViolenceService.liste();
    }

    @PutMapping("/modifier/{id}")
    public Object modifier(@PathVariable Long id,
                           @RequestParam("libelle") String libelle,
                           @RequestParam("numero") String numero,
                           @RequestParam("img") MultipartFile img,
                           @RequestParam("audio") MultipartFile audio) throws IOException {

        if (libelle.isEmpty() || numero.isEmpty() || img.isEmpty() || audio.isEmpty()) {
            return Message.set("Veuillez remplir tous les champs", false);
        }

        if (img.getSize() > 20 * 1024 * 1024) {
            return Message.set("La taille de l'image dépasse la limite autorisée (20 Mo)", false);
        }

        if (audio.getSize() > 20 * 1024 * 1024) {
            return Message.set("La taille du fichier audio dépasse la limite autorisée (20 Mo)", false);
        }

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



    @PutMapping("/modifierEtat/{id}")
    public Object modifierEtat(@PathVariable Long id){
        typeViolenceService.modifierEtat(id);
        return Message.set("Modifier avec succès",true);
    }
    @PutMapping("/modifierEtat2/{id}")
    public Object modifierEtat2(@PathVariable Long id){
        typeViolenceService.modifierEtats(id);
        return Message.set("Modifier avec succès",true);
    }

}
