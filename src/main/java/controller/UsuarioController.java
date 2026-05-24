package controller;

import service.UsuarioService;
import model.Usuario;
import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    // Campos del formulario de registro
    private int nuevoId;
    private String nuevoNombre;

    // Usuario seleccionado (para mantener en sesión)
    private Usuario usuarioSeleccionado;

    // Getters y Setters
    public int getNuevoId() {
        return nuevoId;
    }

    public void setNuevoId(int nuevoId) {
        this.nuevoId = nuevoId;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    // Listar todos los usuarios (puede usarse en otras vistas)
    public List<Usuario> getListaUsuarios() {
        return usuarioService.listarTodosLosUsuarios();
    }

    // Acción del botón "Agregar usuario"
    public void agregarNuevoUsuario() {
        try {
            usuarioService.guardarNuevoUsuario(nuevoId, nuevoNombre);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario agregado correctamente"));
            // Limpiar campos después de agregar
            nuevoId = 0;
            nuevoNombre = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el usuario"));
        }
    }

    // Acción del botón "Ingresar" (por ahora solo verifica si existe el usuario y lo selecciona)
    /*
     public String ingresar() {
        if (nuevoId <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Debe ingresar un ID numérico válido"));
            return null;
        } 
    */
        

        // Buscar usuario por ID (asumiendo que UsuarioService tiene este método)
       /* Usuario u = usuarioService.buscarPorId(nuevoId);
        if (u != null) {
            usuarioSeleccionado = u;
            // Opcional: redirigir a una página de bienvenida o panel del usuario
            // return "panel.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Usuario " + u.getNombre() + " encontrado"));
            return null; // por ahora se queda en la misma página
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe un usuario con ID " + nuevoId));
            return null;
        }*/
    }