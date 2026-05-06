package com.seguridad.app_seguridad.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.servicio.UsuarioServicio;

@Controller
public class AppControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // 🔐 LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 🏠 PANEL
    @GetMapping("/panel")
    public String panel() {
        return "panel";
    }

    // 👤 PERFIL
    @GetMapping("/perfil")
    public String perfil(Principal principal, Model model) {

        if (principal != null) {
            String username = principal.getName();
            Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);
            model.addAttribute("usuario", usuario);
        }

        return "perfil";
    }
}
