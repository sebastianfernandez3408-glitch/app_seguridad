package com.seguridad.app_seguridad.modelo.servicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.seguridad.app_seguridad.modelo.entidad.Factura;

@Service
public class PdfFacturaServicio {

    public ByteArrayInputStream generarFactura(Factura factura) {

        Document documento = new Document(PageSize.A4);
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(documento, salida);
            documento.open();

            // FUENTES
            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            //  TÍTULO CENTRADO
            Paragraph tituloParrafo = new Paragraph("FACTURA", titulo);
            tituloParrafo.setAlignment(Element.ALIGN_CENTER);
            documento.add(tituloParrafo);

            documento.add(new Paragraph(" ")); // espacio

            // ️ VALIDACIÓN PARA EVITAR NULL POINTER
            String nombreCliente = (factura.getCliente() != null)
                    ? factura.getCliente().getNombre()
                    : "SIN CLIENTE";

            //  CONTENIDO
            documento.add(new Paragraph("ID: " + factura.getId(), normal));
            documento.add(new Paragraph("Fecha: " + factura.getFecha(), normal));
            documento.add(new Paragraph("Cliente: " + nombreCliente, normal));
            documento.add(new Paragraph("Total: $" + factura.getTotal(), normal));

            documento.add(new Paragraph(" "));

            documento.add(new Paragraph("Gracias por su servicio", normal));

            documento.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }

        return new ByteArrayInputStream(salida.toByteArray());
    }
}
