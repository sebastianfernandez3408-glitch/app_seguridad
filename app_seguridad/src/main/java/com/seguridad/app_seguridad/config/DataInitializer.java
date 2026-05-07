package com.seguridad.app_seguridad.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepositorio usuarioRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ServicioRepositorio servicioRepositorio;
    private final ContratacionRepositorio contratacionRepositorio;
    private final PagoRepositorio pagoRepositorio;
    private final FacturaRepositorio facturaRepositorio;
    private final InstructorRepositorio instructorRepositorio;
    private final ProgramaRepositorio programaRepositorio;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UsuarioRepositorio usuarioRepositorio,
            ClienteRepositorio clienteRepositorio,
            ServicioRepositorio servicioRepositorio,
            ContratacionRepositorio contratacionRepositorio,
            PagoRepositorio pagoRepositorio,
            FacturaRepositorio facturaRepositorio,
            InstructorRepositorio instructorRepositorio,
            ProgramaRepositorio programaRepositorio,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepositorio = usuarioRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.servicioRepositorio = servicioRepositorio;
        this.contratacionRepositorio = contratacionRepositorio;
        this.pagoRepositorio = pagoRepositorio;
        this.facturaRepositorio = facturaRepositorio;
        this.instructorRepositorio = instructorRepositorio;
        this.programaRepositorio = programaRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // EVITA DUPLICADOS
        if (usuarioRepositorio.count() > 0) {
            System.out.println("⚠️ DATOS YA EXISTEN, NO SE INSERTAN");
            return;
        }

        // =========================
        // USUARIO ADMIN (Acceso Total)
        // =========================
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRol(Rol.ROLE_ADMIN);

        usuarioRepositorio.save(admin);

        // =========================
        // USUARIO NORMAL (Acceso Limitado)
        // =========================
        Usuario user = new Usuario();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRol(Rol.ROLE_USER);

        usuarioRepositorio.save(user);

        // =========================
        // CLIENTE 1 (Asociado a ADMIN)
        // =========================
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Carlos Ramirez");
        cliente1.setDocumento("123456789");
        cliente1.setTelefono("3001234567");
        cliente1.setEmail("carlos@gmail.com");
        cliente1.setTipoCliente(TipoCliente.PREMIUM);
        cliente1.setUsuario(admin);

        clienteRepositorio.save(cliente1);

        // =========================
        // CLIENTE 2 (Asociado a USER)
        // =========================
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Maria Gomez");
        cliente2.setDocumento("987654321");
        cliente2.setTelefono("3019876543");
        cliente2.setEmail("maria@gmail.com");
        cliente2.setTipoCliente(TipoCliente.NORMAL);
        cliente2.setUsuario(user);

        clienteRepositorio.save(cliente2);

        // =========================
        // SERVICIO 1
        // =========================
        Servicio servicio1 = new Servicio();
        servicio1.setNombre("Vigilancia Privada");
        servicio1.setTipo("Vigilancia");
        servicio1.setUbicacion("Medellín");
        servicio1.setPrecio(1500000.0);
        servicio1.setDuracion("30 días");
        servicio1.setDescripcion("Servicio de vigilancia empresarial");

        servicioRepositorio.save(servicio1);

        // =========================
        // SERVICIO 2
        // =========================
        Servicio servicio2 = new Servicio();
        servicio2.setNombre("Patrullaje Nocturno");
        servicio2.setTipo("Patrullaje");
        servicio2.setUbicacion("Bogotá");
        servicio2.setPrecio(2500000.0);
        servicio2.setDuracion("15 días");
        servicio2.setDescripcion("Patrullaje nocturno para zonas privadas");

        servicioRepositorio.save(servicio2);

        // =========================
        // CONTRATACION
        // =========================
        Contratacion contratacion = new Contratacion();
        contratacion.setCliente(cliente1);
        contratacion.setServicio(servicio1);
        contratacion.setEstado(EstadoContratacion.ACTIVA);
        contratacion.setFecha(LocalDate.now());

        contratacionRepositorio.save(contratacion);

        // =========================
        // PAGO
        // =========================
        Pago pago = new Pago();
        pago.setCliente(cliente1);
        pago.setContratacion(contratacion);
        pago.setMonto(1500000.0);
        pago.setMetodo("Transferencia");
        pago.setFecha(LocalDate.now());

        pagoRepositorio.save(pago);

        // =========================
        // FACTURA
        // =========================
        Factura factura = new Factura();
        factura.setCliente(cliente1);
        factura.setFecha(LocalDate.now());
        factura.setTotal(1500000.0);

        facturaRepositorio.save(factura);

        // =========================
        // INSTRUCTOR
        // =========================
        Instructor instructor = new Instructor();
        instructor.setNombre("Carlos Instructor");
        instructor.setEspecialidad("Defensa Personal");
        instructor.setTelefono("3005551234");
        instructor.setEmail("instructor@gmail.com");
        instructor.setDocumento("111222333");
        instructor.setAniosExperiencia(10);

        instructorRepositorio.save(instructor);

        // =========================
        // PROGRAMA
        // =========================
        Programa programa = new Programa();
        programa.setNombre("Defensa Personal Básica");
        programa.setDescripcion("Curso de defensa personal para principiantes");
        programa.setDuracion("4 semanas");
        programa.setCosto(100000.0);
        programa.setInstructor(instructor);

        programaRepositorio.save(programa);

        System.out.println("==================================");
        System.out.println("✅ DATOS INICIALES CREADOS");
        System.out.println("==================================");
        System.out.println("👑 ADMIN -> admin / admin123 (Acceso Total)");
        System.out.println("👤 USER  -> user / user123 (Acceso Limitado)");
        System.out.println("==================================");
    }
}
