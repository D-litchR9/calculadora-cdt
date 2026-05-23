package service;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Inversion;
import model.Usuario;
import model.UsuarioDAO;

@ApplicationScoped
public class UsuarioService {
	
	@Inject UsuarioDAO interfazDAO;
	
	
	public void guardarInversionToUsuario(int i, 
			String n, double m, int p, double t, int idIngresado) {
		
		Usuario usuarioExistente;
		Inversion nuevaInversion;
		usuarioExistente = interfazDAO.obtenerUsuario(idIngresado);
		
		//Si existe el usuario entonces:
		if(usuarioExistente != null) {
			nuevaInversion = new Inversion(i,n,m,p,t);
			usuarioExistente.agregarInversionUsuario(nuevaInversion);
		}
	}
	
	public void guardarNuevoUsuario(int i, String n) {
		Usuario nuevoUsuario = new Usuario(i,n);
		interfazDAO.agregarUsuarioToLista(nuevoUsuario);
	}

	public List<Usuario> listarTodosLosUsuarios() {
		return interfazDAO.obtenerListaUsuarios();
	}
	
	
}
