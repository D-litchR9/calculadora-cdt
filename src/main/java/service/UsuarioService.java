package service;
import java.util.List;
import jakarta.persistence.PersistenceContext;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import model.Inversion;
import model.Usuario;
import model.UsuarioDaoJpa;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UsuarioService {
	
	@Inject
    private UsuarioDaoJpa usuarioDao;
	@PersistenceContext
	 private EntityManager em;
	
	@Transactional
	 public void guardarInversionToUsuario(String nombreInversion, double monto, int plazo, double tasa, int idUsuario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, idUsuario); // En lugar de usuarioDao.obtenerUsuario
            if (usuario != null) {
                Inversion nuevaInversion = new Inversion(nombreInversion, monto, plazo, tasa);
                usuario.agregarInversionUsuario(nuevaInversion);
                em.persist(nuevaInversion); // Si la relación no persiste en cascada, hacer persist
                // em.merge(usuario); // No necesario si usuario está gestionado
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error al guardar inversión", e);
        }
    }
	@Transactional
    public void guardarNuevoUsuario(String nombre) {
        Usuario nuevo = new Usuario(nombre);
        usuarioDao.agregarUsuario(nuevo);
    }

    public List<Usuario> listarTodosLosUsuarios() {
        return usuarioDao.obtenerTodos();
    }
	
    public Usuario obtenerUsuarioConInversiones(int id) {
        Usuario u = usuarioDao.obtenerUsuario(id);
        if (u != null) 
            u.getHistorialInversiones().size(); // Forzar carga si es LAZY y la sesión sigue abierta
        
        return u;
    }
	
	
}
