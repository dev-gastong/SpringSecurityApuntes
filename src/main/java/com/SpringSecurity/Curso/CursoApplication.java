package com.SpringSecurity.Curso;

import com.SpringSecurity.Curso.Enum.RoleEnum;
import com.SpringSecurity.Curso.entity.PermissionEntity;
import com.SpringSecurity.Curso.entity.RolesEntity;
import com.SpringSecurity.Curso.entity.UserEntity;
import com.SpringSecurity.Curso.repository.PermissionRepository;
import com.SpringSecurity.Curso.repository.RoleRepository;
import com.SpringSecurity.Curso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CursoApplication {

    @Autowired
    PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

    @Bean
    public CommandLineRunner init(UserRepository userRepository, RoleRepository repository, PermissionRepository permissionRepository) {
        return args -> {

            // CREAR PERMISOS //

            // Se pueden crear tantos permisos como acciones específicas existan en el proyecto.

            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();


            // CREAR ROLES //


            RolesEntity roleAdmin = RolesEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();



            // Cliente debe tener roles CRUD especificos para sus operaciones (Compra, reserva, pedido, etc)
            RolesEntity roleCliente = RolesEntity.builder()
                    .roleEnum(RoleEnum.CLIENTE)
                    .permissionList(Set.of(readPermission, updatePermission))
                    .build();


            UserEntity userTonga = UserEntity.builder()
                    .username("tonga")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();


            UserEntity userNoTonga = UserEntity.builder()
                    .username("notonga")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(false)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleCliente))
                    .build();

            userRepository.saveAll(List.of(userTonga, userNoTonga));


        };
    }

}
