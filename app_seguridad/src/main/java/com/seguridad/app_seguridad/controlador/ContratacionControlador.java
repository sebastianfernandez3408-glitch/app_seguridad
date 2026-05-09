package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.seguridad.app_seguridad.modelo.entidad.Contratacion;
import com.seguridad.app_seguridad.modelo.entidad.enums.EstadoContratacion;
import com.seguridad.app_seguridad.modelo.servicio.ClienteServicio;
import com.seguridad.app_seguridad.modelo.servicio.ContratacionServicio;
import com.seguridad.app_seguridad.modelo.servicio.ServicioServicio;

@Controller
@RequestMapping("/contrataciones")
public class ContratacionControlador {

    @Autowired
    private ContratacionServicio contratacionServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("contrataciones", contratacionServicio.listarTodas());
        return "contrataciones";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("contratacion", new Contratacion());
        model.addAttribute("clientes", clienteServicio.listarTodos());
        model.addAttribute("servicios", servicioServicio.listarTodos());
        model.addAttribute("estados", EstadoContratacion.values());
        return "contratacion_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contratacion contratacion) {
        contratacionServicio.guardar(contratacion);
        return "redirect:/contrataciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("contratacion", contratacionServicio.buscarPorId(id).orElse(null));
        model.addAttribute("clientes", clienteServicio.listarTodos());
        model.addAttribute("servicios", servicioServicio.listarTodos());
        model.addAttribute("estados", EstadoContratacion.values());
        return "contratacion_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        contratacionServicio.eliminar(id);
        return "redirect:/contrataciones";
    }
}