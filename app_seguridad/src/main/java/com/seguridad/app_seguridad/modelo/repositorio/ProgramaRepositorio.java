package com.seguridad.app_seguridad.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.app_seguridad.modelo.entidad.Programa;

@Repository
public interface ProgramaRepositorio extends JpaRepository<Programa, Long>{

}
