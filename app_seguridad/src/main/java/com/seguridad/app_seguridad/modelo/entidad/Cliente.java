package com.seguridad.app_seguridad.modelo.entidad;

import java.util.List;

import com.seguridad.app_seguridad.modelo.entidad.enums.TipoCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    private String telefono;
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente")
    private List<Contratacion> contrataciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Contratacion> getContrataciones() {
		return contrataciones;
	}

	public void setContrataciones(List<Contratacion> contrataciones) {
		this.contrataciones = contrataciones;
	}

    // getters y setters
    
}