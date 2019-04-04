package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_agencia database table.
 * 
 */
@Entity
@Table(name="adm_agencia")
@NamedQuery(name="AdmAgencia.findAll", query="SELECT a FROM AdmAgencia a")
public class AdmAgencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_agencia")
	private Integer idAgencia;

	@Column(name="celular_administrador")
	private String celularAdministrador;

	private String direccion;

	@Column(name="id_ciudad")
	private Integer idCiudad;

	private String nombre;

	@Column(name="nombre_administrador")
	private String nombreAdministrador;

	//bi-directional many-to-one association to AdmCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private AdmCliente admCliente;

	//bi-directional many-to-one association to AdmPuesto
	@OneToMany(mappedBy="admAgencia")
	private List<AdmPuesto> admPuestos;

	//bi-directional many-to-one association to FisEmpleadoAgencia
	@OneToMany(mappedBy="admAgencia")
	private List<FisEmpleadoAgencia> fisEmpleadoAgencias;

	public AdmAgencia() {
	}

	public Integer getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(Integer idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getCelularAdministrador() {
		return this.celularAdministrador;
	}

	public void setCelularAdministrador(String celularAdministrador) {
		this.celularAdministrador = celularAdministrador;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreAdministrador() {
		return this.nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public AdmCliente getAdmCliente() {
		return this.admCliente;
	}

	public void setAdmCliente(AdmCliente admCliente) {
		this.admCliente = admCliente;
	}

	public List<AdmPuesto> getAdmPuestos() {
		return this.admPuestos;
	}

	public void setAdmPuestos(List<AdmPuesto> admPuestos) {
		this.admPuestos = admPuestos;
	}

	public AdmPuesto addAdmPuesto(AdmPuesto admPuesto) {
		getAdmPuestos().add(admPuesto);
		admPuesto.setAdmAgencia(this);

		return admPuesto;
	}

	public AdmPuesto removeAdmPuesto(AdmPuesto admPuesto) {
		getAdmPuestos().remove(admPuesto);
		admPuesto.setAdmAgencia(null);

		return admPuesto;
	}

	public List<FisEmpleadoAgencia> getFisEmpleadoAgencias() {
		return this.fisEmpleadoAgencias;
	}

	public void setFisEmpleadoAgencias(List<FisEmpleadoAgencia> fisEmpleadoAgencias) {
		this.fisEmpleadoAgencias = fisEmpleadoAgencias;
	}

	public FisEmpleadoAgencia addFisEmpleadoAgencia(FisEmpleadoAgencia fisEmpleadoAgencia) {
		getFisEmpleadoAgencias().add(fisEmpleadoAgencia);
		fisEmpleadoAgencia.setAdmAgencia(this);

		return fisEmpleadoAgencia;
	}

	public FisEmpleadoAgencia removeFisEmpleadoAgencia(FisEmpleadoAgencia fisEmpleadoAgencia) {
		getFisEmpleadoAgencias().remove(fisEmpleadoAgencia);
		fisEmpleadoAgencia.setAdmAgencia(null);

		return fisEmpleadoAgencia;
	}

}