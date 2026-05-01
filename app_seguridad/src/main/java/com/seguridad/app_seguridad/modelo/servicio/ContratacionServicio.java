package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Contratacion;
import com.seguridad.app_seguridad.modelo.entidad.EstadoContratacion;
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

    @Transactional
   public Contratacion cambiarEstado(Long id, String estado) {

    Contratacion c = contratacionRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Contratación no encontrada"));

    EstadoContratacion nuevoEstado = EstadoContratacion.valueOf(estado.toUpperCase());

    c.setEstado(nuevoEstado);

    return contratacionRepositorio.save(c);
} 
}