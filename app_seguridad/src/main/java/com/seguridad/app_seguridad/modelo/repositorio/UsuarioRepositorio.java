package com.seguridad.app_seguridad.modelo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}