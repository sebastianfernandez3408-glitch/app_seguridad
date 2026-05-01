package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.ProgramaEntrenamiento;
import com.seguridad.app_seguridad.modelo.repositorio.ProgramaEntrenamientoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ProgramaEntrenamientoServicio {

    @Autowired
    private ProgramaEntrenamientoRepositorio repo;

    public List<ProgramaEntrenamiento> listarTodos() {
        return repo.findAll();
    }

    public Optional<ProgramaEntrenamiento> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public ProgramaEntrenamiento guardar(ProgramaEntrenamiento p) {
        return repo.save(p);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}