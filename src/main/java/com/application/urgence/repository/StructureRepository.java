package com.application.urgence.repository;

import com.application.urgence.models.Structure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StructureRepository extends JpaRepository<Structure,Long> {
    Structure findByNom(String nom);
    boolean existsByNom(String nom);


}
