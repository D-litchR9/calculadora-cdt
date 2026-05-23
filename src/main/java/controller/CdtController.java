package controller;


import service.UsuarioService;
import model.Usuario;
import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@SessionScoped
public class CdtController implements Serializable{
		
	@Inject UsuarioService ObjService;
	private int nuevoId;
	private String nuevoNombre;
	private int nuevaInversionId;
	private String nuevaInversionNombre;
	private double nuevaInversionMonto;
	private int nuevaInversionPlazo;
	private double nuevaInversionTasa;
	private Usuario usuarioSeleccionado;
	private static final long serialVersionUID = 1428259387931014374L;
	
	public List<Usuario> getListaUsuarios() {
	    return ObjService.listarTodosLosUsuarios();
	}

	
	// Método para cuando se selecciona una fila
		public void onUsuarioSeleccionado(SelectEvent event) {	    this.usuarioSeleccionado = (Usuario) event.getObject();}
		//Tengo que modificar el service para que reciba los datos, y lo envie al modelodao
		public void agregarNuevoUsuarioDesdeVista(int idFormulario,String nombreFormulario) {				ObjService.guardarNuevoUsuario(idFormulario,nombreFormulario);}
		
		public void agregarNuevaInversionDesdeVista(int i, 
				String n, double m, int p, double t, int idIngresado) {

			ObjService.guardarInversionToUsuario(i, n, m, p, t, idIngresado);
		}
		
	//Getters y Setters para usuarios
		public Usuario getUsuarioSeleccionado() {return usuarioSeleccionado;}
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {	    this.usuarioSeleccionado = usuarioSeleccionado;}
	
	public int getNuevoId() {return nuevoId;}
	public void setNuevoId(int nuevoId) {	    this.nuevoId = nuevoId;}

	public String getNuevoNombre() {	    return nuevoNombre;}
	public void setNuevoNombre(String nuevoNombre) {	    this.nuevoNombre = nuevoNombre;}

	// Getters y Setters para los campos del formulario de nueva inversión
	public int getNuevaInversionId() {	    return nuevaInversionId;}
	public void setNuevaInversionId(int nuevaInversionId) {	    this.nuevaInversionId = nuevaInversionId;}

	public String getNuevaInversionNombre() {	    return nuevaInversionNombre;}
	public void setNuevaInversionNombre(String nuevaInversionNombre) {	    this.nuevaInversionNombre = nuevaInversionNombre;}

	public double getNuevaInversionMonto() {	    return nuevaInversionMonto;}
	public void setNuevaInversionMonto(double nuevaInversionMonto) {	    this.nuevaInversionMonto = nuevaInversionMonto;}

	public int getNuevaInversionPlazo() {	    return nuevaInversionPlazo;}
	public void setNuevaInversionPlazo(int nuevaInversionPlazo) {	    this.nuevaInversionPlazo = nuevaInversionPlazo;}

	public double getNuevaInversionTasa() {	    return nuevaInversionTasa;}
	public void setNuevaInversionTasa(double nuevaInversionTasa) {	    this.nuevaInversionTasa = nuevaInversionTasa;}
	
	
	
}
