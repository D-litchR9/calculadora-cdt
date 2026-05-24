package model;

import java.util.List;
import java.util.ArrayList;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioDAO {

    private List<Usuario> historialUsuarios = new ArrayList<>();

    public void agregarUsuarioToLista(Usuario u) {
        historialUsuarios.add(u);
    }

    public Usuario obtenerUsuario(int idIngresado) {
        for (Usuario u : historialUsuarios) {
            if (u.getId() == idIngresado) return u;
        }
        System.out.println("No se ha encontrado al usuario con id: " + idIngresado);
        return null;
    }

    public List<Usuario> obtenerListaUsuarios() {
        return historialUsuarios;
    }
}
