package com.bivi.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;


@Stateless
public class ServicioAdmPuesto {
	
	@PersistenceContext
	private EntityManager em;
	
	public void create(AdmPuesto admpuesto) {
		em.persist(admpuesto);
	}
	
	public void delete(AdmPuesto admpuesto) {
		em.remove(admpuesto);
	}
	
	public void update(AdmPuesto admpuesto) {
		em.merge(admpuesto);
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmPuesto> findAll() {
		Query q = em.createQuery("select a from AdmPuesto a ");
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from AdmPuesto ");
		codigo = (Integer) q.getSingleResult();
		if(codigo == null){
			codigo = 1;
		}else{
			codigo++;
		}
		return codigo;
	}
	
	public AdmPuesto findOne(Integer idAgencia){
		Query q = em.createQuery("select a from AdmPuesto a where a.idAgencia = " + idAgencia);
		return (AdmPuesto) q.getSingleResult();
	}

}
