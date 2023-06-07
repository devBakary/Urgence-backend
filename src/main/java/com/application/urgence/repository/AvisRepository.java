package com.application.urgence.repository;

import com.application.urgence.models.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis,Long> {
}
