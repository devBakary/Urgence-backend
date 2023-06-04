package com.application.urgence;

import com.application.urgence.models.ERole;
import com.application.urgence.models.Role;
import com.application.urgence.models.User;
import com.application.urgence.repository.RoleRepository;
import com.application.urgence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
@Getter
@Setter
public class SpringBootUrgenceApplication implements CommandLineRunner {

	@Autowired
	 PasswordEncoder encoder;


	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootUrgenceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		//VERIFICATION DE L'EXISTANCE DU ROLE ADMIN AVANT SA CREATION
		if (roleRepository.findAll().size() == 0){
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_SUPER));
		}
		if (userRepository.findAll().size() == 0){
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_SUPER).get();
			roles.add(role);
			User admin = new User(
					"black",
					"black@gmail.com",
					encoder.encode( "12345678"),
					90675632L, "aci"
					);
			admin.setRoles(roles);
			userRepository.save(admin);


		}
	}
}
