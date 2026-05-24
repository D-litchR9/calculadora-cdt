package model;

public class Inversion {

    private int id;
    private String nombreInversion;
    private double montoInversion;
    private int plazoEnDias;
    private double tasa;
    private double gananciaBruta;
    private double gananciaNeta;
    
    public Inversion() {}

    public Inversion(int id, String nombreIngresado, double montoIngresado,
                     int plazoIngresado, double tasaIngresada) {
        this.id = id;
        this.nombreInversion = nombreIngresado;
        this.montoInversion = montoIngresado;
        this.plazoEnDias = plazoIngresado;
        this.tasa = tasaIngresada;
        // Calcular al construir para que los getters devuelvan valores correctos
        this.gananciaBruta = calcularGananciaBruta();
        this.gananciaNeta  = calcularGananciaNeta();
    }

    // tasa viene como porcentaje (ej: 5.0 = 5%), se divide entre 100
    public double calcularGananciaBruta() {
        return gananciaBruta = this.montoInversion * (this.tasa / 100.0) * (this.plazoEnDias / 365.0);
    }

    public double calcularGananciaNeta() {
        double gb = calcularGananciaBruta();
        this.gananciaNeta = gb - (gb * 0.04);
        return this.gananciaNeta;
    }

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
}
