package com.application.urgence.repository;

import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import com.application.urgence.models.DemandeAide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeAideRepository extends JpaRepository<DemandeAide, Long> {
    List<DemandeAide> findByValidationDemandeAide(EtatValidation validation);
    List<DemandeAide> findByStatutDemandeAide(Statut statut);
}
