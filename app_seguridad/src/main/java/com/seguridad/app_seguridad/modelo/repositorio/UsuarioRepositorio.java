package com.seguridad.app_seguridad.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}
