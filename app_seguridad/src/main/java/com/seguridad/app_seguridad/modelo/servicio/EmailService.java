package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PdfRenderService pdfRenderService;

    @Value("${spring.mail.username}")
    private String remitente;

    public void enviarFacturaPdf(String destinatario, String asunto, String cuerpo, byte[] pdfBytes, String nombreArchivo) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(remitente);
            helper.setTo(destinatario);
            helper.setCc(remitente);
            helper.setSubject(asunto);

            List<byte[]> imagenes = pdfRenderService.renderizarPaginasComoImagenes(pdfBytes);
            StringBuilder html = new StringBuilder();
            html.append("<p>").append(cuerpo).append("</p>");

            for (int i = 0; i < imagenes.size(); i++) {
                String cid = "pagina_" + (i + 1);
                helper.addInline(cid, new ByteArrayResource(imagenes.get(i)), "image/png");
                html.append("<div style=\"margin-bottom:16px;\">")
                    .append("<img src=\"cid:").append(cid).append("\" style=\"max-width:100%;height:auto;\" />")
                    .append("</div>");
            }

            html.append("<p>El PDF original va adjunto a este correo.</p>");
            helper.setText(html.toString(), true);
            helper.addAttachment(nombreArchivo, new ByteArrayResource(pdfBytes), "application/pdf");

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("No se pudo enviar la factura por correo: " + e.getMessage(), e);
        }
    }
}
