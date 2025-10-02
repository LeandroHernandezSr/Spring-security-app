package com.app.SpringSecurityApp;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.SpringSecurityApp.entities.PermissionEntity;
import com.app.SpringSecurityApp.entities.RoleEntity;
import com.app.SpringSecurityApp.entities.RoleEnum;
import com.app.SpringSecurityApp.entities.UserEntity;
import com.app.SpringSecurityApp.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args->{
			PermissionEntity permissionEntity=new PermissionEntity();
			permissionEntity.setName("CREATE");


			PermissionEntity permissionEntityRead=new PermissionEntity();
			permissionEntityRead.setName("READ");

			RoleEntity roleEntity=new RoleEntity();
			roleEntity.setPermissionEntities(Set.of(permissionEntity,permissionEntityRead));
			roleEntity.setRoleEnum(RoleEnum.ADMIN);

			
			UserEntity usuarioAdmin=new UserEntity();
			usuarioAdmin.setUsername("Leandro");
			usuarioAdmin.setPassword("12345");
			usuarioAdmin.setAccountNoExpired(true);
			usuarioAdmin.setAccountNoLocked(true);
			usuarioAdmin.setCredentialNoExpired(true);
			usuarioAdmin.setRol(Set.of(roleEntity));

			userRepository.save(usuarioAdmin);

		};
	}
}
