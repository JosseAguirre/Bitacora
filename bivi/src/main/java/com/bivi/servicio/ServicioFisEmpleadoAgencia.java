package com.bivi.servicio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bivi.modelo.*;


@Stateless
public class ServicioFisEmpleadoAgencia {
	@PersistenceContext
	private EntityManager em;
	
	public void create(FisEmpleadoAgencia fisempleadoagencia) {
		em.persist(fisempleadoagencia);
	}
	
	public void delete(FisEmpleadoAgencia fisempleadoagencia) {
		em.remove(fisempleadoagencia);
	}
	
	public void update(FisEmpleadoAgencia fisempleadoagencia) {
		em.merge(fisempleadoagencia);
	}
	
	public void eliminaAgenciaAsiganda(int idAgencia){
		em.createNativeQuery("delete from bivi.fis_empleado_agencia where id_agencia = "+idAgencia+"").executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<FisEmpleadoAgencia> buscaAgenciaCiudadCliente(AdmUsuario us) {
			
			List <FisEmpleadoAgencia> lista = new ArrayList<>();
			Query q = em.createNativeQuery("select ua.id_empleado_agencia, ag.nombre, em.nombres, em.apellidos, em.identificacion, cl2.razon_social from bivi.fis_empleado_agencia as ea "+
			"inner join bivi.adm_agencia as ag on ag.id_agencia = ea.id_agencia "+
			"inner join bivi.adm_empleado as em on em.id_empleado = ea.id_empleado "+
			"inner join bivi.adm_usuario as us on us.id_empleado = em.id_empleado "+
			"inner join bivi.adm_cliente as cl on cl.id_cliente = ag.id_cliente "+
			"inner join bivi.adm_cliente as cl2 on cl2.id_cliente = cl.id_cliente_padre "+
			"where us.id_usuario = "+us.getIdUsuario()+"");
			List<Object[]> rows =  q.getResultList();
			for(Object[] row : rows){
				
				FisEmpleadoAgencia e = new FisEmpleadoAgencia();
				e.setIdEmpleadoAgencia(Integer.parseInt(row[0].toString()));
				
				AdmAgencia a = new AdmAgencia();
				a.setNombre(row[1].toString());
				
				AdmEmpleado emp = new AdmEmpleado();
				emp.setNombres(row[2].toString());
				
				AdmEmpleado emp2 = new AdmEmpleado();
				emp2.setApellidos(row[3].toString());
				
				AdmEmpleado emp3 = new AdmEmpleado();
				emp3.setIdentificacion(row[4].toString());
				
				AdmCliente c = new AdmCliente();
				c.setRazonSocial(row[5].toString());
				
				
				a.setAdmCliente(c);
				
				e.setAdmAgencia(a);
				
				lista.add(e);
				
			}
			return lista;
		}
	
	@SuppressWarnings("unchecked")
	public List<FisEmpleadoAgencia> findAll() {
		Query q = em.createQuery("select c from FisEmpleadoAgencia c ");
		return q.getResultList();
	}
	
	public Integer getPK() {
		Integer codigo = 0;
		Query q = em.createQuery("select max(id) from FisEmpleadoAgencia ");
		codigo = (Integer) q.getSingleResult();
		if(codigo == null){
			codigo = 1;
		}else{
			codigo++;
		}
		return codigo;
	}

}
