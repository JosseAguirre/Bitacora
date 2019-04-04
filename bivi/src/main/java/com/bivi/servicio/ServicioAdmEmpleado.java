package com.bivi.servicio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;


@Stateless
public class ServicioAdmEmpleado {
	
	@PersistenceContext
	private EntityManager em;

	public void create(AdmEmpleado admempleado) {
		em.persist(admempleado);
	}

	public void delete(AdmEmpleado admempleado) {
		em.remove(em.merge(admempleado));
	}

	public void update(AdmEmpleado admempleado) {

		em.merge(admempleado);

	}
	
	// trae todos los empleados 
	@SuppressWarnings("unchecked")
	public List<AdmEmpleado> findAll() { //lista de empleados para la creacion de usuarios
		List <AdmEmpleado> lista = new ArrayList<>();
		Query q = em.createNativeQuery("SELECT id_empleado, nombres, apellidos FROM bivi.adm_empleado ");
		List<Object[]> rows =  q.getResultList();
		for(Object[] row : rows){
			
			AdmEmpleado em = new AdmEmpleado();
			em.setIdEmpleado(Integer.parseInt(row[0].toString()));
			em.setNombres(row[1].toString());
			em.setApellidos(row[2].toString());
			
			AdmDetalleCatalogo estado = new  AdmDetalleCatalogo();
			estado.setIdDetalleCatalogo(Integer.parseInt(row[3].toString()));
			
			lista.add(em);
			
		}
		return lista;
	}
	
	// busca la pk mayor y le suma uno
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from AdmEmpleado");
		codigo = (Integer) q.getSingleResult();
		if (codigo == null) {
			codigo = 1;
		} else {
			codigo++;
		}
		return codigo;
	}
	
	// Busca el id seleccionado
	public AdmEmpleado findOne(Integer empleado) {
		Query q = em.createQuery("select f from AdmEmpleado f where f.idEmpleado = " + empleado);
		return (AdmEmpleado) q.getSingleResult();
	}
	
	// busca el id del empleado seleccionado
	public AdmEmpleado findempleado() {
		
		AdmUsuario us = (AdmUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		Query q = em.createQuery("select c from AdmEmpleado c where c.idEmpleado = (select a from AdmUsuario a where idUsuario = "+us.getIdUsuario()+")");
		return (AdmEmpleado) q.getSingleResult();
	}
	
	// usado para buscar el empleado por el nombre semejante
	public List<AdmEmpleado> pornombresemejante(String nombre) {
		return em.createQuery("select c from AdmEmpleado c where c.nombres like :nombres", AdmEmpleado.class)
				.setParameter("nombres", "%" + nombre + "%").getResultList();
	}

}
