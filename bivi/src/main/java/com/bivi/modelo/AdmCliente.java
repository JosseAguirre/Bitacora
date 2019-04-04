package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_cliente database table.
 * 
 */
@Entity
@Table(name="adm_cliente")
@NamedQuery(name="AdmCliente.findAll", query="SELECT a FROM AdmCliente a")
public class AdmCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente")
	private Integer idCliente;

	private String administrador;

	private String direccion;

	@Column(name="id_ciudad")
	private Integer idCiudad;

	@Column(name="id_cliente_padre")
	private Integer idClientePadre;

	private String identificacion;

	@Column(name="nombre_comercial")
	private String nombreComercial;

	@Column(name="razon_social")
	private String razonSocial;

	@Column(name="telefono_administrador")
	private String telefonoAdministrador;

	//bi-directional many-to-one association to AdmAgencia
	@OneToMany(mappedBy="admCliente")
	private List<AdmAgencia> admAgencias;

	public AdmCliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Integer getIdClientePadre() {
		return this.idClientePadre;
	}

	public void setIdClientePadre(Integer idClientePadre) {
		this.idClientePadre = idClientePadre;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTelefonoAdministrador() {
		return this.telefonoAdministrador;
	}

	public void setTelefonoAdministrador(String telefonoAdministrador) {
		this.telefonoAdministrador = telefonoAdministrador;
	}

	public List<AdmAgencia> getAdmAgencias() {
		return this.admAgencias;
	}

	public void setAdmAgencias(List<AdmAgencia> admAgencias) {
		this.admAgencias = admAgencias;
	}

	public AdmAgencia addAdmAgencia(AdmAgencia admAgencia) {
		getAdmAgencias().add(admAgencia);
		admAgencia.setAdmCliente(this);

		return admAgencia;
	}

	public AdmAgencia removeAdmAgencia(AdmAgencia admAgencia) {
		getAdmAgencias().remove(admAgencia);
		admAgencia.setAdmCliente(null);

		return admAgencia;
	}

}