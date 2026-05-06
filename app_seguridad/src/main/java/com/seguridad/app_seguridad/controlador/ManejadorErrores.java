package com.seguridad.app_seguridad.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejar404(NoHandlerFoundException ex, Model model, HttpServletRequest request) {
        model.addAttribute("estado", 404);
        model.addAttribute("mensaje", "Página no encontrada");
        model.addAttribute("ruta", request.getRequestURI());
        model.addAttribute("detalles", "La ruta que intentas acceder no existe en la aplicación");
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String manejarAccesoDenegado(AccessDeniedException ex, Model model, HttpServletRequest request) {
        model.addAttribute("estado", 403);
        model.addAttribute("mensaje", "Acceso denegado");
        model.addAttribute("ruta", request.getRequestURI());
        model.addAttribute("detalles", "No tienes permisos para acceder a este recurso");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String manejarExcepcionGeneral(Exception ex, Model model, HttpServletRequest request) {
        model.addAttribute("estado", 500);
        model.addAttribute("mensaje", "Error interno del servidor");
        model.addAttribute("ruta", request.getRequestURI());
        model.addAttribute("detalles", ex.getMessage() != null ? ex.getMessage() : "Ha ocurrido un error inesperado");
        return "error";
    }
}
