package com.seguridad.app_seguridad.modelo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.app_seguridad.modelo.entidad.Cliente;
import com.seguridad.app_seguridad.modelo.repositorio.ClienteRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepositorio.findById(id);
    }

    @Transactional
    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente cliente) {
        cliente.setId(id);
        return clienteRepositorio.save(cliente);
    }

    @Transactional
    public void eliminar(Long id) {
        clienteRepositorio.deleteById(id);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepositorio.findAll()
                .stream()
                .filter(c -> c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
}