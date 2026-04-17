import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaDevolucion;
    private LocalDateTime fechaLimiteDevolucion;
    private int diasRetraso;
    private double multa;
    private static final double MULTA_POR_DIA = 5000; // Multa en pesos por día de retraso

    public Prestamo(LocalDateTime fE, LocalDateTime fLimite){
        this.fechaEntrega=fE;
        this.fechaLimiteDevolucion=fLimite;
        this.fechaDevolucion=null;
        this.diasRetraso=0;
        this.multa=0;
    }


    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public LocalDateTime getFechaLimiteDevolucion() {
        return fechaLimiteDevolucion;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMulta() {
        return multa;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setFechaLimiteDevolucion(LocalDateTime fechaLimiteDevolucion) {
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
    }

    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    /**
     * Calcula los días de retraso y la multa correspondiente
     * basándose en la fecha de devolución actual
     */
    public void calcularMulta(LocalDateTime fechaDevolucionReal) {
        this.fechaDevolucion = fechaDevolucionReal;
        
        // Si se devolvió antes o en la fecha límite, no hay retraso
        if (fechaDevolucionReal.isBefore(fechaLimiteDevolucion) || 
            fechaDevolucionReal.isEqual(fechaLimiteDevolucion)) {
            this.diasRetraso = 0;
            this.multa = 0;
        } else {
            // Calcular días de retraso
            this.diasRetraso = (int) ChronoUnit.DAYS.between(fechaLimiteDevolucion, fechaDevolucionReal);
            // Calcular multa: multiplicar días de retraso por multa por día
            this.multa = this.diasRetraso * MULTA_POR_DIA;
        }
    }

    public boolean tieneRetraso() {
        return diasRetraso > 0;
    }
}
