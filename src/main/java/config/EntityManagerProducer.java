package config;

import jakarta.annotation.PreDestroy;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("cdtPU"); 
    }

    @Produces
    @RequestScoped   // importante: un EM por petición
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

    // Opcional: si necesitas la fábrica para algo más
    @Produces
    @ApplicationScoped
    public EntityManagerFactory createEntityManagerFactory() {
        return emf;
    }

    @PreDestroy
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}