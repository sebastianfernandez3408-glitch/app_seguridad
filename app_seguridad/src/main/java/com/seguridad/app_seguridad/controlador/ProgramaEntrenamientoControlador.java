package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.ProgramaEntrenamiento;
import com.seguridad.app_seguridad.modelo.servicio.ProgramaEntrenamientoServicio;

@Controller
@RequestMapping("/programas")
public class ProgramaEntrenamientoControlador {

    @Autowired
    private ProgramaEntrenamientoServicio servicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("programas", servicio.listarTodos());
        return "programas/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("programa", new ProgramaEntrenamiento());
        return "programas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ProgramaEntrenamiento p) {
        servicio.guardar(p);
        return "redirect:/programas";
    }
}
