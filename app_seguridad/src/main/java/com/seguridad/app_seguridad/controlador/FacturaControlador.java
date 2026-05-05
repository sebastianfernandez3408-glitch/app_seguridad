package com.seguridad.app_seguridad.controlador;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.servicio.FacturaServicio;
import com.seguridad.app_seguridad.modelo.servicio.PdfFacturaServicio;

@Controller
@RequestMapping("/facturas")
public class FacturaControlador {

    private final FacturaServicio facturaServicio;
    private final PdfFacturaServicio pdfServicio;

    public FacturaControlador(FacturaServicio facturaServicio,
                              PdfFacturaServicio pdfServicio) {
        this.facturaServicio = facturaServicio;
        this.pdfServicio = pdfServicio;
    }

    //  LISTAR FACTURAS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaServicio.listar());
        return "facturas/lista";
    }

    //  GENERAR PDF
    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> generarPdf(@PathVariable Long id) {

        Factura factura = facturaServicio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        InputStream pdf = pdfServicio.generarFactura(factura);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=factura.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}