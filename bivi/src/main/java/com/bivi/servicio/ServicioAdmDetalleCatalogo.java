package com.bivi.servicio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;



@Stateless
public class ServicioAdmDetalleCatalogo {
	
	@PersistenceContext
	private EntityManager em;

	public void create(AdmDetalleCatalogo detallecatalogo) {
		em.persist(detallecatalogo);
	}

	public void delete(AdmDetalleCatalogo detallecatalogo) {
		em.remove(em.merge(detallecatalogo));
	}

	public void update(AdmDetalleCatalogo detallecatalogo) {

		em.merge(detallecatalogo);

	}
	
	//Metodo para obtener las ciudades de la tabla Admdetallecatálogo
	@SuppressWarnings("unchecked")
	public List<AdmDetalleCatalogo> ciudades() {
		Query q = em.createQuery("select c from AdmDetalleCatalogo c where c.idCatalogo = 3 ");
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from AdmDetalleCatalogo ");
		codigo = (Integer) q.getSingleResult();
		if (codigo == null) {
			codigo = 1;
		} else {
			codigo++;
		}
		return codigo;
	}
	
	//Metodo para obtener el iddetallecatalogo//
	public AdmDetalleCatalogo findOne(Integer iddetallecatalogo) {
		Query q = em.createQuery("select g from AdmDetalleCatalogo g where g.idDetalleCatalogo = "+ iddetallecatalogo);
		return (AdmDetalleCatalogo) q.getSingleResult();
	}
	
	//Metodo para obtener toda la información de la tabla Admdetallecatálogo
	@SuppressWarnings("unchecked")
	public List<AdmDetalleCatalogo> findAll() {
		Query q = em.createQuery("select c from AdmDetalleCatalogo c");
		return q.getResultList();
	}

}
