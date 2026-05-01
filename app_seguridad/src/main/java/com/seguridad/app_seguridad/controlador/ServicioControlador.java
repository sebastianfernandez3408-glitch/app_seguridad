package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Servicio;
import com.seguridad.app_seguridad.modelo.servicio.ServicioServicio;

@Controller
@RequestMapping("/servicios")
public class ServicioControlador {

    @Autowired
    private ServicioServicio servicioServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioServicio.listarTodos());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Servicio servicio) {
        servicioServicio.guardar(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicioServicio.eliminar(id);
        return "redirect:/servicios";
    }
}
