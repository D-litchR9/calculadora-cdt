package controller;

import service.UsuarioService;
import model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    private int nuevoId;
    private String nuevoNombre;
    private Usuario usuarioSeleccionado;

    // Para tabla y búsqueda
    private int idBusqueda;
    private List<Usuario> usuariosFiltrados;

    // Getters / Setters
    public int getNuevoId() { return nuevoId; }
    public void setNuevoId(int nuevoId) { this.nuevoId = nuevoId; }

    public String getNuevoNombre() { return nuevoNombre; }
    public void setNuevoNombre(String nuevoNombre) { this.nuevoNombre = nuevoNombre; }

    public Usuario getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) { this.usuarioSeleccionado = usuarioSeleccionado; }

    public int getIdBusqueda() { return idBusqueda; }
    public void setIdBusqueda(int idBusqueda) { this.idBusqueda = idBusqueda; }

    public List<Usuario> getUsuariosFiltrados() {
        if (usuariosFiltrados == null) {
            usuariosFiltrados = new ArrayList<>(getListaUsuarios());
        }
        return usuariosFiltrados;
    }

    public List<Usuario> getListaUsuarios() {
        return usuarioService.listarTodosLosUsuarios();
    }

    public void agregarNuevoUsuario() {
        try {
            usuarioService.guardarNuevoUsuario(nuevoNombre);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario agregado correctamente"));
            nuevoId = 0;
            nuevoNombre = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el usuario"));
        }
    }

    public void limpiarFiltro() {
        this.idBusqueda = 0;
        buscar();
    }

    public void buscar() {
        List<Usuario> todos = getListaUsuarios();
        if (idBusqueda > 0) {
            usuariosFiltrados = todos.stream()
                    .filter(u -> u.getId() == idBusqueda)
                    .collect(Collectors.toList());
        } else {
            usuariosFiltrados = new ArrayList<>(todos);
        }
    }

    public void mostrarTodos() {
        this.idBusqueda = 0;
        buscar();
    }

    // Método ingresar (comentado por ahora, pero sin errores de llaves)
    /*
    public String ingresar() {
        if (nuevoId <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Debe ingresar un ID numérico válido"));
            return null;
        }
        Usuario u = usuarioService.obtenerUsuario(nuevoId);
        if (u != null) {
            usuarioSeleccionado = u;
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Usuario " + u.getNombre() + " encontrado"));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe un usuario con ID " + nuevoId));
            return null;
        }
    }
    */
}