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

import com.bivi.modelo.AdmAgencia;
import com.bivi.modelo.AdmCliente;
import com.bivi.modelo.AdmDetalleCatalogo;
import com.bivi.servicio.ServicioAdmAgencia;
import com.bivi.servicio.ServicioAdmCliente;
import com.bivi.servicio.ServicioAdmDetalleCatalogo;


@ManagedBean
@ViewScoped
public class AdmAgenciaBean implements  Serializable {
	
	private static final long serialVersionUID = 1L;
    private AdmAgencia agencia;
    private AdmAgencia agenciaSeleccionada;
    private List<AdmAgencia> listaAgencia;
    private List<AdmAgencia> agenciaFiltrada;
    
    private List<AdmCliente> listaClientePadre; //Padres	
    private List<AdmCliente> listaCliente; //Hijos
    
    private List<AdmDetalleCatalogo> listaCiudad;
    
    
    private boolean bandera;
    private int idCliente;
    private int idClientePadre;
    private int idCiudad;
    private int idAgencia;
    
    @EJB
    private ServicioAdmAgencia servicioAgencia;

    @EJB
    private ServicioAdmCliente servicioCliente;
    @EJB
    private ServicioAdmDetalleCatalogo servicioDetalleCatalogo;
    
    
    
    @PostConstruct
    public void init() {
        cancelar();
        consultaListaClientePadre();
        
    }
    
    public void cancelar() {
    	agencia = new AdmAgencia();
    	
        bandera = false;
         idCliente = -1;
	     idClientePadre = -1;
	    
	     idCiudad = -1;
	    idAgencia = -1;

    }
    
    
    public void consultaListaClientePadre() {
    	listaClientePadre = new ArrayList<>(); 
    	listaClientePadre = servicioCliente.buscaClientePadre();
    	
    }
    
    
    public void consultaListaAgencia() {
    	listaAgencia = new ArrayList<>(); 
    	listaAgencia = servicioAgencia.buscaAgencia(idCiudad);	
    }
    
    public void consultaListaCiudad() {
    	listaCiudad = new ArrayList<>(); 
    	listaCiudad = servicioDetalleCatalogo.ciudades();
    	
    }
    
    public void consultaListaCliente() {
    	
    	listaCliente = new ArrayList<>(); 
    	listaCliente = servicioCliente.buscaCliente(idClientePadre);
    }

    
    public void guardar() {
    	
    	AdmCliente idCliente = new AdmCliente();
    	idCliente.setIdCliente(this.idCliente);
    	
    	AdmDetalleCatalogo idCiudad = new AdmDetalleCatalogo ();
    	idCiudad.setIdDetalleCatalogo(this.idCiudad);
    	
    	AdmAgencia agencia = new AdmAgencia();
    	agencia.setIdAgencia(this.idAgencia);
    	

        try {
        	this.agencia.setIdAgencia(servicioAgencia.getPK());	 
        	this.agencia.setAdmCliente(idCliente); 
        	this.agencia.setIdCiudad(idCiudad);
        	
        	if(agencia.getIdAgencia() == 0){
        		this.agencia.setAdmCliente(null);
        	}
        	servicioAgencia.create(this.agencia);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso", "Se ha guardado don exito "));
            consultaListaAgencia();
            cancelar();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Se ha producido un error al guardar"));

        }
    }
    
    public void eliminar() {
    	if(agenciaSeleccionada == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {	
			agencia = agenciaSeleccionada;
		
			servicioAgencia.update(agencia);
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos Eliminado Correctamente "));		
		consultaListaAgencia();
		cancelar();		
		}

    }
    
    
    public void actualizar() {
    	AdmCliente idCliente = new AdmCliente();
    	idCliente.setIdCliente(this.idCliente);
    	
    	AdmDetalleCatalogo idCiudad = new AdmDetalleCatalogo ();
    	idCiudad.setIdDetalleCatalogo(this.idCiudad);
    	
    	AdmAgencia agencia = new AdmAgencia();
    	agencia.setIdAgencia(this.idAgencia);
    	
    	this.agencia.setAdmCliente(idCliente); 
    	this.agencia.setIdCiudad(idCiudad);
	

		servicioAgencia.update(this.agencia);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario Actualizado Correctamente "));
		consultaListaAgencia();
		cancelar();
		
	}
    
    
    public void modificar(){
		if(agenciaSeleccionada == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {
			
			 consultaListaCiudad();
				consultaListaAgencia();
		bandera =	true;
		agencia = agenciaSeleccionada;
		idCiudad = agencia.getIdCiudad().getIdDetalleCatalogo();
		consultaListaAgencia();
		idCliente = agencia.getAdmCliente().getIdCliente();
		
		if(agencia.getAdmCliente() == null){
			idClientePadre = 0;	
		} else{
			idAgencia = agencia.getAdmCliente().getIdCliente();
		}
		

		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosAgencia').show();");
		}
	}
    
    public void nuevo(){	
		
		
		bandera =	false;
		agencia = new AdmAgencia();
		consultaListaCiudad();
		consultaListaAgencia();
		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosAgencia').show();");	
		
	}
    
    public static void resetarFormulario() {
        RequestContext.getCurrentInstance().reset("frmCrear");
	}
    
    
  //metodo que llena una lista con los registros provenientes d ela base de datos
    
    public void consultaListaAgenciaCliente() {

        listaAgencia = new ArrayList<>(); // Creo una lista para mostrar todo los usuarios en el datatable
        listaAgencia = servicioAgencia.buscaAgencia(idCliente);
    }
    
    public void persistir() {

        if (bandera == true) {

            actualizar();

        } else {
            guardar();

        }
    }
    
    
    
    public AdmAgencia getAgencia() {
		return agencia;
	}
    
    public void setAgencia(AdmAgencia agencia) {
		this.agencia = agencia;
	}

	public AdmAgencia getAgenciaSeleccionada() {
		return agenciaSeleccionada;
	}

	public void setAgenciaSeleccionada(AdmAgencia agenciaSeleccionada) {
		this.agenciaSeleccionada = agenciaSeleccionada;
	}
	public List<AdmAgencia> getListaAgencia() {
		return listaAgencia;
	}

	public void setListaAgencia(List<AdmAgencia> listaAgencia) {
		this.listaAgencia = listaAgencia;
	}

	public List<AdmAgencia> getAgenciaFiltrada() {
		return agenciaFiltrada;
	}

	public void setAgenciaFiltrada(List<AdmAgencia> agenciaFiltrada) {
		this.agenciaFiltrada = agenciaFiltrada;
	}
	
	public List<AdmCliente> getListaClientePadre() {
		return listaClientePadre;
	}

	public void setListaClientePadre(List<AdmCliente> listaClientePadre) {
		this.listaClientePadre = listaClientePadre;
	}

	public List<AdmCliente> getListaCliente() {
		return listaCliente;
	}
	
	public void setListaCliente(List<AdmCliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public List<AdmDetalleCatalogo> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<AdmDetalleCatalogo> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}
	
	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}
}
