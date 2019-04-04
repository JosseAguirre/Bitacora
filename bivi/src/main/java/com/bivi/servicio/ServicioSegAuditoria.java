package com.bivi.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;

@Stateless
public class ServicioSegAuditoria {
	@PersistenceContext
	private EntityManager em;
	
	public void create(SegAuditoria segauditoria) {
		em.persist(segauditoria);
	}
	
	public void delete(SegAuditoria segauditoria) {
		em.remove(segauditoria);
	}
	
	public void update(SegAuditoria segauditoria) {
		em.merge(segauditoria);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SegAuditoria> findAll() {
		Query q = em.createQuery("select a from SegAuditoria a");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SegAuditoria> buscaDetalle(Integer idAuditoria) {
		Query q = em.createQuery("select a from SegAuditoria a where a.idAuditoria ="+idAuditoria);
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from SegAuditoria ");
		codigo = (Integer) q.getSingleResult();
		if(codigo == null){
			codigo = 1;
		}else{
			codigo++;
		}
		return codigo;
	}
	
	public SegAuditoria findOne(Integer idUsuario){
		Query q = em.createQuery("select a from SegAuditoria a where a.idUsuario = " + idUsuario);
		return (SegAuditoria) q.getSingleResult();
	}
}
