package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Instructor;
import com.seguridad.app_seguridad.modelo.repositorio.InstructorRepositorio;

@Service
public class InstructorServicio {

    private final InstructorRepositorio repo;

    public InstructorServicio(InstructorRepositorio repo) {
        this.repo = repo;
    }

    // Listar todos
    public List<Instructor> listar() {
        return repo.findAll();
    }

    // Guardar 
    public void guardar(Instructor instructor) {
        repo.save(instructor);
    }

    // Buscar por ID 
    public Optional<Instructor> buscarPorId(Long id) {
        return repo.findById(id);
    }

    // Eliminar
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}