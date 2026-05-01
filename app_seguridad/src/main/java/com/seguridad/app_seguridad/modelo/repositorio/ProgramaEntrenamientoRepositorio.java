package com.seguridad.app_seguridad.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.app_seguridad.modelo.entidad.ProgramaEntrenamiento;

@Repository
public interface ProgramaEntrenamientoRepositorio extends JpaRepository<ProgramaEntrenamiento, Long>{

}
