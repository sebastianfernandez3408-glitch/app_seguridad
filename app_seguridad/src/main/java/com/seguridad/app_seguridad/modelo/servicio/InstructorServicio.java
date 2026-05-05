package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Instructor;
import com.seguridad.app_seguridad.modelo.repositorio.InstructorRepositorio;

@Service
public class InstructorServicio {

    private final InstructorRepositorio repo;

    public InstructorServicio(InstructorRepositorio repo) {
        this.repo = repo;
    }

    public List<Instructor> listar() {
        return repo.findAll();
    }
}