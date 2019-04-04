package com.bivi.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the seg_auditoria database table.
 * 
 */
@Entity
@Table(name="seg_auditoria")
@NamedQuery(name="SegAuditoria.findAll", query="SELECT s FROM SegAuditoria s")
public class SegAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp fecha;

	@Column(name="id_auditoria")
	private Integer idAuditoria;

	@Column(name="id_usuario")
	private Integer idUsuario;

	private String modulo;

	private String operacion;

	public SegAuditoria() {
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getIdAuditoria() {
		return this.idAuditoria;
	}

	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}