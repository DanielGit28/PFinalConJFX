package cr.ac.ucenfotec.proyectofinal.bl.entidades;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.1
 */
public class Cancion {
    private String nombreCancion;
    protected Artista artistaCancion;
    protected Compositor compositorCancion;
    protected String fechaLanzamientoCancion;
    protected Genero generoCancion;
    protected int cancionSimple; //1 si es un sencillo, 2 si es de un álbum
    protected Album albumCancion;
    private String recurso;


    public Artista getArtistaCancion() {
        return artistaCancion;
    }

    public void setArtistaCancion(Artista artistaCancion) {
        this.artistaCancion = artistaCancion;
    }

    public Compositor getCompositorCancion() {
        return compositorCancion;
    }

    public void setCompositorCancion(Compositor compositorCancion) {
        this.compositorCancion = compositorCancion;
    }

    public String getFechaLanzamientoCancion() {
        return fechaLanzamientoCancion;
    }

    public void setFechaLanzamientoCancion(String fechaLanzamientoCancion) {
        this.fechaLanzamientoCancion = fechaLanzamientoCancion;
    }

    public Genero getGeneroCancion() {
        return generoCancion;
    }

    public void setGeneroCancion(Genero generoCancion) {
        this.generoCancion = generoCancion;
    }

    public int getCancionSimple() {
        return cancionSimple;
    }

    public void setCancionSimple(int cancionSimple) {
        this.cancionSimple = cancionSimple;
    }

    public Album getAlbumCancion() {
        return albumCancion;
    }

    public void setAlbumCancion(Album albumCancion) {
        this.albumCancion = albumCancion;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public Cancion() {}

    public Cancion(String nombreCancion, Artista artistaCancion, Compositor compositorCancion,
                   String fechaLanzamientoCancion, Genero generoCancion,
                   int cancionSimple, Album albumCancion, String recurso) {
        this.nombreCancion = nombreCancion;
        this.artistaCancion = artistaCancion;
        this.compositorCancion = compositorCancion;
        this.fechaLanzamientoCancion = fechaLanzamientoCancion;
        this.generoCancion = generoCancion;
        this.cancionSimple = cancionSimple;
        this.albumCancion = albumCancion;
        this.recurso = recurso;

    }

    @Override
    public String toString() {
        return "Cancion{" +
                ", nombreCancion=" + nombreCancion +
                ", artistaCancion=" + artistaCancion +
                ", compositorCancion=" + compositorCancion +
                ", fechaLanzamientoCancion='" + fechaLanzamientoCancion + '\'' +
                ", generoCancion=" + generoCancion +
                ", cancionSimple=" + cancionSimple +
                ", albumCancion=" + albumCancion +
                ", recurso=" + recurso +
                '}';
    }



}
