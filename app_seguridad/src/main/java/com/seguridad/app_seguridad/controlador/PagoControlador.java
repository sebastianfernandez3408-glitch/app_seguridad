package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Pago;
import com.seguridad.app_seguridad.modelo.servicio.PagoServicio;

@Controller
@RequestMapping("/pagos")
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pagos", pagoServicio.listarTodos());
        return "pagos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pago", new Pago());
        return "pagos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pago pago) {
        pagoServicio.guardar(pago);
        return "redirect:/pagos";
    }
}