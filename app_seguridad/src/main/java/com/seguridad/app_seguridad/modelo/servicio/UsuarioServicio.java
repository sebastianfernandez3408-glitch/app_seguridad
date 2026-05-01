package com.seguridad.app_seguridad.modelo.servicio;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singleton(() -> usuario.getRol().name())
        );
    }
}