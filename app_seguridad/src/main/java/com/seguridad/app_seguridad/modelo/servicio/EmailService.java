package com.seguridad.app_seguridad.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void enviarFacturaPdf(String destinatario, String asunto, String cuerpo, byte[] pdfBytes, String nombreArchivo) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo, false);
            helper.addAttachment(nombreArchivo, new ByteArrayResource(pdfBytes));

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("No se pudo enviar la factura por correo: " + e.getMessage(), e);
        }
    }
}
