package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Inversion> historialInversiones = new ArrayList<>();

    @Transient   // No se guarda en BD, se calcula cuando se necesite
    private int totalInversiones;

    public Usuario() {}  // Constructor vacío requerido por JPA

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    // Método de conveniencia para mantener ambos lados de la relación
    public void agregarInversionUsuario(Inversion inversion) {
        historialInversiones.add(inversion);
        inversion.setUsuario(this);
    }

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Inversion> getHistorialInversiones() { return historialInversiones; }
    public void setHistorialInversiones(List<Inversion> historialInversiones) { this.historialInversiones = historialInversiones; }

    public int getTotalInversiones() {
        return historialInversiones != null ? historialInversiones.size() : 0;
    }
    public void setTotalInversiones(int totalInversiones) {
        this.totalInversiones = totalInversiones;  // No se usa en BD, se mantiene por compatibilidad
    }
}