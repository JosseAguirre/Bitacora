package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_catalogo database table.
 * 
 */
@Entity
@Table(name="adm_catalogo")
@NamedQuery(name="AdmCatalogo.findAll", query="SELECT a FROM AdmCatalogo a")
public class AdmCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo")
	private Integer idCatalogo;

	@Column(name="id_catalogo_padre")
	private Integer idCatalogoPadre;

	private String nombre;

	//bi-directional many-to-one association to AdmDetalleCatalogo
	@OneToMany(mappedBy="admCatalogo")
	private List<AdmDetalleCatalogo> admDetalleCatalogos;

	public AdmCatalogo() {
	}

	public Integer getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public Integer getIdCatalogoPadre() {
		return this.idCatalogoPadre;
	}

	public void setIdCatalogoPadre(Integer idCatalogoPadre) {
		this.idCatalogoPadre = idCatalogoPadre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AdmDetalleCatalogo> getAdmDetalleCatalogos() {
		return this.admDetalleCatalogos;
	}

	public void setAdmDetalleCatalogos(List<AdmDetalleCatalogo> admDetalleCatalogos) {
		this.admDetalleCatalogos = admDetalleCatalogos;
	}

	public AdmDetalleCatalogo addAdmDetalleCatalogo(AdmDetalleCatalogo admDetalleCatalogo) {
		getAdmDetalleCatalogos().add(admDetalleCatalogo);
		admDetalleCatalogo.setAdmCatalogo(this);

		return admDetalleCatalogo;
	}

	public AdmDetalleCatalogo removeAdmDetalleCatalogo(AdmDetalleCatalogo admDetalleCatalogo) {
		getAdmDetalleCatalogos().remove(admDetalleCatalogo);
		admDetalleCatalogo.setAdmCatalogo(null);

		return admDetalleCatalogo;
	}

}