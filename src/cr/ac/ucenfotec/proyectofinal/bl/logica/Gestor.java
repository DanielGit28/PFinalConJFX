package cr.ac.ucenfotec.proyectofinal.bl.logica;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.*;

import java.util.ArrayList;
import java.util.Locale;

public class Gestor {
    public String[] locales = Locale.getISOCountries();
    Admin administrador = new Admin();
    ArrayList<UsuarioFinal> usuarios = new ArrayList<> ();
    ArrayList<Artista> artistas = new ArrayList<> ();
    ArrayList<Compositor> compositores = new ArrayList<> ();
    ArrayList<Genero> generos = new ArrayList<> ();
    ArrayList<Album> albumes = new ArrayList<> ();
    ArrayList<Cancion> canciones = new ArrayList<>();
    ArrayList<ListaReproduccion> listas = new ArrayList<>();

    //------Bloque de agregar objetos a los ArrayList que los almacenan-------
    public void agregarAdmin(Admin pAdmin) {
        this.administrador = pAdmin;
    }

    public Admin listarAdmin(){
        return this.administrador;
    }

    public void agregarUsuario(UsuarioFinal pUsuario) {
        this.usuarios.add(pUsuario);
    }

    public ArrayList<UsuarioFinal> getUsuarios() {
        return usuarios;
    }

    public void agregarArtista(Artista pArtista) {
        this.artistas.add(pArtista);
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public Artista getArtista(String nombreArt) {
        for (int i = 0; i < artistas.size(); i++) {
            Artista temp = artistas.get(i);
            if(temp.getNombreArtistico().equals(nombreArt)) {
                return temp;
            }
        }
        return null;
    }
    public String getNomArtistico(String nombreArt) {
        for (int i = 0; i < artistas.size(); i++) {
            Artista temp = artistas.get(i);
            if(temp.getNombreArtistico().equals(nombreArt)) {
                return temp.getNombreArtistico();
            }
        }
        return null;
    }

    public void agregarCompositor(Compositor pCompositor) {
        this.compositores.add(pCompositor);
    }

    public ArrayList<Compositor> getCompositores() {
        return compositores;
    }
    public Compositor getCompositor(String nombreComp) {
        for (int i = 0; i < compositores.size(); i++) {
            Compositor temp = compositores.get(i);
            if(temp.getNombre().equals(nombreComp)) {
                return temp;
            }
        }
        return null;
    }
    public String getNomCompositor(String nombreComp) {
        for (int i = 0; i < compositores.size(); i++) {
            Compositor temp = compositores.get(i);
            if(temp.getNombre().equals(nombreComp)) {
                return temp.getNombre();
            }
        }
        return null;
    }

    public void agregarGeneros(Genero pGenero) {
        this.generos.add(pGenero);
    }

    public ArrayList<Genero> getGeneros() {
        return generos;
    }
    public Genero getGenero(String nombreGen) {
        for (int i = 0; i < generos.size(); i++) {
            Genero temp = generos.get(i);
            if(temp.getNombreGenero().equals(nombreGen)) {
                return temp;
            }
        }
        return null;
    }
    public String getNomGenero(String nombreGen) {
        for (int i = 0; i < generos.size(); i++) {
            Genero temp = generos.get(i);
            if(temp.getNombreGenero().equals(nombreGen)) {
                return temp.getNombreGenero();
            }
        }
        return null;
    }

    public void agregarAlbumes(Album pAlbum) {
        this.albumes.add(pAlbum);
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }
    public String getNomAlbum(String album) {
        for (int i = 0; i < albumes.size(); i++) {
            Album temp = albumes.get(i);
            if(temp.getNombreAlbum().equals(album)) {
                return temp.getNombreAlbum();
            }
        }
        return null;
    }
    public Album getAlbum(String album) {
        for (int i = 0; i < albumes.size(); i++) {
            Album temp = albumes.get(i);
            if(temp.getNombreAlbum().equals(album)) {
                return temp;
            }
        }
        return null;
    }


    public void agregarCancion(Cancion pCancion) {
        this.canciones.add(pCancion);
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }
    public String getNomCancion(String cancion) {
        for (int i = 0; i < canciones.size(); i++) {
            Cancion temp = canciones.get(i);
            if(temp.getNombreCancion().equals(cancion)) {
                return temp.getNombreCancion();
            }
        }
        return null;
    }
    public Cancion getCancion(String cancion) {
        for (int i = 0; i < canciones.size(); i++) {
            Cancion temp = canciones.get(i);
            if(temp.getNombreCancion().equals(cancion)) {
                return temp;
            }
        }
        return null;
    }


    public void agregarLista(ListaReproduccion pLista) { this.listas.add(pLista); }

    public ArrayList<ListaReproduccion> getListas() {
        return listas;
    }



    //----FIN BLOQUE ARRAYLISTS-----

    public void getPaises() {
        int contador = 1;
        for (String countryCode : locales) {

            Locale paises = new Locale("", countryCode);

            System.out.println("Código del país = " + paises.getCountry()
                    + ", nombre del país = " + paises.getDisplayCountry() + ", " +
                    "país número = " + contador);
            contador += 1;
        }
        System.out.println(contador);
        System.out.println("Done");
    }
    public String[] paises() {
        return this.locales;
    }
}
