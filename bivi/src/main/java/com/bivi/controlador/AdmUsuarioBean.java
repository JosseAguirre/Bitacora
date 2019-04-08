package com.bivi.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.bivi.modelo.*;
import com.bivi.servicio.*;


@ManagedBean
@ViewScoped
public class AdmUsuarioBean implements  Serializable {
	
	private static final long serialVersionUID = 1L;
    private AdmUsuario admusuario;
    private AdmUsuario usuarioseleccionado;
    private AdmEmpleado admempleado;
    private List<AdmUsuario> listausuario;
    private List<AdmUsuario> usuariofiltrado;
    private AdmDetalleCatalogo detallecatalogo;
    private List<AdmEmpleado> listaempleado;
    
    private int idCliente;
    private List<AdmCliente> listaCliente;
	private List<AdmAgencia> listaAgencia;
	private List<AdmDetalleCatalogo> listaCiudad;
	
	private Date date;
    private int idempleado;
    private int idAgencia;
    private int idCiudadA;
    private int idCiudadC;
    private int ciudad;
    private int ciudadC;
    private int idUsuario;
    private boolean bandera;
    
    
    @EJB
    private ServicioAdmUsuario serviciousuario;
    @EJB
    private ServicioAdmEmpleado servicioempleado;
    @EJB
    private ServicioAdmDetalleCatalogo serviciodetallecatalogo;
    @EJB
	private ServicioAdmDetalleCatalogo servicioDetalleCatalogo;
    @EJB
	private ServicioAdmAgencia servicioAgencia;
    @EJB
	private ServicioAdmCliente servicioCliente;
    
    @PostConstruct
    public void init() {
        cancelar();
        consultaListaUsuarios();
        consultaListaCombos();
        setDate(new Date());
		
		consultaListaCiudad();
		consultaListaCliente();
		 usuarioseleccionado = new AdmUsuario();  
    }
    
  //metodo que instancia nuevos objetos de una clase
    
    public void cancelar() {
        admusuario = new AdmUsuario();
        admempleado = new AdmEmpleado();
        detallecatalogo = new AdmDetalleCatalogo ();;
        bandera = false;
    }
    
  //metodo que guarda un usuario nuevo
    
    public void guardar() {

        try {
            admusuario.setIdUsuario(serviciousuario.getPK());
            admusuario.setAdmEmpleado(admempleado);
            admusuario.setContrasenia(SesionBean.encriptarSHA512(admusuario.getContrasenia()));
            admusuario.setFechaCreacion(date);
            admusuario.setFechaModificacion(date);
          
            serviciousuario.create(admusuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso", "Se registró el Usuario: " + admusuario.getUsuario()));
            consultaListaUsuarios();
            cancelar();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Se ha producido un error al guardar"));

        }

    }
    
  //metodo para eliminar un usuario 
    
    public void eliminar() {
    	if(usuarioseleccionado == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {	
			admusuario = usuarioseleccionado;
		
		serviciousuario.update(admusuario);
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos Eliminado Correctamente "));		
		consultaListaUsuarios();
		cancelar();		
		}

    }
    
    public void persistir(){
    	System.out.println("bandera esss :"+bandera);
    	if(bandera == true){
    		actualizar();  		
    	}else {
    		guardar();
    		
    	}
    }
    
    public void actualizar(){
    	
    	try {
    		serviciousuario.update(admusuario);
    		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Regitro actualizado con exito"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Se produjo un error al actualizar :"+ e));
		}	
    	
    }
    
    public void modificar(){
		if(usuarioseleccionado == null){
			FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe selecionar un Registro "));
					
		}else {
		bandera =	true;
		admusuario = usuarioseleccionado;
		consultaListaCiudad();
		consultaListaCliente();
		
		resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosUsuario').show();");
		}
	}
    
    public void cargarDatosUsuario(){
		
		cancelar();
		consultaListaCiudad();
		consultaListaCliente();
	}
    
    public void nuevo(){
		
		bandera =	false;
		cancelar();
		consultaListaCiudad();
		consultaListaCliente();
		//resetarFormulario();
		RequestContext.getCurrentInstance().execute("PF('dlgDatosUsuario').show();");
	}
    
    public static void resetarFormulario() {
        RequestContext.getCurrentInstance().reset("frmCrear");
	}
    
  //metodo que llena una lista con los registros provenientes d ela base de datos
    
    public void consultaListaUsuarios() {

        listausuario = new ArrayList<>(); // Creo una lista para mostrar todo los usuarios en el datatable
        listausuario = serviciousuario.findAll();   
    }
    
    public void consultaListaCombos() {
    	listaempleado = new ArrayList<>(); // Creo una lista para mostar todos los empleado en un datatable
        listaempleado = servicioempleado.findAll();
    	
    }
    
    public void consultaListaCiudad() {
    	if(usuarioseleccionado != null){
    	
    		listaCiudad = new ArrayList<>();
    		listaCiudad = servicioDetalleCatalogo.ciudades();
    	}else {
    		
    		listaCiudad = new ArrayList<>();
    	}
    }
    
    public void consultaListaAgencia() {

		setListaAgencia(new ArrayList<>());
		setListaAgencia(servicioAgencia.buscaAgenciaCiudad(ciudad, idCliente)); //consulta todas las agencias del cliente seleccionado
		
	}
    
    public void onRowSelectEmpleado(SelectEvent event) {
		admempleado =(AdmEmpleado) event.getObject();
    }
    
    public void consultaListaCliente() {
		listaCliente = new ArrayList<>();
		listaCliente = servicioCliente.buscaClientePadre();

	}
    
    public void asignaCiudad() {

		ciudad = idCiudadA;
    }
    public void asignaCiudadC() {

  		ciudadC = idCiudadC;
    }
    
    public List<AdmUsuario> getListausuario() {
        return listausuario;
    }

    public void setListausuario(List<AdmUsuario> listausuario) {
        this.listausuario = listausuario;
    }

    public List<AdmEmpleado> getListaempleado() {
        return listaempleado;
    }

    public void setListaempleado(List<AdmEmpleado> listaempleado) {
        this.listaempleado = listaempleado;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public AdmUsuario getAdmUsuario() {
        return admusuario;
    }

    public void setAdmUsuario(AdmUsuario admusuario) {
        this.admusuario = admusuario;
    }

    public AdmEmpleado getAdmEmpleado() {
        return admempleado;
    }

    public void setAdmEmpleado(AdmEmpleado admempleado) {
        this.admempleado = admempleado;
    }

    public List<AdmUsuario> getUsuariofiltrado() {
        return usuariofiltrado;
    }

    public void setUsuariofiltrado(List<AdmUsuario> usuariofiltrado) {
        this.usuariofiltrado = usuariofiltrado;
    }

    public AdmDetalleCatalogo getDetallecatalogo() {
        return detallecatalogo;
    }

    public void setDetallecatalogo(AdmDetalleCatalogo detallecatalogo) {
        this.detallecatalogo = detallecatalogo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AdmUsuario getUsuarioseleccionado() {
        return usuarioseleccionado;
    }

    public void setUsuarioseleccionado(AdmUsuario usuarioseleccionado) {
        this.usuarioseleccionado = usuarioseleccionado;
    }

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public List<AdmDetalleCatalogo> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<AdmDetalleCatalogo> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}


	public List<AdmCliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<AdmCliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public List<AdmAgencia> getListaAgencia() {
		return listaAgencia;
	}

	public void setListaAgencia(List<AdmAgencia> listaAgencia) {
		this.listaAgencia = listaAgencia;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCiudadA() {
		return idCiudadA;
	}

	public void setIdCiudadA(int idCiudadA) {
		this.idCiudadA = idCiudadA;
	}

	public int getIdCiudadC() {
		return idCiudadC;
	}

	public void setIdCiudadC(int idCiudadC) {
		this.idCiudadC = idCiudadC;
	}
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
