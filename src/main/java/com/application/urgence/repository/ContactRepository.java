package com.application.urgence.repository;

import com.application.urgence.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "select * from contact where contact.iduser = ?", nativeQuery = true)
    List<Contact> listeContact(Long id);
}
