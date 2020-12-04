package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Artista {
    private String id;
    private String nombreArtista;
    private String apellidoArtista;
    private String nombreArtistico;
    private String fechaNacimientoArtista;
    private String fechaFallecimientoArtista;
    private String paisNacimiento;
    private Genero generoMusicalArtista;
    private int edadArtista;
    private String descripcionArtista;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getApellidoArtista() {
        return apellidoArtista;
    }

    public void setApellidoArtista(String apellidoArtista) {
        this.apellidoArtista = apellidoArtista;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getFechaNacimientoArtista() {
        return fechaNacimientoArtista;
    }

    public void setFechaNacimientoArtista(String fechaNacimientoArtista) {
        this.fechaNacimientoArtista = fechaNacimientoArtista;
    }

    public String getFechaFallecimientoArtista() {
        return fechaFallecimientoArtista;
    }

    public void setFechaFallecimientoArtista(String fechaFallecimientoArtista) {
        this.fechaFallecimientoArtista = fechaFallecimientoArtista;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public Genero getGeneroMusicalArtista() {
        return generoMusicalArtista;
    }

    public void setGeneroMusicalArtista(Genero generoMusicalArtista) {
        this.generoMusicalArtista = generoMusicalArtista;
    }

    public int getEdadArtista() {
        return edadArtista;
    }

    public void setEdadArtista(int edadArtista) {
        this.edadArtista = edadArtista;
    }

    public String getDescripcionArtista() {
        return descripcionArtista;
    }

    public void setDescripcionArtista(String descripcionArtista) {
        this.descripcionArtista = descripcionArtista;
    }

    public Artista(){}

    public Artista(String id, String nombreArtista, String apellidoArtista,
                   String nombreArtistico, String fechaNacimientoArtista,
                   String fechaFallecimientoArtista, String paisNacimiento,
                   Genero generoMusicalArtista, int edadArtista,
                   String descripcionArtista) {

        this.id = id;
        this.nombreArtista = nombreArtista;
        this.apellidoArtista = apellidoArtista;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimientoArtista = fechaNacimientoArtista;
        this.fechaFallecimientoArtista = fechaFallecimientoArtista;
        this.paisNacimiento = paisNacimiento;
        this.generoMusicalArtista = generoMusicalArtista;
        this.edadArtista = edadArtista;
        this.descripcionArtista = descripcionArtista;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", apellidoArtista='" + apellidoArtista + '\'' +
                ", nombreArtistico='" + nombreArtistico + '\'' +
                ", fechaNacimientoArtista='" + fechaNacimientoArtista + '\'' +
                ", fechaFallecimientoArtista='" + fechaFallecimientoArtista + '\'' +
                ", paisNacimiento='" + paisNacimiento + '\'' +
                ", generoMusicalArtista=" + generoMusicalArtista +
                ", edadArtista=" + edadArtista +
                ", descripcionArtista='" + descripcionArtista + '\'' +
                '}';
    }


}
