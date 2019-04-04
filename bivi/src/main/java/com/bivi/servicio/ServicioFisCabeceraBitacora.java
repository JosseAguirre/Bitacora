package com.bivi.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;

@Stateless
public class ServicioFisCabeceraBitacora {
	@PersistenceContext
	private EntityManager em;
	
	public void create(FisCabeceraBitacora fiscabeceraboitacora) {
		em.persist(fiscabeceraboitacora);
	}
	
	public void delete(FisCabeceraBitacora fiscabeceraboitacora) {
		em.remove(fiscabeceraboitacora);
	}
	
	public void update(FisCabeceraBitacora fiscabeceraboitacora) {
		em.merge(fiscabeceraboitacora);
	}
	
	@SuppressWarnings("unchecked")
	public List<FisCabeceraBitacora> findAll() {
		Query q = em.createQuery("select a from FisCabeceraBitacora a ");
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from FisCabeceraBitacora ");
		codigo = (Integer) q.getSingleResult();
		if(codigo == null){
			codigo = 1;
		}else{
			codigo++;
		}
		return codigo;
	}
	
	public FisCabeceraBitacora findOne(Integer idCabeceraBitacora){
		Query q = em.createQuery("select a from FisCabeceraBitacora a where a.idCabeceraBitacora= " + idCabeceraBitacora);
		return (FisCabeceraBitacora) q.getSingleResult();
	}

}
