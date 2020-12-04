package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
**/
public class Genero {
    private String id;
    private String nombreGenero;
    private String descripcionGenero;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getDescripcionGenero() {
        return descripcionGenero;
    }

    public void setDescripcionGenero(String descripcionGenero) {
        this.descripcionGenero = descripcionGenero;
    }

    public Genero() {}

    public Genero(String id, String nombreGenero, String descripcionGenero) {
        this.id = id;
        this.nombreGenero = nombreGenero;
        this.descripcionGenero = descripcionGenero;
    }

    @Override
    public String toString() {
        return "Generos{" +
                "id='" + id + '\'' +
                ", nombreGenero='" + nombreGenero + '\'' +
                ", descripcionGenero='" + descripcionGenero + '\'' +
                '}';
    }


}
