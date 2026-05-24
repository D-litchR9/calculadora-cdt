package model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsuarioDaoJpa {
	
    @PersistenceContext(unitName = "cdtPU")
    private EntityManager em;

    @Transactional
    public void agregarUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario obtenerUsuario(int id) {
        return em.find(Usuario.class, id);
    }

    public List<Usuario> obtenerTodos() {
        return em.createQuery("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.historialInversiones", Usuario.class)
                 .getResultList();
    }

    @Transactional
    public void actualizarUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    @Transactional
    public void eliminarUsuario(int id) {
        Usuario u = obtenerUsuario(id);
        if (u != null) em.remove(u);
    }
}