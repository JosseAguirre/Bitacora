package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adm_empleado database table.
 * 
 */
@Entity
@Table(name="adm_empleado")
@NamedQuery(name="AdmEmpleado.findAll", query="SELECT a FROM AdmEmpleado a")
public class AdmEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_empleado")
	private Integer idEmpleado;

	private String apellidos;

	private String direccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String identificacion;

	@Column(name="nombre_contacto")
	private String nombreContacto;

	private String nombres;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_contacto")
	private String telefonoContacto;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	//bi-directional many-to-one association to AdmUsuario
	@OneToMany(mappedBy="admEmpleado")
	private List<AdmUsuario> admUsuarios;

	//bi-directional many-to-one association to FisEmpleadoAgencia
	@OneToMany(mappedBy="admEmpleado")
	private List<FisEmpleadoAgencia> fisEmpleadoAgencias;

	public AdmEmpleado() {
	}

	public Integer getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreContacto() {
		return this.nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public List<AdmUsuario> getAdmUsuarios() {
		return this.admUsuarios;
	}

	public void setAdmUsuarios(List<AdmUsuario> admUsuarios) {
		this.admUsuarios = admUsuarios;
	}

	public AdmUsuario addAdmUsuario(AdmUsuario admUsuario) {
		getAdmUsuarios().add(admUsuario);
		admUsuario.setAdmEmpleado(this);

		return admUsuario;
	}

	public AdmUsuario removeAdmUsuario(AdmUsuario admUsuario) {
		getAdmUsuarios().remove(admUsuario);
		admUsuario.setAdmEmpleado(null);

		return admUsuario;
	}

	public List<FisEmpleadoAgencia> getFisEmpleadoAgencias() {
		return this.fisEmpleadoAgencias;
	}

	public void setFisEmpleadoAgencias(List<FisEmpleadoAgencia> fisEmpleadoAgencias) {
		this.fisEmpleadoAgencias = fisEmpleadoAgencias;
	}

	public FisEmpleadoAgencia addFisEmpleadoAgencia(FisEmpleadoAgencia fisEmpleadoAgencia) {
		getFisEmpleadoAgencias().add(fisEmpleadoAgencia);
		fisEmpleadoAgencia.setAdmEmpleado(this);

		return fisEmpleadoAgencia;
	}

	public FisEmpleadoAgencia removeFisEmpleadoAgencia(FisEmpleadoAgencia fisEmpleadoAgencia) {
		getFisEmpleadoAgencias().remove(fisEmpleadoAgencia);
		fisEmpleadoAgencia.setAdmEmpleado(null);

		return fisEmpleadoAgencia;
	}

}