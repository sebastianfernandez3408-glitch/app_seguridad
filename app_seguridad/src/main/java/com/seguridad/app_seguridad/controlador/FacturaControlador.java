package com.seguridad.app_seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.servicio.FacturaServicio;

@Controller
@RequestMapping("/facturas")
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @GetMapping
    public String verFacturas(Model modelo) {
        modelo.addAttribute("facturas", facturaServicio.obtenerTodos());
        return "factura/ver";
    }

    @GetMapping("/nuevo")
    public String nuevaFactura(Model modelo) {
        modelo.addAttribute("factura", new Factura());
        return "factura/form";
    }

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura) {
        facturaServicio.guardar(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/editar/{id}")
    public String editarFactura(@PathVariable Long id, Model modelo) {
        Factura factura = facturaServicio.obtenerPorId(id);
        modelo.addAttribute("factura", factura);
        return "factura/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaServicio.eliminar(id);
        return "redirect:/facturas";
    }
}