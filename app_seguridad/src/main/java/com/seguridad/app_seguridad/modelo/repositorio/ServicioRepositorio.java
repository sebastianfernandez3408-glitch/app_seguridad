package com.seguridad.app_seguridad.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.app_seguridad.modelo.entidad.Servicio;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {
    List<Servicio> findByNombreContaining(String nombre);
}