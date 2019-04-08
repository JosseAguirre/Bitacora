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
public class FisDetalleBitacoraBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private FisDetalleBitacora detalleBitacora;
	private FisDetalleBitacora detalleBitacoraSeleccionada;
	private List<FisDetalleBitacora> listaDetalleBitacora;
	private List<FisDetalleBitacora> detalleBitacoraFiltrado;
	
	private FisCabeceraBitacora cabeceraBitacora;
	private List<FisCabeceraBitacora> listaCabeceraBitacora;
	private int idCabeceraBitacora;
	private boolean bandera;
	
	@EJB
	private ServicioFisDetalleBitacora servicioDetalleBitacora;
	@EJB
	private ServicioFisCabeceraBitacora servicioCabeceraBitacora;
	
	@PostConstruct
    public void init() {
        cancelar();
        consultaListaDetalleBitacora();
        consultaListaCabeceraBitacora();
    }
	
	public void cancelar() {
		detalleBitacora = new FisDetalleBitacora();
		cabeceraBitacora = new FisCabeceraBitacora();
		bandera = false;
		idCabeceraBitacora = -1;
	}
	
	public void guardar() {
		try {
			detalleBitacora.setIdDetalleBitacora(servicioDetalleBitacora.getPK());
			cabeceraBitacora = servicioCabeceraBitacora.findOne(idCabeceraBitacora);
			detalleBitacora.setFisCabeceraBitacora(cabeceraBitacora);
			servicioDetalleBitacora.create(detalleBitacora);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha guardado don exito "));
			consultaListaDetalleBitacora();
			cancelar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Se ha producido un error al guardar"));
		}
	}
	
	public void eliminar() {
		if(detalleBitacoraSeleccionada == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
		}else {
			detalleBitacora = detalleBitacoraSeleccionada;
			
			servicioDetalleBitacora.update(detalleBitacora);
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos Eliminado Correctamente "));
			consultaListaDetalleBitacora();
			cancelar();	
		}
	}
	
	public void actualizar() {
		servicioDetalleBitacora.update(detalleBitacora);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario Actualizado Correctamente "));
		cancelar();
	}
	
	public void modificar() {
		if(detalleBitacoraSeleccionada == null) {
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
		}else {
			bandera = true;
			detalleBitacora = detalleBitacoraSeleccionada;
			idCabeceraBitacora = detalleBitacora.getFisCabeceraBitacora().getIdCabeceraBitacora();
			
			resetarFormulario();
			RequestContext.getCurrentInstance().execute("PF('dlgDatosDetalleBitacora').show();");
		}
	}
	
	public void nuevo() {
		bandera = false;
		detalleBitacora = new FisDetalleBitacora();
		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosDetalleBitacora').show();");
	}
	
	public static void resetarFormulario() {
        RequestContext.getCurrentInstance().reset("frmCrear");
	}
	
	public void consultaListaDetalleBitacora() {
		setListaDetalleBitacora(new ArrayList<>());
		setListaDetalleBitacora(servicioDetalleBitacora.findAll());
	}
	
	public void consultaListaCabeceraBitacora() {
		setListaCabeceraBitacora(new ArrayList<>());
		setListaCabeceraBitacora(servicioCabeceraBitacora.findAll());
	}
	
	public void persistir() {

        if (bandera == true) {

            actualizar();

        } else {
            guardar();

        }
    }
	
	public FisDetalleBitacora getDetalleBitacora() {
		return detalleBitacora;
	}
	
	public void setDetalleBitacora(FisDetalleBitacora detalleBitacora) {
		this.detalleBitacora = detalleBitacora;
	}
	
	public FisDetalleBitacora getDetalleBitacoraSeleccionada() {
		return detalleBitacoraSeleccionada;
	}
	
	public void setDetalleBitacoraSeleccionada(FisDetalleBitacora detalleBitacoraSeleccionada) {
		this.detalleBitacoraSeleccionada = detalleBitacoraSeleccionada;
	}

	public List<FisDetalleBitacora> getListaDetalleBitacora() {
		return listaDetalleBitacora;
	}

	public void setListaDetalleBitacora(List<FisDetalleBitacora> listaDetalleBitacora) {
		this.listaDetalleBitacora = listaDetalleBitacora;
	}

	public List<FisDetalleBitacora> getDetalleBitacoraFiltrado() {
		return detalleBitacoraFiltrado;
	}

	public void setDetalleBitacoraFiltrado(List<FisDetalleBitacora> detalleBitacoraFiltrado) {
		this.detalleBitacoraFiltrado = detalleBitacoraFiltrado;
	}

	public FisCabeceraBitacora getCabeceraBitacora() {
		return cabeceraBitacora;
	}
	
	public void setCabeceraBitacora(FisCabeceraBitacora cabeceraBitacora) {
		this.cabeceraBitacora = cabeceraBitacora;
	}
	
	public List<FisCabeceraBitacora> getListaCabeceraBitacora() {
		return listaCabeceraBitacora;
	}

	public void setListaCabeceraBitacora(List<FisCabeceraBitacora> listaCabeceraBitacora) {
		this.listaCabeceraBitacora = listaCabeceraBitacora;
	}
	
	public int getIdCabeceraBitacora() {
		return idCabeceraBitacora;
	}
	
	public void setIdCabeceraBitacora() {
		this.idCabeceraBitacora = idCabeceraBitacora;
	}

}
