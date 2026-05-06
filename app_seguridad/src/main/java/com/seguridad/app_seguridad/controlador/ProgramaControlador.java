package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Programa;
import com.seguridad.app_seguridad.modelo.servicio.ProgramaServicio;

@Controller
@RequestMapping("/programas")
public class ProgramaControlador {

    @Autowired
    private ProgramaServicio programaServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("programas", programaServicio.listarTodos());
        return "programas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("programa", new Programa());
        return "programa_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Programa programa) {
        programaServicio.guardar(programa);
        return "redirect:/programas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("programa", programaServicio.buscarPorId(id).orElse(null));
        return "programa_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        programaServicio.eliminar(id);
        return "redirect:/programas";
    }
}