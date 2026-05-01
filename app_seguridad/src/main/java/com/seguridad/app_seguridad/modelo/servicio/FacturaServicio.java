package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.repositorio.FacturaRepositorio;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    public List<Factura> listarTodas() {
        return facturaRepositorio.findAll();
    }

    public Factura guardar(Factura factura) {
        return facturaRepositorio.save(factura);
    }
}