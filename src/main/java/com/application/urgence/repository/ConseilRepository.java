package com.application.urgence.repository;

import com.application.urgence.models.Conseil;
import com.application.urgence.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConseilRepository extends JpaRepository<Conseil, Long> {

}
