package com.application.urgence.repository;

import com.application.urgence.models.Conseil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConseilRepository extends JpaRepository<Conseil, Long> {
}
