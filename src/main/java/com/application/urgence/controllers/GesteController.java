package com.application.urgence.controllers;

import com.application.urgence.models.Gestes;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("urgence/geste")
public class GesteController {

    @Autowired
    GesteService gesteService;

    @Autowired
    GesteRepository gesteRepository;

    @PostMapping("/creer")
    public String ajouterGeste(@Param(value = "img1") MultipartFile img1, @RequestParam(value = "img2", required= false ) MultipartFile img2,
                               @RequestParam(value = "img3", required= false ) MultipartFile img3, @RequestParam(value = "img4", required= false )  @Nullable MultipartFile img4,
                               @Param("nom") String nom, @Param("description") String description) throws IOException {



            String imge1 = StringUtils.cleanPath(img1.getOriginalFilename());
            String imge2 = StringUtils.cleanPath(img2.getOriginalFilename());
            String imge3 = StringUtils.cleanPath(img3.getOriginalFilename());
            String imge4 = StringUtils.cleanPath(img4.getOriginalFilename());


            Gestes gestes = new Gestes();

            gestes.setNom(nom);
            gestes.setDescription(description);
            gestes.setImg1(imge1);
            gestes.setImg2(imge2);
            gestes.setImg3(imge3);
            gestes.setImg4(imge4);

            gesteRepository.save(gestes);

            String uploadDir = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\images";
            FileUploadUtil.saveFile(uploadDir, imge1, img1);
            FileUploadUtil.saveFile(uploadDir, imge2, img2);
            FileUploadUtil.saveFile(uploadDir, imge3, img3);
            FileUploadUtil.saveFile(uploadDir, imge2, img2);

            return "success!";
    }
    @DeleteMapping("supprimer/{id}")
    public String supprimer(@PathVariable Long id){
        gesteRepository.deleteById(id);
        return "supprimer avec succes!";
    }
}
