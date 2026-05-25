package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaConexion {
    public static void main(String[] args) {
        
        String unidadPersistencia = "cdtPU"; 
        
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Intentando conectar a PostgreSQL...");
            emf = Persistence.createEntityManagerFactory(unidadPersistencia);
            em = emf.createEntityManager();

            // 2. Ejecuta una consulta nativa de prueba para obligar a JPA a conectar
            String versionPostgres = (String) em.createNativeQuery("SELECT version()").getSingleResult();
            
            System.out.println("=========================================");
            System.out.println("¡CONEXIÓN EXITOSA CON POSTGRESQL!");
            System.out.println("Versión del servidor: " + versionPostgres);
            System.out.println("=========================================");

        } catch (Exception e) {
            System.err.println("=========================================");
            System.err.println("ERROR: No se pudo conectar a la base de datos.");
            System.err.println("Detalle del error:");
            e.printStackTrace();
            System.err.println("=========================================");
        } finally {
            // 3. Cierra siempre los recursos abiertos
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
