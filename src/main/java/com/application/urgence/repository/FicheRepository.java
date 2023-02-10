package com.application.urgence.repository;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FicheRepository extends JpaRepository<Fiche, Long> {


    @Query(value = "select * from fiche where fiche.iduser = ?", nativeQuery = true)
    List<Fiche> listeFiche(Long id);
}
