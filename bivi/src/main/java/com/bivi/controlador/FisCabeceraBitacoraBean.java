package com.bivi.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import com.bivi.modelo.*;
import com.bivi.servicio.*;

@ManagedBean
@ViewScoped
public class FisCabeceraBitacoraBean implements  Serializable {

	private static final long serialVersionUID = 1L;
	private FisCabeceraBitacora servicio;
	private FisCabeceraBitacora servicioSeleccionado;
	private List<FisCabeceraBitacora> listaServicio;
	private List<FisCabeceraBitacora> servicioFiltrado;
	private boolean bandera;
	
	@EJB
	private ServicioFisCabeceraBitacora servicioCabecera;
	
	@PostConstruct
    public void init() {
        cancelar();
        consultalistaServicio();
    }

	private void cancelar() {
		servicio = new FisCabeceraBitacora();
		bandera = false;
	}
	
	public void guardar() {

        try {
        	servicio.setIdCabeceraBitacora(servicioCabecera.getPK());
       
        	servicioCabecera.create(servicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso", "Se ha guardado don exito "));
            consultalistaServicio();
            cancelar();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Se ha producido un error al guardar"));

        }
    }
	
	//metodo para eliminar una cabecera 
    
    public void eliminar() {
    	if(servicioSeleccionado == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {	
			servicio = servicioSeleccionado;
		
			servicioCabecera.update(servicio);
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos Eliminado Correctamente "));		
		consultalistaServicio();
		cancelar();		
		}

    }
    
    public void actualizar() {
		
    	servicioCabecera.update(servicio);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario Actualizado Correctamente "));
		cancelar();
		
	}
    
    public void modificar(){
		if(servicioSeleccionado == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {
		bandera =	true;
		servicio = servicioSeleccionado;
		
		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosservicio').show();");
		}
	}
    
    public void nuevo(){	
		bandera =	false;
		servicio = new FisCabeceraBitacora();
		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosservicio').show();");
	}
    
    public static void resetarFormulario() {
        RequestContext.getCurrentInstance().reset("frmCrear");
	}
    
  //metodo que llena una lista con los registros provenientes d ela base de datos
    
    public void consultalistaServicio() {

        listaServicio = new ArrayList<>(); // Creo una lista para mostrar todo los cabeceras en el datatable
        listaServicio = servicioCabecera.findAll();     
    }
    
  //metodo que verifica si la accion es de guardar uno nuevo o actualizar uno ya existente
    public void persistir() {

        if (bandera == true) {

            actualizar();

        } else {
            guardar();

        }
    }
}
