package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Contratacion;
import com.seguridad.app_seguridad.modelo.repositorio.ContratacionRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ContratacionServicio {

    @Autowired
    private ContratacionRepositorio contratacionRepositorio;

    public List<Contratacion> listarTodas() {
        return contratacionRepositorio.findAll();
    }

    public Optional<Contratacion> buscarPorId(Long id) {
        return contratacionRepositorio.findById(id);
    }

    @Transactional
    public Contratacion guardar(Contratacion c) {
        return contratacionRepositorio.save(c);
    }

    @Transactional
    public void eliminar(Long id) {
        contratacionRepositorio.deleteById(id);
    }
}