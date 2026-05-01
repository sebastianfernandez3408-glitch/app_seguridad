package com.seguridad.app_seguridad.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public Optional<Usuario> buscarPorNombreUsuario(String username) {
        return usuarioRepositorio.findByUsername(username);
    }
}