package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;

public class ListaReproduccion {
    private String id;
    private ArrayList<Cancion> cancionesListaReproduccion;
    private String fechaCreacionListaReproduccion;
    private String nombreListaReproduccion;
    private String calificacionReproduccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Cancion> getCancionesListaReproduccion() {
        return cancionesListaReproduccion;
    }

    public void setCancionesListaReproduccion(ArrayList<Cancion> cancionesListaReproduccion) {
        this.cancionesListaReproduccion = cancionesListaReproduccion;
    }

    public String getFechaCreacionListaReproduccion() {
        return fechaCreacionListaReproduccion;
    }

    public void setFechaCreacionListaReproduccion(String fechaCreacionListaReproduccion) {
        this.fechaCreacionListaReproduccion = fechaCreacionListaReproduccion;
    }

    public String getNombreListaReproduccion() {
        return nombreListaReproduccion;
    }

    public void setNombreListaReproduccion(String nombreListaReproduccion) {
        this.nombreListaReproduccion = nombreListaReproduccion;
    }

    public String getCalificacionReproduccion() {
        return calificacionReproduccion;
    }

    public void setCalificacionReproduccion(String calificacionReproduccion) {
        this.calificacionReproduccion = calificacionReproduccion;
    }

    public ListaReproduccion(){}

    public ListaReproduccion(String id, ArrayList<Cancion> cancionesListaReproduccion,
                             String fechaCreacionListaReproduccion,
                             String nombreListaReproduccion,
                             String calificacionReproduccion) {
        this.id = id;
        this.cancionesListaReproduccion = cancionesListaReproduccion;
        this.fechaCreacionListaReproduccion = fechaCreacionListaReproduccion;
        this.nombreListaReproduccion = nombreListaReproduccion;
        this.calificacionReproduccion = calificacionReproduccion;
    }

    @Override
    public String toString() {
        return "ListaReproduccion{" +
                "id=" + id +
                ", cancionesListaReproduccion=" + cancionesListaReproduccion +
                ", fechaCreacionListaReproduccion='" + fechaCreacionListaReproduccion + '\'' +
                ", nombreListaReproduccion='" + nombreListaReproduccion + '\'' +
                ", calificacionReproduccion='" + calificacionReproduccion + '\'' +
                '}';
    }


}
