package com.application.urgence.security.services;

import com.application.urgence.Autres.GestionDate;
import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import com.application.urgence.models.DemandeAide;
import com.application.urgence.models.Publication;
import com.application.urgence.models.User;
import com.application.urgence.repository.PublicationRepository;
import com.application.urgence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    // Ajouter une nouvelle publication
    @Override
    public Publication ajouterPublication(Publication publication) {
        User utiisateur = userRepository.findById(publication.getUtilisateur().getId()).isPresent() ? userRepository.findById(publication.getUtilisateur().getId()).get() : null;
        User admin = userRepository.findById(publication.getAdmin().getId()).isPresent() ? userRepository.findById(publication.getAdmin().getId()).get() : null;
        if (utiisateur == null) {
            throw new RuntimeException("Cet utilisateur n'existe pas");
        }
        if (admin == null) {
            throw new RuntimeException("Cet admin n'existe pas");
        }
        publication.setUtilisateur(utiisateur);
        publication.setAdmin(admin);
        publication.setDatePublication(LocalDateTime.now());
        publication.setValidationPublication(EtatValidation.EN_COURS);
        publication.setStatutPublication(Statut.ACTIF);
        GestionDate gestionDate = new GestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        gestionDate.setDateEntregistrement(LocalDateTime.now());
        publication.setGestionDate(gestionDate);
        return publicationRepository.save(publication);
    }

    // Modifier une publication
    @Override
    public Publication modifierPublication(Publication publication) {

        return publicationRepository.findById(publication.getId()).map(p -> {
            Optional<User> utilisateur = userRepository.findById(publication.getUtilisateur().getId());
            Optional<User> admin = userRepository.findById(publication.getAdmin().getId());
            utilisateur.ifPresentOrElse(publication::setUtilisateur, () -> {
                throw  new RuntimeException("Cet utilisateur n'existe pas");
            });
            admin.ifPresentOrElse(publication::setAdmin, () -> {
                throw  new RuntimeException("Cet admin n'existe pas");
            });
            p.setContenu(publication.getContenu());
            p.setImage(publication.getImage());
            p.setNumero(publication.getNumero());
            p.setTitre(publication.getTitre());
            GestionDate gestionDate = p.getGestionDate();
            gestionDate.setDateModification(LocalDateTime.now());
            p.setGestionDate(gestionDate);
            return publicationRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Cette présence n'existe pas"));
    }

    // Recuperer une publication par son id
    @Override
    public Publication recuperPublicationParId(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette publication n'existe pas"));

    }


    // Lister toutes les publications
    @Override
    public List<Publication> listerPublication(String type) {
        return switch (type) {
            case "tout" -> publicationRepository.findAll();
            case "supprimer" -> publicationRepository.findByStatutPublication(Statut.INACTIF);
            case "nonsupprimer" -> publicationRepository.findByStatutPublication(Statut.ACTIF);
            default -> new ArrayList<>();
        };
    }

    // Lister toutes les publications par statut de validation
    @Override
    public List<Publication> listerPublicationParValidation(String type) {
        return switch (type) {
            case "tout" -> publicationRepository.findAll();
            case "valider" -> publicationRepository.findByValidationPublication(EtatValidation.VALIDER);
            case "encours" -> publicationRepository.findByValidationPublication(EtatValidation.EN_COURS);
            case "nonvalider" -> publicationRepository.findByValidationPublication(EtatValidation.NON_VALIDER);
            default -> new ArrayList<>();
        };
    }


    // Supprimer, activer ou désactiver une publication
    @Override
    public String supprimerActiverOuDesactiverPublication(Long id, String type) {
        Publication publication = publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce publication n'existe pas"));
        String message;
        GestionDate gestionDate = publication.getGestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        if (type.equals("supprimer")){
            publicationRepository.deleteById(id);
            message = "Publication supprimer avec succés";
        }else if (type.equals("activer")){
            publication.setStatutPublication(Statut.ACTIF);
            publicationRepository.save(publication);
            message = "Publication travail activer avec succés";
        }else {
            publication.setStatutPublication(Statut.INACTIF);
            publicationRepository.save(publication);
            message = "Publication désactiver avec succés";
        }
        return message;
    }

    // Validation d'une publication
    @Override
    public String validerPublication(Long id, String type) {
        Publication publication = publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette publication n'existe pas"));
        String message;
        GestionDate gestionDate = publication.getGestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        if (type.equals("valider")){
            publication.setValidationPublication(EtatValidation.VALIDER);
            message = "Publication validation changé en validé avec succés";
        }else if (type.equals("encours")){
            publication.setValidationPublication(EtatValidation.EN_COURS);
            publicationRepository.save(publication);
            message = "ublication validation changé en en cours avec succés";
        }else {
            publication.setValidationPublication(EtatValidation.NON_VALIDER);
            publicationRepository.save(publication);
            message = "Publication validation changé en non valide avec succés";
        }
        return message;
    }
}
