package model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

@ApplicationScoped
public class UsuarioDaoJpa {

    @Inject
    private EntityManagerFactory emf;

    // Métodos de escritura con transacciones manuales

    public void agregarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, id);
            if (u != null) {
                em.remove(u);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Métodos de lectura (sin transacción, o con transacción de solo lectura)
    // Puedes abrir una transacción y hacer commit si tu JPA lo requiere, pero normalmente no es necesario.
    // Aquí los dejamos sin transacción explícita; el EntityManager se cierra al final.

    public Usuario obtenerUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.historialInversiones",
                    Usuario.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}