package com.seguridad.app_seguridad.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.servicio.FacturaServicio;
import com.seguridad.app_seguridad.util.PdfGenerator;

@Controller
@RequestMapping("/facturas")
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping
    public String verFacturas(Model modelo) {
        try {
            List<Factura> facturas = facturaServicio.obtenerTodos();
            System.out.println("Facturas encontradas: " + facturas.size());
            modelo.addAttribute("facturas", facturas);
            return "facturas";
        } catch (Exception e) {
            System.out.println("Error en verFacturas: " + e.getMessage());
            e.printStackTrace();
            modelo.addAttribute("error", "Error al cargar facturas: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/nuevo")
    public String nuevaFactura(Model modelo) {
        modelo.addAttribute("factura", new Factura());
        return "factura_form";
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
        return "factura_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaServicio.eliminar(id);
        return "redirect:/facturas";
    }

    @GetMapping("/descargar/{id}")
    public ResponseEntity<byte[]> descargarFacturaPdf(@PathVariable Long id) {
        Factura factura = facturaServicio.obtenerPorId(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = pdfGenerator.generarFacturaPdf(factura);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}