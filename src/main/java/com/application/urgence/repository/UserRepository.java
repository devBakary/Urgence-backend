package com.application.urgence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.urgence.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  Boolean existsByNumero(Long numero);

  @Query(value="select * from users where username = ?", nativeQuery = true)
  public User findUsername(String username);

}
