package model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inversion")
public class Inversion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_inversion", nullable = false)
    private String nombreInversion;

    @Column(name = "monto_inversion", nullable = false)
    private double montoInversion;

    @Column(name = "plazo_en_dias", nullable = false)
    private int plazoEnDias;

    private double tasa;

    @Column(name = "ganancia_bruta")
    private double gananciaBruta;

    @Column(name = "ganancia_neta")
    private double gananciaNeta;
    
    public Inversion() {}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Inversion() {}  // Constructor vacío JPA

    public Inversion(String nombreInversion, double montoInversion, int plazoEnDias, double tasa) {
        this.nombreInversion = nombreInversion;
        this.montoInversion = montoInversion;
        this.plazoEnDias = plazoEnDias;
        this.tasa = tasa;
        calcularGanancias();
    }

    private void calcularGanancias() {
        this.gananciaBruta = this.montoInversion * (this.tasa / 100.0) * (this.plazoEnDias / 365.0);
        this.gananciaNeta = this.gananciaBruta - (this.gananciaBruta * 0.04);
    }

    // Método que se debe llamar antes de persistir o actualizar
    @PrePersist
    @PreUpdate
    public void actualizarGanancias() {
        calcularGanancias();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreInversion() { return nombreInversion; }
    public void setNombreInversion(String nombreInversion) { this.nombreInversion = nombreInversion; }

    public double getMontoInversion() { return montoInversion; }
    public void setMontoInversion(double montoInversion) { this.montoInversion = montoInversion; }

    public int getPlazoEnDias() { return plazoEnDias; }
    public void setPlazoEnDias(int plazoEnDias) { this.plazoEnDias = plazoEnDias; }

    public double getTasa() { return tasa; }
    public void setTasa(double tasa) { this.tasa = tasa; }

    public double getGananciaBruta() { return gananciaBruta; }
    public void setGananciaBruta(double gananciaBruta) { this.gananciaBruta = gananciaBruta; }

    public double getGananciaNeta() { return gananciaNeta; }
    public void setGananciaNeta(double gananciaNeta) { this.gananciaNeta = gananciaNeta; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}