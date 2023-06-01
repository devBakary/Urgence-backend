package com.application.urgence.repository;

import com.application.urgence.models.Type_violence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeViolenceRepository extends JpaRepository<Type_violence,Long> {


    Type_violence findByLibelle(String libelle);
}
