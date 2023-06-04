package com.application.urgence.security.services;

import com.application.urgence.Autres.GestionDate;
import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import com.application.urgence.models.DemandeAide;
import com.application.urgence.models.User;
import com.application.urgence.repository.DemandeAideRepository;
import com.application.urgence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeAideServiceImpl implements DemandeAideService {
    private final DemandeAideRepository demandeAideRepository;
    private final UserRepository userRepository;

    // Ajouter une nouvelle demande d'aide
    @Override
    public DemandeAide ajouterDemandeAide(DemandeAide demande) {
        User utiisateur = userRepository.findById(demande.getUtilisateur().getId()).isPresent() ? userRepository.findById(demande.getUtilisateur().getId()).get() : null;
        User admin = userRepository.findById(demande.getAdmin().getId()).isPresent() ? userRepository.findById(demande.getAdmin().getId()).get() : null;
        if (utiisateur == null) {
            throw new RuntimeException("Cet utilisateur n'existe pas");
        }
        if (admin == null) {
            throw new RuntimeException("Cet admin n'existe pas");
        }
        demande.setUtilisateur(utiisateur);
        demande.setAdmin(admin);
        demande.setValidationDemandeAide(EtatValidation.EN_COURS);
        demande.setStatutDemandeAide(Statut.ACTIF);
        GestionDate gestionDate = new GestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        gestionDate.setDateEntregistrement(LocalDateTime.now());
        demande.setGestionDate(gestionDate);
        return demandeAideRepository.save(demande);
    }

    // Modifier une demande d'aide
    @Override
    public DemandeAide modifierDemandeAide(DemandeAide demande) {
        return demandeAideRepository.findById(demande.getId()).map(d -> {
            Optional<User> utilisateur = userRepository.findById(demande.getUtilisateur().getId());
            Optional<User> admin = userRepository.findById(demande.getAdmin().getId());
            utilisateur.ifPresentOrElse(demande::setUtilisateur, () -> {
                throw  new RuntimeException("Cet utilisateur n'existe pas");
            });
            admin.ifPresentOrElse(demande::setAdmin, () -> {
                throw  new RuntimeException("Cet admin n'existe pas");
            });
            d.setDescription(demande.getDescription());
            d.setImage(demande.getImage());
            d.setLibelle(demande.getLibelle());
            d.setNumero(demande.getNumero());
            d.setPieceIdentitite(demande.getPieceIdentitite());
            GestionDate gestionDate = d.getGestionDate();
            gestionDate.setDateModification(LocalDateTime.now());
            d.setGestionDate(gestionDate);
            return demandeAideRepository.save(d);
        }).orElseThrow(() -> new RuntimeException("Cette demande d'aide n'existe pas"));
    }


    // Recuperer une demande d'aide
    @Override
    public DemandeAide recuperDemandeAideParId(Long id) {
        return demandeAideRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette demande d'aide  n'existe pas"));

    }


    // Lister toutes les demandes d'aide
    @Override
    public List<DemandeAide> listerDemandeAide(String type) {
        return switch (type) {
            case "tout" -> demandeAideRepository.findAll();
            case "supprimer" -> demandeAideRepository.findByStatutDemandeAide(Statut.INACTIF);
            case "nonsupprimer" -> demandeAideRepository.findByStatutDemandeAide(Statut.ACTIF);
            default -> new ArrayList<>();
        };
    }

    // Lister  les demandes d'aide par statut de validation
    @Override
    public List<DemandeAide> listerDemandeAideParValidation(String type) {
        return switch (type) {
            case "tout" -> demandeAideRepository.findAll();
            case "valider" -> demandeAideRepository.findByValidationDemandeAide(EtatValidation.VALIDER);
            case "nonvalider" -> demandeAideRepository.findByValidationDemandeAide(EtatValidation.NON_VALIDER);
            case "encours" -> demandeAideRepository.findByValidationDemandeAide(EtatValidation.EN_COURS);
            default -> new ArrayList<>();
        };
    }


    // Supprimer, activer ou désactiver une demande d'aide
    @Override
    public String supprimerActiverOuDesactiverDemandeAide(Long id, String type) {
        DemandeAide demande = demandeAideRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette demande d'aide n'existe pas"));
        String message;
        GestionDate gestionDate = demande.getGestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        if (type.equals("supprimer")){
            demandeAideRepository.deleteById(id);
            message = "Demande supprimer avec succés";
        }else if (type.equals("activer")){
            demande.setStatutDemandeAide(Statut.ACTIF);
            demandeAideRepository.save(demande);
            message = "Demande travail activer avec succés";
        }else {
            demande.setStatutDemandeAide(Statut.INACTIF);
            demandeAideRepository.save(demande);
            message = "Demande désactiver avec succés";
        }
        return message;
    }


    // Validation d'une demande d'aide
    @Override
    public String validerDemandeAide(Long id, String type) {
        DemandeAide demande = demandeAideRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette demande d'aide n'existe pas"));
        String message;
        GestionDate gestionDate = demande.getGestionDate();
        gestionDate.setDateModification(LocalDateTime.now());
        if (type.equals("valider")){
            demande.setValidationDemandeAide(EtatValidation.VALIDER);
            message = "Demande validation changé en validé avec succés";
        }else if (type.equals("encours")){
            demande.setValidationDemandeAide(EtatValidation.EN_COURS);
            demandeAideRepository.save(demande);
            message = "Demande validation changé en en cours avec succés";
        }else {
            demande.setValidationDemandeAide(EtatValidation.NON_VALIDER);
            demandeAideRepository.save(demande);
            message = "Demande validation changé en non valide avec succés";
        }
        return message;
    }
}
