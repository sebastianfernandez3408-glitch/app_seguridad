package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Pago;
import com.seguridad.app_seguridad.modelo.repositorio.PagoRepositorio;

@Service
public class PagoServicio {

    private final PagoRepositorio repo;

    public PagoServicio(PagoRepositorio repo) {
        this.repo = repo;
    }

    public List<Pago> listar() {
        return repo.findAll();
    }

    public Pago guardar(Pago p) {
        return repo.save(p);
    }
}