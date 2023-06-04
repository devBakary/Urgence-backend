package com.application.urgence.models;

import lombok.Data;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      //@UniqueConstraint(columnNames = "email")
    })

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @Size(max = 50)
  @Email
  @Column(nullable = true)
  private String email;


  @Column(length = 8, nullable = false, unique = true)
  private Long numero;


  private String adresse;



  @Size(max = 120)
  private String password;

  private String img ;

  @OneToMany(mappedBy = "admin")
  private Collection<Publication> validerPublications = new ArrayList<>();
  @OneToMany(mappedBy = "utilisateur")
  private Collection<Publication> publications = new ArrayList<>();
  @OneToMany(mappedBy = "admin")
  private Collection<DemandeAide> validerDemandeAide = new ArrayList<>();
  @OneToMany(mappedBy = "utilisateur")
  private Collection<DemandeAide> demandeAide = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();




  public User() {
  }

  public User(String username, String email, String password, Long numero, String adresse) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.numero = numero;
    this.adresse = adresse;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
