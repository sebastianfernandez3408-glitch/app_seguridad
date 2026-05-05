package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Programa;
import com.seguridad.app_seguridad.modelo.repositorio.ProgramaRepositorio;

@Service
public class ProgramaServicio {

    private final ProgramaRepositorio repo;

    public ProgramaServicio(ProgramaRepositorio repo) {
        this.repo = repo;
    }

    public List<Programa> listar() {
        return repo.findAll();
    }

    public Programa guardar(Programa p) {
        return repo.save(p);
    }
}
    

