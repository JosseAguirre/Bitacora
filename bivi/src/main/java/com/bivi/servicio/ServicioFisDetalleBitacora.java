package com.bivi.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;


@Stateless
public class ServicioFisDetalleBitacora {
	@PersistenceContext
	private EntityManager em;
	
	public void create(FisDetalleBitacora fisdetallebitacora) {
		em.persist(fisdetallebitacora);
	}
	
	public void delete(FisDetalleBitacora fisdetallebitacora) {
		em.remove(fisdetallebitacora);
	}
	
	public void update(FisDetalleBitacora fisdetallebitacora) {
		em.merge(fisdetallebitacora);
	}
	
	@SuppressWarnings("unchecked")
	public List<FisDetalleBitacora> findAll() {
		Query q = em.createQuery("select a from FisDetalleBitacora a ");
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from FisDetalleBitacora ");
		codigo = (Integer) q.getSingleResult();
		if(codigo == null){
			codigo = 1;
		}else{
			codigo++;
		}
		return codigo;
	}
	
	public FisDetalleBitacora findOne(Integer idDetalleBitacora){
		Query q = em.createQuery("select a from FisDetalleBitacora a where a.idDetalleBitacora = " + idDetalleBitacora);
		return (FisDetalleBitacora) q.getSingleResult();
	}

}
