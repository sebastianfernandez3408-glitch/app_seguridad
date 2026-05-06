package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.servicio.ContratacionServicio;

@Controller
@RequestMapping("/contrataciones")
public class ContratacionControlador {

    @Autowired
    private ContratacionServicio contratacionServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("contrataciones", contratacionServicio.listarTodas());
        return "contrataciones";
    }
}