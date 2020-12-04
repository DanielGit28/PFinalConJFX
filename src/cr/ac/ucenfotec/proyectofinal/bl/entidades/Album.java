package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Album {
    private String id;
    private String nombreAlbum;
    private String fechaLanzamiento;
    private Artista artistaAlbum;
    private String imagenAlbum;
    private ArrayList<Cancion> cancionesAlbum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Artista getArtistaAlbum() {
        return artistaAlbum;
    }

    public void setArtistaAlbum(Artista artistaAlbum) {
        this.artistaAlbum = artistaAlbum;
    }

    public String getImagenAlbum() {
        return imagenAlbum;
    }

    public void setImagenAlbum(String imagenAlbum) {
        this.imagenAlbum = imagenAlbum;
    }

    public ArrayList<Cancion> getCancionesAlbum() {
        return cancionesAlbum;
    }

    public void setCancionesAlbum(ArrayList<Cancion> cancionesAlbum) {
        this.cancionesAlbum = cancionesAlbum;
    }

    public Album(){}

    public Album(String id, String nombreAlbum, String fechaLanzamiento, Artista artistaAlbum,
                 String imagenAlbum, ArrayList<Cancion> cancionesAlbum) {
        this.id = id;
        this.nombreAlbum = nombreAlbum;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artistaAlbum = artistaAlbum;
        this.imagenAlbum = imagenAlbum;
        this.cancionesAlbum = cancionesAlbum;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", nombreAlbum='" + nombreAlbum + '\'' +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", artistaAlbum=" + artistaAlbum +
                ", imagenAlbum='" + imagenAlbum + '\'' +
                ", cancionesAlbum=" + cancionesAlbum +
                '}';
    }


}
