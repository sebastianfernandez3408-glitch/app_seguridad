package com.seguridad.app_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.repositorio.UsuarioRepositorio;

@Configuration
public class InicializadorDatos {

    @Bean
    public org.springframework.boot.CommandLineRunner init(
            UsuarioRepositorio repo,
            PasswordEncoder encoder) {

        return args -> {
            if (repo.count() == 0) {

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");

                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRol("ROLE_USER");

                repo.save(admin);
                repo.save(user);
            }
        };
    }
}