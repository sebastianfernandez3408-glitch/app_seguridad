package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Programa;
import com.seguridad.app_seguridad.modelo.repositorio.ProgramaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ProgramaServicio {

    @Autowired
    private ProgramaRepositorio programaRepositorio;

    public List<Programa> listarTodos() {
        return programaRepositorio.findAll();
    }

    public Optional<Programa> buscarPorId(Long id) {
        return programaRepositorio.findById(id);
    }

    @Transactional
    public Programa guardar(Programa programa) {
        return programaRepositorio.save(programa);
    }

    @Transactional
    public void eliminar(Long id) {
        programaRepositorio.deleteById(id);
    }
}