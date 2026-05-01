package com.seguridad.app_seguridad.modelo.entidad;

import com.seguridad.app_seguridad.modelo.entidad.enums.EstadoContratacion;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrataciones")
public class Contratacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servicio servicio;

    @Enumerated(EnumType.STRING)
    private EstadoContratacion estado;

    private java.time.LocalDate fecha;

    @OneToMany(mappedBy = "contratacion")
    private java.util.List<Pago> pagos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public EstadoContratacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoContratacion estado) {
		this.estado = estado;
	}

	public java.time.LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(java.time.LocalDate fecha) {
		this.fecha = fecha;
	}

	public java.util.List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(java.util.List<Pago> pagos) {
		this.pagos = pagos;
	}

    // getters y setters

	
}