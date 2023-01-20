package com.application.urgence.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.application.urgence.models.ERole;
import com.application.urgence.models.Entite;
import com.application.urgence.models.Role;
import com.application.urgence.models.User;
import com.application.urgence.payload.request.LoginRequest;
import com.application.urgence.payload.request.SignupRequest;
import com.application.urgence.payload.response.JwtResponse;
import com.application.urgence.payload.response.MessageResponse;
import com.application.urgence.repository.RoleRepository;
import com.application.urgence.repository.UserRepository;
import com.application.urgence.security.jwt.JwtUtils;
import com.application.urgence.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/connexion")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(),
                         userDetails.getEmail(),
                         userDetails.getAdresse(),
                         userDetails.getNumero(),
                         roles));
  }

  @PostMapping("/inscription")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Cet utilisateur existe deja!"));
    }



    // Creation du comte de l'utilisateur
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getNumero(),
            signUpRequest.getAdresse()
    );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role non trouvé"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
            roles.add(adminRole);

            break;
          case "super":
            Role modRole = roleRepository.findByName(ERole.ROLE_SUPER)
                    .orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succes!"));
  }


  @PostMapping("/inscrire")
  public ResponseEntity<?> registerRespon(@Valid @RequestBody SignupRequest signUpRequest) {
    if (
          userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Cet utilisateur existe deja!"));
    }



    // Creation du comte de l'utilisateur
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getNumero(),
               signUpRequest.getAdresse()
               );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();


      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new RuntimeException("Error: Role non trouvé"));
      roles.add(adminRole);

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Responsable enregistré avec succes!"));
  }

  @PutMapping("modifier/{id}")
  public User UpdateUser(@Valid @RequestBody SignupRequest signupRequest, @PathVariable Long id){
    User userU =userRepository.findById(id).get();

    userU.setUsername(signupRequest.getUsername());
    userU.setEmail(signupRequest.getEmail());
    userU.setNumero(signupRequest.getNumero());
    userU.setAdresse(signupRequest.getAdresse());
    userU.setPassword(encoder.encode((signupRequest.getPassword())));

    return userRepository.saveAndFlush(userU);
  }

  @DeleteMapping("/supprimer/{id}")
  public String supprimer(@PathVariable Long id){
    userRepository.deleteById(id);
    return  "supprimé!";
  }

  @GetMapping("/liste")
  public List<User> listeUser(){
    return userRepository.findAll();
  }

  @GetMapping("/rliste")
  public List<Role> list(){
    return roleRepository.findAll();
  }

  //afficher commentaire par id region
  @GetMapping("/user/{username}")
  public User afficherparUsername(@PathVariable String username) {
    return userRepository.findUsername(username);
  }

}
