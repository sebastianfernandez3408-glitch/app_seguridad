package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.servicio.UsuarioServicio;

@Controller
public class ControladorAutenticacion {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String paginaLogin(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model modelo) {
        
        if (error != null) {
            modelo.addAttribute("mensajeError", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            modelo.addAttribute("mensajeSalida", "Has cerrado sesión correctamente");
        }
        
        return "login";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/panel";
    }

    @GetMapping("/panel")
    public String panel(Model modelo) {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = autenticacion.getName();
        
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        modelo.addAttribute("usuario", usuario);
        return "panel";
    }

    @GetMapping("/perfil")
    public String perfil(Model modelo) {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = autenticacion.getName();
        
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        modelo.addAttribute("usuario", usuario);
        return "perfil";
    }

    @GetMapping("/clientes")
    public String clientes(Model modelo) {
        modelo.addAttribute("titulo", "Gestión de Clientes");
        return "clientes";
    }
}
