package com.seguridad.app_seguridad.util;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.seguridad.app_seguridad.modelo.entidad.Factura;

@Service
public class PdfGenerator {

    public byte[] generarFacturaPdf(Factura factura) {

        Document documento = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(documento, salida);
            documento.open();

            // TiULO
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("FACTURA DE SERVICIO", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);

            documento.add(titulo);
            documento.add(new Paragraph(" "));

            // DATOS DEL CLIENTE
            documento.add(new Paragraph("Cliente: " + factura.getCliente().getNombre()));
            documento.add(new Paragraph("Fecha: " + factura.getFecha()));
            documento.add(new Paragraph("Total: $" + factura.getTotal()));

            documento.add(new Paragraph(" "));

            //  TABLA
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);

            tabla.addCell("Descripción");
            tabla.addCell("Valor");

            tabla.addCell("Servicio contratado");
            tabla.addCell("$" + factura.getTotal());

            documento.add(tabla);

            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida.toByteArray();
    }
}