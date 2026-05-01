package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Servicio;
import com.seguridad.app_seguridad.modelo.repositorio.ServicioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ServicioServicio {

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    public List<Servicio> listarTodos() {
        return servicioRepositorio.findAll();
    }

    public Optional<Servicio> buscarPorId(Long id) {
        return servicioRepositorio.findById(id);
    }

    @Transactional
    public Servicio guardar(Servicio servicio) {
        return servicioRepositorio.save(servicio);
    }

    @Transactional
    public Servicio actualizar(Long id, Servicio servicio) {
        servicio.setId(id);
        return servicioRepositorio.save(servicio);
    }

    @Transactional
    public void eliminar(Long id) {
        servicioRepositorio.deleteById(id);
    }

    public List<Servicio> buscarPorNombre(String nombre) {
        return servicioRepositorio.findAll()
                .stream()
                .filter(s -> s.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}