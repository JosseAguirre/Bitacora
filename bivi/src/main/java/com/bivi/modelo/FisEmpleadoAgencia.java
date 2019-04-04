package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fis_empleado_agencia database table.
 * 
 */
@Entity
@Table(name="fis_empleado_agencia")
@NamedQuery(name="FisEmpleadoAgencia.findAll", query="SELECT f FROM FisEmpleadoAgencia f")
public class FisEmpleadoAgencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_empleado_agencia")
	private Integer idEmpleadoAgencia;

	//bi-directional many-to-one association to AdmAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private AdmAgencia admAgencia;

	//bi-directional many-to-one association to AdmEmpleado
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private AdmEmpleado admEmpleado;

	public FisEmpleadoAgencia() {
	}

	public Integer getIdEmpleadoAgencia() {
		return this.idEmpleadoAgencia;
	}

	public void setIdEmpleadoAgencia(Integer idEmpleadoAgencia) {
		this.idEmpleadoAgencia = idEmpleadoAgencia;
	}

	public AdmAgencia getAdmAgencia() {
		return this.admAgencia;
	}

	public void setAdmAgencia(AdmAgencia admAgencia) {
		this.admAgencia = admAgencia;
	}

	public AdmEmpleado getAdmEmpleado() {
		return this.admEmpleado;
	}

	public void setAdmEmpleado(AdmEmpleado admEmpleado) {
		this.admEmpleado = admEmpleado;
	}

}