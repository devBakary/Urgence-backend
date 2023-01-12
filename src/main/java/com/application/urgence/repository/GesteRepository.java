package com.application.urgence.repository;

import com.application.urgence.models.Gestes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GesteRepository extends JpaRepository<Gestes, Long> {
}
