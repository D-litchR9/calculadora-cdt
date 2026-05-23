package model;


import java.util.List;
import java.util.ArrayList;

public class UsuarioDAO {
	private List<Usuario> historialUsuarios = new ArrayList<>();
	
	public void agregarUsuarioToLista(Usuario u) {
		historialUsuarios.add(u);
	}
	public Usuario obtenerUsuario(int idIngresado) {
		Usuario usuarioAuxiliar; 
		for(int i = 0; i< historialUsuarios.size();i++) {
			usuarioAuxiliar = historialUsuarios.get(i);
			if (usuarioAuxiliar.getId()==idIngresado)
				return usuarioAuxiliar;
		}
		//faltaría devolver un mensaje o un indicativo que no se encontró
		System.out.println("No se ha encontrado al usuario");
		return null;
	}
	public List<Usuario> obtenerListaUsuarios() {
		return historialUsuarios;
	}
	
}
