package cr.ac.ucenfotec.proyectofinal.bl.logica;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.*;
import cr.ac.ucenfotec.proyectofinal.bl.dao.*;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class Gestor {
    Connection connection;

    AdminDAO adminDAO;
    AlbumDAO albumDAO;
    ArtistaDAO artistaDAO;
    CancionDAO cancionDAO;
    CompositorDAO compositorDAO;
    GeneroDAO generoDAO;
    ListaReproduccionDAO listaReproduccionDAO;
    UsuarioFinalDAO usuarioFinalDAO;


    public String[] locales = Locale.getISOCountries();
    Admin administrador = new Admin();
    ArrayList<UsuarioFinal> usuarios = new ArrayList<> ();
    ArrayList<Artista> artistas = new ArrayList<> ();
    ArrayList<Compositor> compositores = new ArrayList<> ();
    ArrayList<Genero> generos = new ArrayList<> ();
    ArrayList<Album> albumes = new ArrayList<> ();
    ArrayList<Cancion> canciones = new ArrayList<>();
    ArrayList<ListaReproduccion> listas = new ArrayList<>();

    public Gestor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbaulrecuerdos"
                    , "root", "root");
            this.adminDAO = new AdminDAO(this.connection);
            this.albumDAO = new AlbumDAO(this.connection);
            this.artistaDAO = new ArtistaDAO(this.connection);
            this.cancionDAO = new CancionDAO(this.connection);
            this.compositorDAO = new CompositorDAO(this.connection);
            this.generoDAO = new GeneroDAO(this.connection);
            this.listaReproduccionDAO = new ListaReproduccionDAO(this.connection);
            this.usuarioFinalDAO = new UsuarioFinalDAO(this.connection);
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param contrasenna Recibe como parametro el string de la contraseña a evaluar
     * @return boolean Devuelve un verdadero o falso según el resultado de la evaluacion del string de la contraseña
     */
    public boolean verificarContrasenna(String contrasenna) {
        int len = contrasenna.length();
        String formato = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$";
        String contra = null;
        boolean verificacion = false;
        if ((len <= 12) && (len >= 8)) {
            boolean verif = Pattern.matches(formato, contrasenna);
            if (verif == true) {
                verificacion = true;
            }
        }
        return verificacion;
    }

    //------Bloque de agregar objetos a los ArrayList que los almacenan-------

    /**
     *
     * @param avatar directorio del archivo del avatar
     * @param nombre String del nombre del admin
     * @param apellidos String de los apellidos del admin
     * @param contrasenna String de la contraseña del admin
     * @param correo String del correo del admin
     * @param nombreUsuario String del usuario del admin
     * @throws SQLException
     */
    public void agregarAdmin(String avatar, String nombre, String apellidos, String contrasenna, String correo, String nombreUsuario) throws SQLException {
        Admin admin = new Admin("1",avatar,nombre,apellidos,contrasenna,correo,nombreUsuario);

        Statement query = connection.createStatement();

        ResultSet resultado = query.executeQuery("select * from admin");
        if(resultado != null ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ya existe un administrador registrado");
            alert.showAndWait();
        } else {
            try {
                adminDAO.guardarAdmin(admin);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Registro");
                alert.setContentText("Administrador registrado con éxito");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
