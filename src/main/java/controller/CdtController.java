package controller;

import service.UsuarioService;
import model.Usuario;
import java.io.Serializable;
import java.util.List;

//import org.primefaces.PrimeFaces;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;

@Named
@SessionScoped
public class CdtController implements Serializable {

    private static final long serialVersionUID = 1428259387931014374L;

    @Inject
    UsuarioService objService;

    // Variables de usuario
    private int nuevoId;
    private String nuevoNombre;
    private int idUsuarioSeleccionado;
    
    private Usuario usuarioActual;
    private Usuario usuarioSeleccionado; // Usuario actual en la vista 
    

    // Variables para nueva inversión
    private int nuevaInversionId;
    private String nuevaInversionNombre;
    private double nuevaInversionMonto;
    private int nuevaInversionPlazo;
    private double nuevaInversionTasa;

   
    
    // Listar usuarios
    public List<Usuario> getListaUsuarios() {
        return objService.listarTodosLosUsuarios();
    }

    
    // Cargar usuario por ID (cuando se accede directamente con parámetro) pendiente por mirar
    public void cargarUsuarioPorId() {
        if (idUsuarioSeleccionado > 0) {
            usuarioActual = objService.listarTodosLosUsuarios().stream()
                .filter(u -> u.getId() == idUsuarioSeleccionado)
                .findFirst()
                .orElse(null);
            if (usuarioActual == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no encontrado"));
            }
        }
    }
    
    // Volver a la lista de usuarios
    public String volverALista() {
        return "usuarios.xhtml?faces-redirect=true";
    }

    //Agregar nuevo usuario 
    public void agregarNuevoUsuario() {
        objService.guardarNuevoUsuario(nuevoId, nuevoNombre);
    }

    // Agregar inversión al usuario actual 
    public void agregarNuevaInversionDesdeVista() {
    	//Ya se sabe el usuario seleccionado por el setter llamado en el dataTable;
        objService.guardarInversionToUsuario(
            nuevaInversionId,
            nuevaInversionNombre,
            nuevaInversionMonto,
            nuevaInversionPlazo,
            nuevaInversionTasa,
            usuarioSeleccionado.getId()
        );
    }
    
   
    
    // Getters y Setters de nueva Inversión
    public int getNuevoId() { return nuevoId; }
    public void setNuevoId(int nuevoId) { this.nuevoId = nuevoId; }

    public String getNuevoNombre() { return nuevoNombre; }
    public void setNuevoNombre(String nuevoNombre) { this.nuevoNombre = nuevoNombre; }

    public int getNuevaInversionId() { return nuevaInversionId; }
    public void setNuevaInversionId(int nuevaInversionId) { this.nuevaInversionId = nuevaInversionId; }

    public String getNuevaInversionNombre() { return nuevaInversionNombre; }
    public void setNuevaInversionNombre(String n) { this.nuevaInversionNombre = n; }

    public double getNuevaInversionMonto() { return nuevaInversionMonto; }
    public void setNuevaInversionMonto(double m) { this.nuevaInversionMonto = m; }

    public int getNuevaInversionPlazo() { return nuevaInversionPlazo; }
    public void setNuevaInversionPlazo(int p) { this.nuevaInversionPlazo = p; }

    public double getNuevaInversionTasa() { return nuevaInversionTasa; }
    public void setNuevaInversionTasa(double t) { this.nuevaInversionTasa = t; }
    
    //Getters y setters de Usuario 
    public Usuario getUsuarioActual() { return usuarioActual; }
    public void setUsuarioActual(Usuario usuarioActual) { this.usuarioActual = usuarioActual; }
    
    public int getIdUsuarioSeleccionado() { return idUsuarioSeleccionado; }
    public void setIdUsuarioSeleccionado(int idUsuarioSeleccionado) { this.idUsuarioSeleccionado = idUsuarioSeleccionado;}
    
    public Usuario getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(Usuario u) { this.usuarioSeleccionado = u; }
    }