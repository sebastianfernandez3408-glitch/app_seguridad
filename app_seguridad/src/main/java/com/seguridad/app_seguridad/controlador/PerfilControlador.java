package com.seguridad.app_seguridad.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.servicio.UsuarioServicio;

@Controller
public class PerfilControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil")
    public String perfil(Principal principal, Model model) {

        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(principal.getName());
        model.addAttribute("usuario", usuario);

        return "perfil";
    }
}