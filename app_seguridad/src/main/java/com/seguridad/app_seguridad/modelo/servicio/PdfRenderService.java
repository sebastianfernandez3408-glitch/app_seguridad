package com.seguridad.app_seguridad.modelo.servicio;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

@Service
public class PdfRenderService {

    public List<byte[]> renderizarPaginasComoImagenes(byte[] pdfBytes) {
        try (PDDocument documento = Loader.loadPDF(pdfBytes)) {
            PDFRenderer renderer = new PDFRenderer(documento);
            List<byte[]> imagenes = new ArrayList<>();

            for (int pagina = 0; pagina < documento.getNumberOfPages(); pagina++) {
                BufferedImage imagen = renderer.renderImageWithDPI(pagina, 150, ImageType.RGB);
                try (ByteArrayOutputStream salida = new ByteArrayOutputStream()) {
                    ImageIO.write(imagen, "PNG", salida);
                    imagenes.add(salida.toByteArray());
                }
            }

            return imagenes;
        } catch (IOException e) {
            throw new RuntimeException("Error renderizando el PDF como imágenes", e);
        }
    }
}
