package com.application.urgence.payload.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;


  @Size(max = 50)
  //@Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


  @Column(length = 8, nullable = false, unique = true)
  private Long numero;

  @Column(name = "adresse", nullable = true)
  private String adresse;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Long getNumero(){return numero;}

  public void setNumero(Long numero) {
    this.numero = numero;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
