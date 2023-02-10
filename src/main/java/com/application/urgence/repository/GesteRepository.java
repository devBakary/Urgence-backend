package com.application.urgence.repository;

import com.application.urgence.models.Contact;
import com.application.urgence.models.Gestes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GesteRepository extends JpaRepository<Gestes, Long> {

    @Query(value = "select * from gestes where id = ?", nativeQuery = true)
    List<Gestes> listeGeste(Long id);
}
