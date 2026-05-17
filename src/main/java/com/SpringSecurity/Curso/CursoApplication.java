package com.SpringSecurity.Curso;

import com.SpringSecurity.Curso.Enum.RoleEnum;
import com.SpringSecurity.Curso.entity.PermissionEntity;
import com.SpringSecurity.Curso.entity.RolesEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

    public CommandLineRunner init() {
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
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();





        };
    }

}
