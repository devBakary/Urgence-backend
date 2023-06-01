package com.application.urgence.repository;

import com.application.urgence.models.Avis;
import com.application.urgence.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis,Long> {
}
