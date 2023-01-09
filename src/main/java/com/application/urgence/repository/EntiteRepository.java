package com.application.urgence.repository;

import com.application.urgence.models.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {


}
