package com.seguridad.app_seguridad.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seguridad.app_seguridad.modelo.entidad.Cliente;
import com.seguridad.app_seguridad.modelo.entidad.Contratacion;
import com.seguridad.app_seguridad.modelo.entidad.Factura;
import com.seguridad.app_seguridad.modelo.entidad.Instructor;
import com.seguridad.app_seguridad.modelo.entidad.Pago;
import com.seguridad.app_seguridad.modelo.entidad.Programa;
import com.seguridad.app_seguridad.modelo.entidad.Servicio;
import com.seguridad.app_seguridad.modelo.entidad.Usuario;
import com.seguridad.app_seguridad.modelo.entidad.enums.EstadoContratacion;
import com.seguridad.app_seguridad.modelo.entidad.enums.Rol;
import com.seguridad.app_seguridad.modelo.entidad.enums.TipoCliente;
import com.seguridad.app_seguridad.modelo.repositorio.ClienteRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.ContratacionRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.FacturaRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.InstructorRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.PagoRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.ProgramaRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.ServicioRepositorio;
import com.seguridad.app_seguridad.modelo.repositorio.UsuarioRepositorio;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(
            UsuarioRepositorio usuarioRepo,
            ClienteRepositorio clienteRepo,
            ServicioRepositorio servicioRepo,
            ContratacionRepositorio contratacionRepo,
            PagoRepositorio pagoRepo,
            FacturaRepositorio facturaRepo,
            InstructorRepositorio instructorRepo,
            ProgramaRepositorio programaRepo,
            PasswordEncoder encoder 
    ) {
        return args -> {

            // 🔐 SOLO CREAR SI NO EXISTEN
            if (usuarioRepo.count() == 0) {

                // 👑 ADMIN
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol(Rol.ROLE_ADMIN);
                usuarioRepo.save(admin);

                // 👤 USER
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRol(Rol.ROLE_USER);
                usuarioRepo.save(user);

                // 👤 CLIENTE
                Cliente cliente = new Cliente();
                cliente.setNombre("Juan Perez");
                cliente.setDocumento("123456");
                cliente.setTelefono("3001234567");
                cliente.setEmail("juan@email.com");
                cliente.setTipoCliente(TipoCliente.NORMAL);
                cliente.setUsuario(admin);
                clienteRepo.save(cliente);

                // 🛡️ SERVICIO
                Servicio servicio = new Servicio();
                servicio.setNombre("Vigilancia");
                servicio.setTipo("Seguridad");
                servicio.setPrecio(50000);
                servicio.setUbicacion("Medellin");
                servicioRepo.save(servicio);

                // 📄 CONTRATACIÓN
                Contratacion con = new Contratacion();
                con.setCliente(cliente);
                con.setServicio(servicio);
                con.setEstado(EstadoContratacion.ACTIVA);
                con.setFecha(LocalDate.now());
                contratacionRepo.save(con);

                // 💰 PAGO
                Pago pago = new Pago();
                pago.setCliente(cliente);
                pago.setContratacion(con);
                pago.setMonto(50000);
                pago.setMetodo("Efectivo");
                pago.setFecha(LocalDate.now());
                pagoRepo.save(pago);

                // 🧾 FACTURA
                Factura factura = new Factura();
                factura.setCliente(cliente);
                factura.setTotal(50000);
                factura.setFecha(LocalDate.now());
                facturaRepo.save(factura);

                // 🧠 INSTRUCTOR
                Instructor inst = new Instructor();
                inst.setNombre("Carlos Instructor");
                instructorRepo.save(inst);

                // 📚 PROGRAMA
                Programa prog = new Programa();
                prog.setNombre("Defensa Personal");
                prog.setCosto(100000);
                prog.setInstructor(inst);
                programaRepo.save(prog);

                System.out.println("✅ DATOS INICIALES CARGADOS");
            } else {
                System.out.println("⚠️ DATOS YA EXISTEN, NO SE INSERTAN");
            }
        };
    }
}