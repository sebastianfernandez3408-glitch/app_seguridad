package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.seguridad.app_seguridad.modelo.entidad.Instructor;
import com.seguridad.app_seguridad.modelo.servicio.InstructorServicio;

@Controller
@RequestMapping("/instructores")
public class InstructorControlador {

    @Autowired
    private InstructorServicio instructorServicio;

    // Listar todos los instructores
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("instructores", instructorServicio.listar());
        return "instructores";  // → busca instructores.html
    }

    // Mostrar formulario para crear nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor_form";  // → busca instructor_form.html
    }

    // Guardar (crear o editar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Instructor instructor) {
        instructorServicio.guardar(instructor);
        return "redirect:/instructores";
    }

    // Cargar formulario con datos para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorServicio.buscarPorId(id).orElse(null));
        return "instructor_form";
    }

    // Eliminar instructor
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        instructorServicio.eliminar(id);
        return "redirect:/instructores";
    }
}