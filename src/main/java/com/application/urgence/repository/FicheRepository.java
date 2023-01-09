package com.application.urgence.repository;

import com.application.urgence.models.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheRepository extends JpaRepository<Fiche, Long> {

}
