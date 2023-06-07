package com.application.urgence.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.application.urgence.models.*;
import com.application.urgence.payload.request.LoginRequest;
import com.application.urgence.payload.request.SignupRequest;
import com.application.urgence.payload.response.JwtResponse;
import com.application.urgence.payload.response.MessageResponse;
import com.application.urgence.repository.FicheRepository;
import com.application.urgence.repository.RoleRepository;
import com.application.urgence.repository.UserRepository;
import com.application.urgence.security.EmailConstructor;
import com.application.urgence.security.FileUploadUtil;
import com.application.urgence.security.jwt.JwtUtils;
import com.application.urgence.security.services.UserDetailsImpl;
import com.application.urgence.security.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/urgence/auth")
public class AuthController {

  @Autowired
  private EmailConstructor emailConstructor;

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  FicheRepository ficheRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserService userService;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

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

  // pour le user
  @PostMapping("/inscription")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body("Cet utilisateur existe deja! Choisissez un autre");
    }else if (signUpRequest.getEmail() != null && !signUpRequest.getEmail().isEmpty() && userRepository.existsByEmail(signUpRequest.getEmail())){
      return ResponseEntity.badRequest()
              .body("Cet Email est deja utilisé");
    }else if(signUpRequest.getEmail().isEmpty()){
      signUpRequest.setEmail(null);
    }
    else if(userRepository.existsByNumero(signUpRequest.getNumero())){
      return ResponseEntity.badRequest()
              .body("Cet Numero a deja un compte");
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

    Fiche fiche = new Fiche();
    fiche.setEmail(signUpRequest.getEmail());
    fiche.setAdresse(signUpRequest.getAdresse());
    fiche.setNumero(user.getNumero());


    user.setRoles(roles);
    userRepository.save(user);
    fiche.setUser(user);
    ficheRepository.save(fiche);

    return ResponseEntity.ok().body(" Utilisateur enregistré avec succes!");
  }


  //Pour le responsable
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
    mailSender.send(emailConstructor.constructNewUserEmail(user));

    return ResponseEntity.ok(new MessageResponse("Responsable enregistré avec succes!"));
  }

  @PutMapping("modifier/{id}")
  public User UpdateUser(@Valid @RequestBody SignupRequest signupRequest, @PathVariable Long id){
    User userU =userRepository.findById(id).get();


    if (signupRequest != null) {
      if (signupRequest.getUsername() != null && !signupRequest.getUsername().trim().equals("")) {
        userU.setUsername(signupRequest.getUsername());
      }

      if (signupRequest.getEmail() != null && !signupRequest.getEmail().trim().equals("")) {
        userU.setEmail(signupRequest.getEmail());
      }

      if (signupRequest.getNumero() != null && !signupRequest.getNumero().equals("")) {
        userU.setNumero(signupRequest.getNumero());
      }
      if (signupRequest.getAdresse() != null && !signupRequest.getAdresse().trim().equals("")) {
        userU.setAdresse(signupRequest.getAdresse());
      }
      /*if (signupRequest.getPassword() != null) {
        userU.setPassword(encoder.encode(signupRequest.getPassword()));
      }*/
    }


    return userRepository.save(userU);
  }

  @DeleteMapping("/supprimer/{id}")
  public String supprimer(@PathVariable Long id){
    userRepository.deleteById(id);
    return  "supprimé!";
  }

  @GetMapping("/liste")
  public List<User> listeUser(){
    return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
  }

  @GetMapping("/liste/{id}")
  public List<User> listeU(@PathVariable Long id){
    User is = userRepository.findById(id).get();
    return userRepository.list(id);
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




  //mot de passe oublié
  @PostMapping("/resetpassword")
  public ResponseEntity<String> resetPassword(@RequestBody User users) {
    User user = userRepository.findUsername(users.getUsername());
    System.out.println(user);
    if (user == null) {
      return new ResponseEntity<String>("Username introuvable !", HttpStatus.BAD_REQUEST);
    }
    if(user.getEmail() == null ){
      return new ResponseEntity<String>("Nous n'avons pas trouvé de mail correspondant pour cet username !", HttpStatus.BAD_REQUEST);
    }
    userService.resetPassword(user);
    return new ResponseEntity<String>(users.getUsername(), HttpStatus.OK);
  }

  //reinitialiser password
  @PostMapping("/changePassword/{username}")
  public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request, @PathVariable String username) {
    //String numeroOrEmail = request.get("numeroOrEmail");
    User user = userRepository.findUsername(username);
    if (user == null) {
      return new ResponseEntity<>("Utilisateur introuvable !", HttpStatus.BAD_REQUEST);
    }
    String currentPassword = request.get("currentpassword");

    String newPassword = request.get("newpassword");
    String confirmpassword = request.get("confirmpassword");
    if (!newPassword.equals(confirmpassword)) {
      return new ResponseEntity<>(" Les mots de passe ne correspondent pas !", HttpStatus.BAD_REQUEST);
    }
    String userPassword = user.getPassword();
    //System.out.println(userPassword + "shnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" );


      if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
        if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
         // System.out.println(currentPassword +" " + userPassword +" notre vericationnnnnnnnnnnnn");
          userService.updateUserPassword(user, newPassword);

          return new ResponseEntity<>(" Mot de passe changé avec succès !", HttpStatus.OK);
        }
      }
        return new ResponseEntity<>(" Incorrect Mot de passe !", HttpStatus.BAD_REQUEST);

  }


  //pour mettre ajour le profil
  @PutMapping("/image/{id}")
  public String upImg(@Param("img")MultipartFile img, Long id) throws IOException {
    User us = userRepository.findById(id).get();
    User upIm = new User();
    String imge = us.getUsername() +img.getOriginalFilename();
    if (us.getImg() != null){
      upIm.setImg(imge);
    }
    userRepository.saveAndFlush(upIm);
    String upload = "C:\\Users\\bddiakite\\Desktop\\urgence-projet\\assets\\images";
    FileUploadUtil.saveFile(upload, imge, img);

    return "modifier avec s";
  }

}
