package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Pago;
import com.seguridad.app_seguridad.modelo.repositorio.PagoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PagoServicio {

    @Autowired
    private PagoRepositorio pagoRepositorio;

    public List<Pago> listarTodos() {
        return pagoRepositorio.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepositorio.findById(id);
    }

    @Transactional
    public Pago guardar(Pago pago) {
        return pagoRepositorio.save(pago);
    }

    @Transactional
    public void eliminar(Long id) {
        pagoRepositorio.deleteById(id);
    }
}