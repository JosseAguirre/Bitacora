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
public class ServicioAdmAgencia {
	
	@PersistenceContext
	private EntityManager em;

	public void create(AdmAgencia admagencia) {
		em.persist(admagencia);
	}

	public void delete(AdmAgencia admagencia) {
		em.remove(em.merge(admagencia));
	}

	public void update(AdmAgencia admagencia) {

		em.merge(admagencia);
	}
	
	
	
	@SuppressWarnings("unchecked") //Busca las agencias asignadas al usuario
	public List<AdmAgencia> buscaAgenciaCiudad(int idCiudad, AdmUsuario usuario) {
		Query q = em.createQuery("select f from AdmAgencia f  where f.idCiudadDc = " +idCiudad+" and f.idAgencia in (select a.idAgencia from FisEmpleadoAgencia a where a.idUsuario = "+usuario.getIdUsuario()+")");
		return q.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmAgencia> buscaAgenciaCiudad(int idCiudad, int idCliente) {
			
			List <AdmAgencia> lista = new ArrayList<>();
			Query q = em.createNativeQuery("SELECT  id_agencia, nombre FROM bivi.adm_agencia a where id_ciudad_dc = "+idCiudad+" and  id_cliente in "+
			"(select id_cliente from bivi.adm_cliente c where id_cliente = "+idCliente+"))");
			List<Object[]> rows =  q.getResultList();
			for(Object[] row : rows){
				
				AdmAgencia e = new AdmAgencia();
				e.setIdAgencia(Integer.parseInt(row[0].toString()));
				e.setNombre(row[1].toString());
				
				lista.add(e);
				
			}
			return lista;		
		}
	
	
	@SuppressWarnings("unchecked")
	public List<AdmAgencia> buscaAgencia(int idCliente) {
		Query q = em.createQuery("select f from AdmAgencia f  where f.idCliente in ("+idCliente+")");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmAgencia> findAgenciaCiudad(Integer idCiudad) {
		AdmUsuario us = (AdmUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		System.out.println("user es "+us.getUsuario());
		Query q = em.createQuery("select f.idAgencia from FisEmpleadoAgencia f where f.idempleado = (select a.idEmpleado from AdmUsuario a where a.idUsuario = "+us.getIdUsuario()+") and f.idAgencia.idCiudad ="+idCiudad);
		return  q.getResultList();
	}
	
	public Integer findPk() {

		Query q = em.createQuery("select max(id) from AdmAgencia");
		return (Integer) q.getSingleResult();

	}
	
	// GENERA UNA BUSQUEDA PARA OBTENER EL ID DE MAYOR VALOR
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from AdmAgencia ");
		codigo = (Integer) q.getSingleResult();
		if (codigo == null) {
			codigo = 1;
			// SI EL CODIGO ES MAYOR A NADA SE LE SUMA 1 Y ME DEVUELVE EL NUEVO CODIGO
		} else {
			codigo++;
		}
		return codigo;
	}
		
	
	// GENERO UNA BUSQUEDA PARA ENCONTAR EL ID DE LA AGENCIA
	public AdmAgencia findOne(Integer codigoadmagencia) {
		Query q = em.createQuery("select f from AdmAgencia f where f.idAgencia = " + codigoadmagencia);
		return (AdmAgencia) q.getSingleResult();
	}

	// GENERO UNA BUSQUEDA POR MEDIO DE UNA LISTA PARA FILTRAR UNA BUSQUEDA.
	public List<AdmAgencia> pornombresemejante(String nombre) {
		return em.createQuery("select f from AdmAgencia f where c.nombres like :nombres", AdmAgencia.class)
				.setParameter("nombres", "%" + nombre + "%").getResultList();
	}
		

}
