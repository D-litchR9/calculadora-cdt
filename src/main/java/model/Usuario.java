package model;

import java.util.ArrayList;
import java.util.List;
//import jakarta.enterprise.context.ApplicationScoped;


public class Usuario {
	private int id;
	private String nombre;
	private int totalInversiones;
	private List<Inversion> historialInversiones= new ArrayList<Inversion>();
	
	//Método principal
	
	public void agregarInversionUsuario(Inversion ObjInversion) {
		historialInversiones.add(ObjInversion);
	};
	
	
	public Usuario(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	

	//Getters y Setters
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public int getTotalnversiones() {return totalInversiones;}
	public void setTotalInversiones(int totalInversiones) {this.totalInversiones= totalInversiones;}
	
	public List<Inversion> getHistorialInversiones() {		return historialInversiones;}
	public void setHistorialInversiones(List<Inversion> historialInversiones) {this.historialInversiones = historialInversiones;}
	
}
