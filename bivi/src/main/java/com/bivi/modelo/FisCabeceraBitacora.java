package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fis_cabecera_bitacora database table.
 * 
 */
@Entity
@Table(name="fis_cabecera_bitacora")
@NamedQuery(name="FisCabeceraBitacora.findAll", query="SELECT f FROM FisCabeceraBitacora f")
public class FisCabeceraBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cabecera_bitacora")
	private Integer idCabeceraBitacora;

	@Column(name="id_puesto")
	private Integer idPuesto;

	//bi-directional many-to-one association to FisDetalleBitacora
	@OneToMany(mappedBy="fisCabeceraBitacora")
	private List<FisDetalleBitacora> fisDetalleBitacoras;

	public FisCabeceraBitacora() {
	}

	public Integer getIdCabeceraBitacora() {
		return this.idCabeceraBitacora;
	}

	public void setIdCabeceraBitacora(Integer idCabeceraBitacora) {
		this.idCabeceraBitacora = idCabeceraBitacora;
	}

	public Integer getIdPuesto() {
		return this.idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public List<FisDetalleBitacora> getFisDetalleBitacoras() {
		return this.fisDetalleBitacoras;
	}

	public void setFisDetalleBitacoras(List<FisDetalleBitacora> fisDetalleBitacoras) {
		this.fisDetalleBitacoras = fisDetalleBitacoras;
	}

	public FisDetalleBitacora addFisDetalleBitacora(FisDetalleBitacora fisDetalleBitacora) {
		getFisDetalleBitacoras().add(fisDetalleBitacora);
		fisDetalleBitacora.setFisCabeceraBitacora(this);

		return fisDetalleBitacora;
	}

	public FisDetalleBitacora removeFisDetalleBitacora(FisDetalleBitacora fisDetalleBitacora) {
		getFisDetalleBitacoras().remove(fisDetalleBitacora);
		fisDetalleBitacora.setFisCabeceraBitacora(null);

		return fisDetalleBitacora;
	}

}