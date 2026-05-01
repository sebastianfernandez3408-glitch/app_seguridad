package com.seguridad.app_seguridad.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguridad.app_seguridad.modelo.entidad.Instructor;

public interface InstructorRepositorio extends JpaRepository<Instructor, Long> {
}
