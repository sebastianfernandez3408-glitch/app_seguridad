package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.repositorio.FacturaRepositorio;

@Service
public class FacturaServicio {

    private final FacturaRepositorio facturaRepositorio;

    public FacturaServicio(FacturaRepositorio facturaRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Factura> listar() {
        return facturaRepositorio.findAll();
    }

    @Transactional
    public Factura guardar(Factura factura) {
        return facturaRepositorio.save(factura);
    }

    @Transactional(readOnly = true)
    public Optional<Factura> buscarPorId(Long id) {
        return facturaRepositorio.findById(id);
    }

    @Transactional
    public void eliminar(Long id) {
        facturaRepositorio.deleteById(id);
    }
}