package com.seguridad.app_seguridad.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.entidad.enums.Rol;
import com.seguridad.app_seguridad.modelo.repositorio.UsuarioRepositorio;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UsuarioRepositorio usuarioRepo, PasswordEncoder encoder) {
        return args -> {

            if (usuarioRepo.count() == 0) {

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol(Rol.ROLE_ADMIN);

                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRol(Rol.ROLE_USER);

                usuarioRepo.save(admin);
                usuarioRepo.save(user);

                System.out.println("🔥 Usuarios creados correctamente");
            }
        };
    }
}