package com.seguridad.app_seguridad.controlador;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.servicio.FacturaServicio;
import com.seguridad.app_seguridad.modelo.servicio.PdfFacturaServicio;

@RestController
@RequestMapping("/api/facturas")
public class FacturaControladorApi {

    @Autowired
    private FacturaServicio facturaServicio;

    @Autowired
    private PdfFacturaServicio pdfFacturaServicio;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> generarPdf(@PathVariable Long id) {

        Factura factura = facturaServicio.obtenerPorId(id);

        ByteArrayInputStream pdf = pdfFacturaServicio.generarFactura(factura);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=factura.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}