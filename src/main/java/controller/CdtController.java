package controller;

import service.UsuarioService;
import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped   // No necesita mantener estado entre peticiones, solo para el formulario de inversión
public class CdtController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioController usuarioController; // Para saber a qué usuario asociar la inversión

    // Campos del formulario de nueva inversión (CDT)
    private int nuevaInversionId;
    private String nuevaInversionNombre;
    private double nuevaInversionMonto;
    private int nuevaInversionPlazo;
    private double nuevaInversionTasa;

    // Getters y Setters
    public int getNuevaInversionId() {
        return nuevaInversionId;
    }

    public void setNuevaInversionId(int nuevaInversionId) {
        this.nuevaInversionId = nuevaInversionId;
    }

    public String getNuevaInversionNombre() {
        return nuevaInversionNombre;
    }

    public void setNuevaInversionNombre(String nuevaInversionNombre) {
        this.nuevaInversionNombre = nuevaInversionNombre;
    }

    public double getNuevaInversionMonto() {
        return nuevaInversionMonto;
    }

    public void setNuevaInversionMonto(double nuevaInversionMonto) {
        this.nuevaInversionMonto = nuevaInversionMonto;
    }

    public int getNuevaInversionPlazo() {
        return nuevaInversionPlazo;
    }

    public void setNuevaInversionPlazo(int nuevaInversionPlazo) {
        this.nuevaInversionPlazo = nuevaInversionPlazo;
    }

    public double getNuevaInversionTasa() {
        return nuevaInversionTasa;
    }

    public void setNuevaInversionTasa(double nuevaInversionTasa) {
        this.nuevaInversionTasa = nuevaInversionTasa;
    }

    // Acción para agregar la inversión al usuario seleccionado actualmente
    public void agregarNuevaInversion() {
        if (usuarioController.getUsuarioSeleccionado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Debe seleccionar un usuario primero"));
            return;
        }

        int idUsuario = usuarioController.getUsuarioSeleccionado().getId();
        try {
            usuarioService.guardarInversionToUsuario(
                nuevaInversionId,
                nuevaInversionNombre,
                nuevaInversionMonto,
                nuevaInversionPlazo,
                nuevaInversionTasa,
                idUsuario
            );
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Inversión agregada al usuario"));
            // Limpiar el formulario
            nuevaInversionId = 0;
            nuevaInversionNombre = null;
            nuevaInversionMonto = 0.0;
            nuevaInversionPlazo = 0;
            nuevaInversionTasa = 0.0;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la inversión"));
        }
    }

    // Opcional: método para volver a la lista de usuarios
    public String volverALista() {
        return "usuarios.xhtml?faces-redirect=true";
    }
}