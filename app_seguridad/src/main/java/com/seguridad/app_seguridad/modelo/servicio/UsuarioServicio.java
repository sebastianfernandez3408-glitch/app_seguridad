package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    // 🔐 LOGIN SPRING SECURITY
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    String rol = (usuario.getRol() != null) 
    ? usuario.getRol().name() 
    : "ROLE_USER";

return new User(
    usuario.getUsername(),
    usuario.getPassword(),
    java.util.Collections.singletonList(
        new SimpleGrantedAuthority(rol)
    )
);
    }

    // 🔎 PARA USAR EN CONTROLADORES
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // CRUD BÁSICO
    public Usuario guardar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }

    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}