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

    // LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //PANEL
    @GetMapping("/panel")
    public String panel(Principal principal, Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(principal.getName());
        model.addAttribute("usuario", usuario);
        return "panel";
    }


}
