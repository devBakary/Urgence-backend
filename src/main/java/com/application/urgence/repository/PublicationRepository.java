package com.application.urgence.repository;

import com.application.urgence.Enumeration.EtatValidation;
import com.application.urgence.Enumeration.Statut;
import com.application.urgence.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByStatutPublication(Statut statut);
    List<Publication> findByValidationPublication(EtatValidation validation);
}
