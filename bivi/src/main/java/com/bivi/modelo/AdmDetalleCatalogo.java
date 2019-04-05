package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the adm_detalle_catalogo database table.
 * 
 */
@Entity
@Table(name="adm_detalle_catalogo")
@NamedQuery(name="AdmDetalleCatalogo.findAll", query="SELECT a FROM AdmDetalleCatalogo a")
public class AdmDetalleCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_detalle_catalogo")
	private Integer idDetalleCatalogo;

	private String descripcion;

	//bi-directional many-to-one association to AdmDetalleCatalogo
	@ManyToOne
	@JoinColumn(name="id_detalle_catalogo_padre")
	private AdmDetalleCatalogo idDetalleCatalogoPadre;

	//bi-directional many-to-one association to AdmCatalogo
	@ManyToOne
	@JoinColumn(name="id_catalogo")
	private AdmCatalogo admCatalogo;

	public AdmDetalleCatalogo() {
	}

	public Integer getIdDetalleCatalogo() {
		return this.idDetalleCatalogo;
	}

	public void setIdDetalleCatalogo(Integer idDetalleCatalogo) {
		this.idDetalleCatalogo = idDetalleCatalogo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AdmDetalleCatalogo getIdDetalleCatalogoPadre() {
		return this.idDetalleCatalogoPadre;
	}

	public void setIdDetalleCatalogoPadre(AdmDetalleCatalogo idDetalleCatalogoPadre) {
		this.idDetalleCatalogoPadre = idDetalleCatalogoPadre;
	}

	public AdmCatalogo getAdmCatalogo() {
		return this.admCatalogo;
	}

	public void setAdmCatalogo(AdmCatalogo admCatalogo) {
		this.admCatalogo = admCatalogo;
	}

}