package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adm_puestos database table.
 * 
 */
@Entity
@Table(name="adm_puestos")
@NamedQuery(name="AdmPuesto.findAll", query="SELECT a FROM AdmPuesto a")
public class AdmPuesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_puesto")
	private Integer idPuesto;

	@Column(name="id_tipo_puesto_catalogo")
	private Integer idTipoPuestoCatalogo;

	@Column(name="nombre_puesto")
	private String nombrePuesto;

	private String ubicacion;

	//bi-directional many-to-one association to AdmAgencia
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private AdmAgencia admAgencia;

	public AdmPuesto() {
	}

	public Integer getIdPuesto() {
		return this.idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getIdTipoPuestoCatalogo() {
		return this.idTipoPuestoCatalogo;
	}

	public void setIdTipoPuestoCatalogo(Integer idTipoPuestoCatalogo) {
		this.idTipoPuestoCatalogo = idTipoPuestoCatalogo;
	}

	public String getNombrePuesto() {
		return this.nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public AdmAgencia getAdmAgencia() {
		return this.admAgencia;
	}

	public void setAdmAgencia(AdmAgencia admAgencia) {
		this.admAgencia = admAgencia;
	}

}