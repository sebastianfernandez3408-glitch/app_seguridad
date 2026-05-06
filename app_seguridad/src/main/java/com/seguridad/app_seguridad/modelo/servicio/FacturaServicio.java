package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.repositorio.FacturaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    // 📌 Obtener todas las facturas
    public List<Factura> obtenerTodos() {
        return facturaRepositorio.findAll();
    }

    // 📌 Obtener factura por ID (con control de error)
    public Factura obtenerPorId(Long id) {
        return facturaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    // 📌 Guardar o actualizar factura
    @Transactional
    public Factura guardar(Factura factura) {

        if (factura == null) {
            throw new RuntimeException("La factura no puede ser null");
        }

        return facturaRepositorio.save(factura);
    }

    // 📌 Eliminar factura con validación
    @Transactional
    public void eliminar(Long id) {

        if (!facturaRepositorio.existsById(id)) {
            throw new RuntimeException("No existe la factura con ID: " + id);
        }

        facturaRepositorio.deleteById(id);
    }

    // 📊 Contar facturas
    public long contar() {
        return facturaRepositorio.count();
    }
}