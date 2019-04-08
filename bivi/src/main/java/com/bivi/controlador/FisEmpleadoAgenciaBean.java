package com.bivi.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import com.bivi.modelo.*;
import com.bivi.servicio.*;


@ManagedBean
@ViewScoped
public class FisEmpleadoAgenciaBean implements  Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<AdmAgencia> listaAgenciaOrigen;
	private List<AdmAgencia> listaAgenciaDestino;

	private  DualListModel<AdmAgencia> listaModelAgencia ;

	@EJB
	private ServicioAdmAgencia servicioAgencia;
	
	public void init() {
		cancelar ();
		}
		
	public void cancelar() {
		
		listaAgenciaOrigen =  new ArrayList<>();
		listaAgenciaDestino =  new ArrayList<>();
		listaModelAgencia = new DualListModel <AdmAgencia>();
		consultar();
	}
	
	public void agenciasAsignacion(){ // busca las agencias asignadas al empleado
	    if (listaAgenciaDestino.isEmpty()){
	    	listaModelAgencia = new DualListModel<AdmAgencia>(listaAgenciaOrigen, listaAgenciaDestino);
	    }
	    	else {
	    		List <AdmAgencia> agenciasDisponibles ;
	    		agenciasDisponibles = new ArrayList<>();
	    		listaModelAgencia = new DualListModel<AdmAgencia>(agenciasDisponibles, listaAgenciaDestino);
	   }
	}
	
	public void guardar() {
			
		
	}

	
	public void eliminar() {
			
		
	}


	public void actualizar() {
			
		
	}

	
	public void consultar() {
		// TODO Auto-generated method stub
		
	}
	
}
