package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Contratacion;
import com.seguridad.app_seguridad.modelo.repositorio.ContratacionRepositorio;

@Service
public class ContratacionServicio {

    private final ContratacionRepositorio repo;

    public ContratacionServicio(ContratacionRepositorio repo) {
        this.repo = repo;
    }

    public List<Contratacion> listar() {
        return repo.findAll();
    }

    public Contratacion guardar(Contratacion c) {
        return repo.save(c);
    }

    public Contratacion buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
