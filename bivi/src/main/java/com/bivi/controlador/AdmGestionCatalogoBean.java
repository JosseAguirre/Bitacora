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

import org.primefaces.event.SelectEvent;
import com.bivi.modelo.*;
import com.bivi.servicio.*;


@ManagedBean
@ViewScoped
public class AdmGestionCatalogoBean implements  Serializable {

	private static final long serialVersionUID = 1L;
	// Creo variables de los diferentes modelos que necesito para trabajar
	private AdmDetalleCatalogo admdetallecatalogo;
	private AdmCatalogo admcatalogo;
	// creo listas que necesitare para las consultas
	private List<AdmCatalogo> listacatalogopadre;
	private List<AdmDetalleCatalogo> listadetallecatalogopadre;
	private List<AdmDetalleCatalogo> detallecatalogofiltro;
	private List<AdmDetalleCatalogo> listadetallecatalogo;
	private List<AdmCatalogo> catalogofiltro;
	private List<AdmCatalogo> listacatalogo;
	
	
	// variables para editar pantalla de Gestión catálogos
	private Integer bandera;
	private Integer idcatalogo;
	private Integer iddetallecatalogo;
	private Integer idpadrecatalogo;
	private Integer iddetallecatalogopadre;
	private Integer ningunvalor = null;
	private Integer padrecatalogos;
	private Integer padredetallecatalogos;
		
		
		
	// Creo los EJB para establecer conección a los servicios usar
	@EJB
	private ServicioAdmDetalleCatalogo serviciodetallecatalogo;
	@EJB
	private ServicioAdmCatalogo serviciocatalogo;
	@EJB
	private ServicioAdmEmpleado servicioadmepleado;
	
	@PostConstruct
	public void init() {
		cancelar();
	}
	
	// Creo el metodo guardar catalogo
	public void guardarcatalogo() {
		admcatalogo.setIdCatalogo(serviciocatalogo.getPK());
		buscaIdCombosCatalogo();

		serviciocatalogo.create(admcatalogo);
		// mensaje al guardar
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
				"Se registró el Catálogo: " + admcatalogo.getNombre()));

		cancelar();
	}
	
	// Busqueda para llenar los parametros al momento de guardar
	public void buscaIdCombosCatalogo() {

		// creo una variable para poder llamar al catalogo padre
		AdmCatalogo idCatalogopadre = new AdmCatalogo();
		idCatalogopadre.setIdCatalogo(idpadrecatalogo);
	}
	
	// metdodo guardar detalle catalogo
	public void guardardetallecatalogo() {
		admdetallecatalogo.setIdDetalleCatalogo(serviciodetallecatalogo.getPK());
		buscaIdCombosDetalleCatalogo();

		serviciodetallecatalogo.create(admdetallecatalogo);
		// mensaje al guardar detalle Catálogo
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
				"Se registró el Detalle Catálogo: " + admdetallecatalogo.getDescripcion()));

		cancelar();
	}
	
	//Metodo para llenar la información automaticamente al momento de editar
	public void buscaIdCombosDetalleCatalogo() {
		admcatalogo = serviciocatalogo.findOne(idcatalogo);
		admdetallecatalogo.setAdmCatalogo(admcatalogo);
		//creo una variable para poder llamar al detallecatalogo padre
		AdmDetalleCatalogo idDetallecatalogopadre = new AdmDetalleCatalogo();
		idDetallecatalogopadre.setIdDetalleCatalogo(iddetallecatalogopadre);
		admdetallecatalogo.setIdDetalleCatalogoPadre(idDetallecatalogopadre);
	}
		
	//Metodo para seleccionar y llenar la información el catálogo al momento de dar un click sobre la fila
	public void onRowSelectCatalogo(SelectEvent event) {
		admcatalogo = (AdmCatalogo) event.getObject();
		bandera = 1;
	}
	
	//metodo para distingir entre guardar o editar
	public void persitir1() {
		if (bandera == 1) {

			actualizarcatalogo();

		} else {
			guardarcatalogo();

		}
	}
	
	//Metodo para seleccionar y llenar la información el detalle catálogo al momento de dar un click sobre la fila

	public void onRowSelectdetallecatalogo(SelectEvent event) {
		admdetallecatalogo = (AdmDetalleCatalogo) event.getObject();

		idcatalogo = admdetallecatalogo.getAdmCatalogo().getIdCatalogo();
		iddetallecatalogopadre = admdetallecatalogo.getIdDetalleCatalogoPadre().getIdDetalleCatalogo();
		bandera = 1;
	}
	
	//Metodo para distingir entre editar y guardar
	public void persitir2() {
		if (bandera == 1) {

			actualizardetallecatalogo();
		} else {
			guardardetallecatalogo();
		}
	}
	
	//Metodo para eliminar catálogo
	public void eliminarcatalogo() {

		serviciocatalogo.delete(admcatalogo);
		//mensaje al eliminar catálogo
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
		"Se eliminó el Catálogo: " + admcatalogo.getNombre()));

		cancelar();
	}
	
	//Metodo para eliminar detallecatálogo
	public void eliminardetallecatalogo() {

		serviciodetallecatalogo.delete(admdetallecatalogo);
		//mensaje al eliminar detallecatálogo
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
		"Se eliminó el Detalle Catálogo: " + admdetallecatalogo.getDescripcion()));

		cancelar();
	}
	
	//Metodo para actualizar catálogo
	public void actualizarcatalogo() {
		
		buscaIdCombosCatalogo();
		serviciocatalogo.update(admcatalogo);
		//Mensaje al actualizar catálogo
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
		"Se actualizó el Catálogo: " + admcatalogo.getNombre()));

		cancelar();
	}
	
	//Metodo para actualizar detlle catálago
	public void actualizardetallecatalogo() {
		
		buscaIdCombosDetalleCatalogo();
		serviciodetallecatalogo.update(admdetallecatalogo);
		//mensaje al actualizar detalle catálogo
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
		"Se actualizó el Detalle Catálogo: " + admdetallecatalogo.getDescripcion()));

		cancelar();
	}
	
	public void consultar() {

		listadetallecatalogo = new ArrayList<>();//
		listadetallecatalogo = serviciodetallecatalogo.findAll();

		listacatalogo = new ArrayList<>();//
		listacatalogo = serviciocatalogo.findAll();
	}
	
	//Metodo Cancelar
	 
	public void cancelar() {
		admdetallecatalogo = new AdmDetalleCatalogo();
		admcatalogo = new AdmCatalogo();
		bandera = 0;
		consultar();
	}
	
	
	//set y get de los metodos usados
	public void setAdmDetalleCatalogo(AdmDetalleCatalogo admdetallecatalogo) {
		this.admdetallecatalogo = admdetallecatalogo;
	}
	
	public AdmCatalogo getAdmCatalogo() {
		return admcatalogo;
	}

	public void setAdmCatalogo(AdmCatalogo admcatalogo) {
		this.admcatalogo = admcatalogo;
	}
	
	public List<AdmDetalleCatalogo> getDetallecatalogofiltro() {
		return detallecatalogofiltro;
	}

	public void setDetallecatalogofiltro(List<AdmDetalleCatalogo> detallecatalogofiltro) {
		this.detallecatalogofiltro = detallecatalogofiltro;
	}

	public List<AdmDetalleCatalogo> getListadetallecatalogo() {
		return listadetallecatalogo;
	}

	public void setListadetallecatalogo(List<AdmDetalleCatalogo> listadetallecatalogo) {
		this.listadetallecatalogo = listadetallecatalogo;
	}

	public List<AdmCatalogo> getCatalogofiltro() {
		return catalogofiltro;
	}

	public void setCatalogofiltro(List<AdmCatalogo> catalogofiltro) {
		this.catalogofiltro = catalogofiltro;
	}

	public List<AdmCatalogo> getListacatalogo() {
		return listacatalogo;
	}

	public void setListacatalogo(List<AdmCatalogo> listacatalogo) {
		this.listacatalogo = listacatalogo;
	}

	public Integer getBandera() {
		return bandera;
	}

	public void setBandera(Integer bandera) {
		this.bandera = bandera;
	}
	
	public Integer getIdcatalogo() {
		return idcatalogo;
	}

	public void setIdcatalogo(Integer idcatalogo) {
		this.idcatalogo = idcatalogo;
	}

	public Integer getIddetallecatalogo() {
		return iddetallecatalogo;
	}

	public void setIddetallecatalogo(Integer iddetallecatalogo) {
		this.iddetallecatalogo = iddetallecatalogo;
	}
	
	public ServicioAdmDetalleCatalogo getServiciodetallecatalogo() {
		return serviciodetallecatalogo;
	}

	public void setServiciodetallecatalogo(ServicioAdmDetalleCatalogo serviciodetallecatalogo) {
		this.serviciodetallecatalogo = serviciodetallecatalogo;
	}

	public void setServiciocatalogo(ServicioAdmCatalogo serviciocatalogo) {
		this.serviciocatalogo = serviciocatalogo;
	}

	public AdmDetalleCatalogo getAdmDetalleCatalogo() {
		return admdetallecatalogo;
	}

	public ServicioAdmCatalogo getServiciocatalogo() {
		return serviciocatalogo;
	}

	public Integer getIdpadrecatalogo() {
		return idpadrecatalogo;
	}

	public void setIdpadrecatalogo(Integer idpadrecatalogo) {
		this.idpadrecatalogo = idpadrecatalogo;
	}

	public List<AdmCatalogo> getListacatalogopadre() {
		return listacatalogopadre;
	}

	public void setListacatalogopadre(List<AdmCatalogo> listacatalogopadre) {
		this.listacatalogopadre = listacatalogopadre;
	}

	public Integer getIddetallecatalogopadre() {
		return iddetallecatalogopadre;
	}

	public void setIddetallecatalogopadre(Integer iddetallecatalogopadre) {
		this.iddetallecatalogopadre = iddetallecatalogopadre;
	}

	public List<AdmDetalleCatalogo> getListadetallecatalogopadre() {
		return listadetallecatalogopadre;
	}

	public void setListadetallecatalogopadre(List<AdmDetalleCatalogo> listadetallecatalogopadre) {
		this.listadetallecatalogopadre = listadetallecatalogopadre;
	}

	public ServicioAdmEmpleado getServicioadmepleado() {
		return servicioadmepleado;
	}

	public void setServicioadmepleado(ServicioAdmEmpleado servicioadmepleado) {
		this.servicioadmepleado = servicioadmepleado;
	}

	public Integer getNingunvalor() {
		return ningunvalor;
	}

	public void setNingunvalor(Integer ningunvalor) {
		this.ningunvalor = ningunvalor;
	}

	public void setListacatalogofiltro(List<AdmCatalogo> listacatalogofiltro) {
		this.catalogofiltro = listacatalogofiltro;
	}

	public List<AdmCatalogo> getcatalogofiltro() {
		return catalogofiltro;
	}
	
	public Integer getPadrecatalogos() {
		return padrecatalogos;
	}

	public void setPadrecatalogos(Integer padrecatalogos) {
		this.padrecatalogos = padrecatalogos;
	}

	public Integer getPadredetallecatalogos() {
		return padredetallecatalogos;
	}

	public void setPadredetallecatalogos(Integer padredetallecatalogos) {
		this.padredetallecatalogos = padredetallecatalogos;
	}
}
