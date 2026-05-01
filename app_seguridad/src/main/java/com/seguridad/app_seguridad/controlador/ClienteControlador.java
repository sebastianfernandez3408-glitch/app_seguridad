package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Cliente;
import com.seguridad.app_seguridad.modelo.servicio.ClienteServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result) {
        if (result.hasErrors()) return "clientes/formulario";
        clienteServicio.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteServicio.buscarPorId(id).orElseThrow();
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteServicio.eliminar(id);
        return "redirect:/clientes";
    }
}