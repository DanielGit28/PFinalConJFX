package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.time.LocalDate;

public class MetodoPago {
    private int id;
    private int numeroTarjeta;
    private LocalDate fechaVencimiento;
    private int codigoSeguridad;
    private UsuarioFinal usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public UsuarioFinal getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioFinal usuario) {
        this.usuario = usuario;
    }

    public MetodoPago(){}

    public MetodoPago(int id, int numeroTarjeta, LocalDate fechaVencimiento, int codigoSeguridad, UsuarioFinal usuario) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "id=" + id +
                ", numeroTarjeta=" + numeroTarjeta +
                ", fechaVencimiento=" + fechaVencimiento +
                ", codigoSeguridad=" + codigoSeguridad +
                ", usuario=" + usuario +
                '}';
    }
}
