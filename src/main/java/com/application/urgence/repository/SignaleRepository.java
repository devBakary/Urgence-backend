package com.application.urgence.repository;

import com.application.urgence.models.SignalerDanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignaleRepository extends JpaRepository<SignalerDanger, Long> {
}
