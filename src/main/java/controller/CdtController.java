package controller;

import service.UsuarioService;

import java.io.Serializable;
//import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class CdtController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

   // @Inject
   // private UsuarioController usuarioController;

    // Campos del formulario de nueva inversión (CDT)
    private int nuevaInversionId;
    private String nuevaInversionNombre;
    private double nuevaInversionMonto;
    private int nuevaInversionPlazo;
    private double nuevaInversionTasa;
    private int identificadorUsuarioSeleccionado;
    
    
    public int getIdentificadorUsuarioSeleccionado() { return identificadorUsuarioSeleccionado; }
    public void setIdentificadorUsuarioSeleccionado(int identificadorUsuarioSeleccionado) { this.identificadorUsuarioSeleccionado = identificadorUsuarioSeleccionado; }

    // Variables para mostrar resultados del cálculo (solo para la vista)
    private Double gananciaBrutaCalculada;
    private Double gananciaNetaCalculada;

    // Getters y Setters (originales)
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

    // Getters y Setters para los resultados del cálculo
    public Double getGananciaBrutaCalculada() {
        return gananciaBrutaCalculada;
    }

    public void setGananciaBrutaCalculada(Double gananciaBrutaCalculada) {
        this.gananciaBrutaCalculada = gananciaBrutaCalculada;
    }

    public Double getGananciaNetaCalculada() {
        return gananciaNetaCalculada;
    }

    public void setGananciaNetaCalculada(Double gananciaNetaCalculada) {
        this.gananciaNetaCalculada = gananciaNetaCalculada;
    }

    
    public void ValidarEntradasDesdeVista() {
        // Validar que los campos tengan valores razonables
        if (nuevaInversionNombre == null || nuevaInversionNombre.trim().isEmpty() ||
            nuevaInversionMonto <= 0 || nuevaInversionPlazo <= 0 || nuevaInversionTasa <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", 
                    "Complete todos los campos con valores válidos antes de calcular"));
            return;
        }
    }

    // Método original: agregar inversión al usuario seleccionado (void)
    public void registrarInversionDesdeVista() {
    	ValidarEntradasDesdeVista();
    	if(validarUsuarioSeleccionado()) {
	        try {
	            usuarioService.guardarInversionToUsuario(
	                nuevaInversionNombre,
	                nuevaInversionMonto,
	                nuevaInversionPlazo,
	                nuevaInversionTasa,
	                identificadorUsuarioSeleccionado
	            );
	            
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
	                    "Inversión agregada al usuario con el ID: "+ identificadorUsuarioSeleccionado));
	            
	            // Limpiar el formulario
	            limpiarFormulario();
	            
	        } catch (Exception e) {
	            System.out.println("ERROR: no se ha podido agregar la inversión a el usuario:"+ identificadorUsuarioSeleccionado);
	            e.printStackTrace();  // Esto imprime el error completo en la consola de Tomcat
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
	                    "No se pudo guardar la inversión: " + e.getMessage()));
	        }
    	}
    }

    // Nuevo método: guardar la inversión y redirigir a tablaUsuarios.xhtml
    public String guardarYRedirigir() {
    	
        return "tablaUsuarios.xhtml?faces-redirect=true";
    }

    // Método para volver a la lista de usuarios (original)
    public String volverALista() {
        return "usuarios.xhtml?faces-redirect=true";
    }
    public void limpiarFormulario() {
    	nuevaInversionId = 0;
        nuevaInversionNombre = null;
        nuevaInversionMonto = 0.0;
        nuevaInversionPlazo = 0;
        nuevaInversionTasa = 0.0;
    
    }
    public boolean validarUsuarioSeleccionado() {
    	if (identificadorUsuarioSeleccionado == 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", 
                    "Debe seleccionar un usuario primero"));
            return false;
        }
    	else return true;
    }
}