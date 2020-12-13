package cr.ac.ucenfotec.proyectofinal.bl.logica;

import cr.ac.ucenfotec.proyectofinal.PropertiesHandler;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.*;
import cr.ac.ucenfotec.proyectofinal.bl.dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author Daniel
 * @version 1.2
 */

public class Gestor {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    Connection connection;

    private PreparedStatement cmdInsertarPaises;
    private PreparedStatement queryPaises;
    private PreparedStatement queryAdmin;
    private final String TEMPLATE_CMD_INSERTAR = "insert into pais (nombrePais, codigoPais) values (?,?)";
    private final String TEMPLATE_QRY_TODOSLOSPAISES = "select * from pais";
    private final String TEMPLATE_QRY_ADMIN = "select * from admin";
    private String[] locales = Locale.getISOCountries();
    private ObservableList<String> listaPaises;
    private String [] listPais;

    AdminDAO adminDAO;
    AlbumDAO albumDAO;
    ArtistaDAO artistaDAO;
    CancionDAO cancionDAO;
    CompositorDAO compositorDAO;
    GeneroDAO generoDAO;
    ListaReproduccionDAO listaReproduccionDAO;
    UsuarioFinalDAO usuarioFinalDAO;

//VARIABLES QUE PROXIMAMENTE SE ELIMINARAN
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
            propertiesHandler.loadProperties();
            String driver = propertiesHandler.getDriver();
            Class.forName(driver).newInstance();
            String url= propertiesHandler.getCnxStr();
            connection = DriverManager.getConnection(url, propertiesHandler.getUser(), propertiesHandler.getPassword());

            this.adminDAO = new AdminDAO(this.connection);
            this.albumDAO = new AlbumDAO(this.connection);
            this.artistaDAO = new ArtistaDAO(this.connection);
            this.cancionDAO = new CancionDAO(this.connection);
            this.compositorDAO = new CompositorDAO(this.connection);
            this.generoDAO = new GeneroDAO(this.connection);
            this.listaReproduccionDAO = new ListaReproduccionDAO(this.connection);
            this.usuarioFinalDAO = new UsuarioFinalDAO(this.connection);

            //--ESTA SECCION CARGA LOS PAISES EN LA BASE DE DATOS CUANDO SE INICIALICE EL CONSTRUCTOR DEL GESTOR--
            this.cmdInsertarPaises = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryPaises = connection.prepareStatement(TEMPLATE_QRY_TODOSLOSPAISES);
            this.queryAdmin = connection.prepareStatement(TEMPLATE_QRY_ADMIN);
            ResultSet resultadoPaises = queryPaises.executeQuery();
            if(resultadoPaises.next()) {
                System.out.println("Paises cargados");
            } else {
                for (String countryCode : locales) {
                    Locale paises = new Locale("", countryCode);
                    if(this.cmdInsertarPaises != null) {
                        this.cmdInsertarPaises.setString(1,paises.getDisplayCountry());
                        this.cmdInsertarPaises.setString(2,paises.getCountry());
                        this.cmdInsertarPaises.execute();
                    } else {
                        System.out.println("No se pudieron insertar los paises");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Esta función carga, con los paises de la BD, cualquier ComboBox que reciba como parámetro
     * @param combo ComboBox que se desea cargar de paises
     * @throws SQLException
     */
    public void cargarPaisesComboBox(ComboBox<String> combo) throws SQLException {
        ResultSet resultadoPaises = queryPaises.executeQuery();
        while(resultadoPaises.next()) {
            combo.getItems().add(resultadoPaises.getString("nombrePais"));
        }
    }

    /**
     * Busca el pais según el nombre en la BD y devuelve el id del pais
     * @param nombrePais nombre del país que se desea buscar
     * @return pais objeto pais
     * @throws SQLException
     */
    public int idPais(String nombrePais) throws SQLException {
        int resultado = 0;
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from pais where nombrePais = '"+nombrePais+"'");
        if(result.next()) {
            resultado = result.getInt("idPais");
        } else {
            System.out.println("No se encontró ningún país con ese nombre");
        }

        return resultado;
    }

    /**
     * Busca el pais según el nombre en la BD y devuelve un objeto Pais
     * @param nombrePais nombre del país que se desea buscar
     * @return pais objeto pais
     * @throws SQLException
     */
    public Pais pais(String nombrePais) throws SQLException {
        Pais pais = new Pais();
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from pais where nombrePais = '"+nombrePais+"'");
        if(result.next()) {
            pais.setIdPais(result.getInt("idPais"));
            pais.setNombrePais(result.getString("nombrePais"));
            pais.setCodigoPais(result.getString("codigoPais"));
        } else {
            System.out.println("No se encontró ningún país con ese nombre");
        }

        return pais;
    }

    /**
     *
     * @param contrasenna Recibe como parametro el string de la contraseña a evaluar
     * @return boolean Devuelve un verdadero o falso según el resultado de la evaluacion del string de la contraseña
     */
    public boolean verificarContrasenna(String contrasenna) {

        int len = contrasenna.length();
        boolean verificacion = false;

        //String formato = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$";
        //String formato = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){8,15}$/";
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";
        String pattern2 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}$";

        if (contrasenna.matches(pattern2)) {
            verificacion = true;
        } else {
            verificacion = false;
        }
        return verificacion;
    }

    /**
     *
     * @param query query que evalúa si existe la información
     * @return true or false según valide si hay información o no
     * @throws SQLException
     */
    public boolean siExiste(String query) throws SQLException {
        boolean verificacion = false;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(query);
        if(rs.next()){
            verificacion = true;
        }
        else{
            verificacion = false;
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
    public void agregarAdmin(String avatar, String nombre, String apellidos, String correo,String contrasenna, String nombreUsuario) throws SQLException {
        Admin admin = new Admin(1,avatar,nombre,apellidos,correo,contrasenna,nombreUsuario);
        if(siExiste("select 1 from admin") == false) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ya existe un administrador registrado");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param correo String del correo para verificar sesión
     * @param contrasenna String de contraseña para verificar sesión
     * @return true or false según valide si la información corresponde al administrador
     * @throws SQLException
     */
    public boolean verificarSesionAdmin(String correo, String contrasenna) throws SQLException {
        boolean validacion = false;
        Statement query = connection.createStatement();
        ResultSet resultadoAdmin = query.executeQuery("select * from admin where correo = '"+correo+"'");
        //ResultSet resultadoUsuario = query.executeQuery("select * from usuario_final");
        if(correo != null && contrasenna != null) {
            //System.out.println(correo + ", "+contrasenna);
            if(resultadoAdmin.next()){
                if(resultadoAdmin.getString("correo").equals(correo) && resultadoAdmin.getString("contrasenna").equals(contrasenna) ) {
                    validacion = true;
                }
            }
        }

        return validacion;
    }



    public void agregarUsuario(String avatar, String nombre, String apellidos, String correo, String contrasenna, LocalDate fechaNac, String nombrePais, String id, String nombreUsuario) throws SQLException {
        int otp = 0;
        ArrayList<ListaReproduccion> listasRep = new ArrayList<>();
        ArrayList<Cancion> canciones = new ArrayList<>();
        Pais pais = pais(nombrePais);

        UsuarioFinal usuario = new UsuarioFinal(1,avatar,nombre,apellidos,correo,contrasenna,fechaNac,pais,id,nombreUsuario,otp,listasRep,canciones);
        String query = "select * from usuario_final where identificacion = '"+id+"'";
        if(siExiste(query) == false) {
            try {
                usuarioFinalDAO.guardarUsuario(usuario);
                alertasInformacion("Registro", "Usuario registrado con éxito");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            creacionAlertas("Ya existe un usuario con esa identificación registrado");
        }
    }


    /**
     *
     * @param correo String del correo de usuario para verificar sesión
     * @param contrasenna String de la contraseña del usuario para verificar sesión
     * @return true or false según valide si la información corresponde a un  usuario registrado
     * @throws SQLException
     */
    public Boolean verificarSesionUsuario(String correo, String contrasenna) throws SQLException {
        boolean validacion = false;
        Statement query = connection.createStatement();
        ResultSet resultado = query.executeQuery("select * from usuario_final where correo = '"+correo+"'");
        if(correo != null && contrasenna != null) {
            if(resultado.next()){
                if(resultado.getString("correo").equals(correo) && resultado.getString("contrasenna").equals(contrasenna) ) {
                    validacion = true;
                }
            }
        }

        return validacion;
    }

    /**
     * Esta función crea una alerta de erorr JavaFx
     * @param x String que describe la alerta
     */
    public void creacionAlertas(String x) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(x);
        alert.showAndWait();
    }

    /**
     * Esta función crea alertas de información JavaFx
     * @param titulo String del titulo de la alerta
     * @param info String que describe la información de la alerta
     */
    public void alertasInformacion(String titulo, String info) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }

    /**
     * Actualiza los datos del administrador
     * @param avatar Path del avatar
     * @param nombre Nombre actualizado del admin
     * @param apellidos Apellidos actualizados del admin
     * @param nombreUsuario nombre de usuario actualizado del admin
     * @throws SQLException
     */
    public void actualizarAdmin(String avatar, String nombre, String apellidos, String nombreUsuario) throws SQLException {
        adminDAO.actualizarDatosAdmin(avatar, nombre, apellidos, nombreUsuario);
    }

    /**
     * Devuelve el administrador de la base de datos
     * @return nuevo Objeto Admin de la BD
     * @throws SQLException
     */
    public Admin getAdmin() throws SQLException {
        ResultSet resultado = queryAdmin.executeQuery();
        Admin nuevo = new Admin();
        if(resultado.next()) {
            nuevo.setAvatarUsuario(resultado.getString("avatar"));
            nuevo.setNombre(resultado.getString("nombre"));
            nuevo.setApellidosUsuario(resultado.getString("apellidos"));
            nuevo.setCorreoUsuario(resultado.getString("correo"));
            nuevo.setContrasennaUsuario(resultado.getString("contrasenna"));
            nuevo.setNombreUsuarioAdmin(resultado.getString("nombreUsuario"));
        }

        return nuevo;
    }

    //FUNCIONES AUN NO TRABAJADAS
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
