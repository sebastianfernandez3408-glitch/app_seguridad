package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.servicio.FacturaServicio;

@Controller
@RequestMapping("/facturas")
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaServicio.listarTodas());
        return "facturas/lista";
    }
}
