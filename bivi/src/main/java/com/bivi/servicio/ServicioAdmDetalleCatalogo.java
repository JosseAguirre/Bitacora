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

}
