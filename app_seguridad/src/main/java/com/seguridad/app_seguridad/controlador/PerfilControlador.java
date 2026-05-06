package com.seguridad.app_seguridad.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilControlador {

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }
}