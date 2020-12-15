package cr.ac.ucenfotec.proyectofinal.bl.logica;

import cr.ac.ucenfotec.proyectofinal.PropertiesHandler;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.*;
import cr.ac.ucenfotec.proyectofinal.bl.dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    private PreparedStatement queryGeneros;
    private PreparedStatement queryArtistas;
    private PreparedStatement queryAlbumes;
    private PreparedStatement queryCanciones;

    private final String TEMPLATE_CMD_INSERTAR = "insert into pais (nombrePais, codigoPais) values (?,?)";
    private final String TEMPLATE_QRY_TODOSLOSPAISES = "select * from pais";
    private final String TEMPLATE_QRY_ADMIN = "select * from admin";
    private final String TEMPLATE_QRY_GENEROS = "select * from genero";
    private final String TEMPLATE_QRY_ARTISTAS = "select * from artista";
    private final String TEMPLATE_QRY_ALBUMES = "select * from album";
    private final String TEMPLATE_QRY_CANCIONES = "select * from cancion";

    private String[] locales = Locale.getISOCountries();
    private ObservableList<String> listaPaises;
    private String[] listPais;

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
    ArrayList<UsuarioFinal> usuarios = new ArrayList<>();
    ArrayList<Artista> artistas = new ArrayList<>();
    ArrayList<Compositor> compositores = new ArrayList<>();
    ArrayList<Genero> generos = new ArrayList<>();
    ArrayList<Album> albumes = new ArrayList<>();
    ArrayList<Cancion> canciones = new ArrayList<>();
    ArrayList<ListaReproduccion> listas = new ArrayList<>();

    public Gestor() {
        try {
            propertiesHandler.loadProperties();
            String driver = propertiesHandler.getDriver();
            Class.forName(driver).newInstance();
            String url = propertiesHandler.getCnxStr();
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
            this.queryGeneros = connection.prepareStatement(TEMPLATE_QRY_GENEROS);
            this.queryArtistas = connection.prepareStatement(TEMPLATE_QRY_ARTISTAS);
            this.queryAlbumes = connection.prepareStatement(TEMPLATE_QRY_ALBUMES);
            this.queryCanciones = connection.prepareStatement(TEMPLATE_QRY_CANCIONES);

            ResultSet resultadoPaises = queryPaises.executeQuery();
            if (resultadoPaises.next()) {
                System.out.println("Paises cargados");
            } else {
                for (String countryCode : locales) {
                    Locale paises = new Locale("", countryCode);
                    if (this.cmdInsertarPaises != null) {
                        this.cmdInsertarPaises.setString(1, paises.getDisplayCountry());
                        this.cmdInsertarPaises.setString(2, paises.getCountry());
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
        while (resultadoPaises.next()) {
            combo.getItems().add(resultadoPaises.getString("nombrePais"));
        }
    }

    /**
     * Esta función carga, con los géneros de la BD, cualquier ComboBox que reciba como parámetro
     * @param combo ComboBox que se desea cargar de géneros musicales
     * @throws SQLException
     */
    public void cargarGenerosComboBox(ComboBox<String> combo) throws SQLException {
        ResultSet resultadoGeneros = queryGeneros.executeQuery();
        while (resultadoGeneros.next()) {
            combo.getItems().add(resultadoGeneros.getString("nombre"));
        }
    }

    /**
     * Busca el pais según el nombre en la BD y devuelve el id del pais
     *
     * @param nombrePais nombre del país que se desea buscar
     * @return pais objeto pais
     * @throws SQLException
     */
    public int idPais(String nombrePais) throws SQLException {
        int resultado = 0;
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from pais where nombrePais = '" + nombrePais + "'");
        if (result.next()) {
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
        ResultSet result = query.executeQuery("select * from pais where nombrePais = '" + nombrePais + "'");
        if (result.next()) {
            pais.setIdPais(result.getInt("idPais"));
            pais.setNombrePais(result.getString("nombrePais"));
            pais.setCodigoPais(result.getString("codigoPais"));
        } else {
            System.out.println("No se encontró ningún país con ese nombre");
        }

        return pais;
    }

    /**
     * Busca el pais según el id en la BD y devuelve un objeto Pais
     * @param idPais id del país que se desea buscar
     * @return pais objeto pais
     * @throws SQLException
     */
    public Pais getPaisById(int idPais) throws SQLException {
        Pais pais = new Pais();
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from pais where idPais = " + idPais);
        if (result.next()) {
            pais.setIdPais(result.getInt("idPais"));
            pais.setNombrePais(result.getString("nombrePais"));
            pais.setCodigoPais(result.getString("codigoPais"));
        } else {
            System.out.println("No se encontró ningún país con ese nombre");
        }

        return pais;
    }

    /**
     * Busca y devuelve un género musical de la base de datos
     * @param nomGenero recibe un String del nombre del género para buscar en la BD
     * @return genero Objeto Genero con los datos de la BD
     * @throws SQLException
     */
    public Genero getGenero(String nomGenero) throws SQLException {
        Genero genero = new Genero();
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from genero where nombre = '" + nomGenero + "'");
        if (result.next()) {
            genero.setId(result.getInt("idGenero"));
            genero.setNombreGenero(result.getString("nombre"));
            genero.setDescripcionGenero(result.getString("descripcion"));
        } else {
            System.out.println("No se encontró ningún género con ese nombre");
        }

        return genero;
    }

    /**
     * Busca y devuelve un género musical de la base de datos
     * @param idGenero id del género para buscar en la BD
     * @return genero Objeto Genero con los datos de la BD
     * @throws SQLException
     */
    public Genero getGeneroById(int idGenero) throws SQLException {
        Genero genero = new Genero();
        Statement query = connection.createStatement();
        ResultSet result = query.executeQuery("select * from genero where idGenero = " + idGenero);
        if (result.next()) {
            genero.setId(result.getInt("idGenero"));
            genero.setNombreGenero(result.getString("nombre"));
            genero.setDescripcionGenero(result.getString("descripcion"));
        } else {
            System.out.println("No se encontró ningún género con ese nombre");
        }

        return genero;
    }

    /**
     * Devuelve un Observable list del tipo de objeto género para cargar una tabla
     * @return ObservableList del tipo de objeto Genero
     * @throws SQLException
     */
    public FilteredList<Genero> cargaGeneros() throws SQLException {
        ResultSet resultadoGeneros = queryGeneros.executeQuery();
        ObservableList<Genero> generos = FXCollections.observableArrayList();
        while(resultadoGeneros.next()) {
            Genero leido = new Genero();

            leido.setId(resultadoGeneros.getInt("idGenero"));
            leido.setNombreGenero(resultadoGeneros.getString("nombre"));
            leido.setDescripcionGenero(resultadoGeneros.getString("descripcion"));
            //System.out.println(leido.toString());
            generos.add(leido);
        }
        FilteredList<Genero> generosFiltrado = new FilteredList<>(FXCollections.observableList(generos));
        //System.out.println(generos);
        return generosFiltrado;
    }

    /**
     * Devuelve un Observable list del tipo de objeto Artista para cargar una tabla
     * @return ObservableList del tipo de objeto Artista
     * @throws SQLException
     */
    public FilteredList<Artista> cargaArtistas() throws SQLException {
        ResultSet resultadoArtistas = queryArtistas.executeQuery();
        ObservableList<Artista> artistas = FXCollections.observableArrayList();
        while(resultadoArtistas.next()) {
            Artista leido = new Artista();

            leido.setId(resultadoArtistas.getInt("idGenero"));
            leido.setNombreArtista(resultadoArtistas.getString("nombre"));
            leido.setApellidoArtista(resultadoArtistas.getString("apellido"));
            leido.setNombreArtistico(resultadoArtistas.getString("nombreArtistico"));
            leido.setFechaNacimientoArtista(resultadoArtistas.getDate("fechaNacimiento").toLocalDate());
            leido.setFechaFallecimientoArtista(resultadoArtistas.getDate("fechaFallecimiento").toLocalDate());
            leido.setPaisNacimiento(getPaisById(resultadoArtistas.getInt("idPaisArtista")));
            leido.setGeneroMusicalArtista(getGeneroById(resultadoArtistas.getInt("idGeneroArtista")));
            leido.setEdadArtista(resultadoArtistas.getInt("edadArtista"));
            leido.setDescripcionArtista(resultadoArtistas.getString("descripcion"));
            //System.out.println(leido.toString());
            artistas.add(leido);
        }
        FilteredList<Artista> generosFiltrado = new FilteredList<>(FXCollections.observableList(artistas));
        //System.out.println(generos);
        return generosFiltrado;
    }

    /**
     * Actualiza el género recibido en la BD
     * @param genero Objeto género que se actualizara
     * @throws SQLException
     */
    public void actualizarGenero(Genero genero) throws SQLException {
        generoDAO.actualizarGenero(genero);
        //System.out.println(genero.toString());
        alertasInformacion("Género", "Género actualizado exitosamente");
    }

    /**
     * Elimina el genero recibido en la BD
     * @param genero Objeto Genero que se eliminara
     * @throws SQLException
     */
    public void eliminarGenero(Genero genero) throws SQLException {
        generoDAO.eliminarGenero(genero);
        alertasInformacion("Género","Género eliminado exitosamente");
    }


    /**
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
     * @param query query que evalúa si existe la información
     * @return true or false según valide si hay información o no
     * @throws SQLException
     */
    public boolean siExiste(String query) throws SQLException {
        boolean verificacion = false;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(query);
        if (rs.next()) {
            verificacion = true;
        } else {
            verificacion = false;
        }
        return verificacion;
    }

    //------Bloque de agregar objetos a los ArrayList que los almacenan-------

    /**
     * @param avatar        directorio del archivo del avatar
     * @param nombre        String del nombre del admin
     * @param apellidos     String de los apellidos del admin
     * @param contrasenna   String de la contraseña del admin
     * @param correo        String del correo del admin
     * @param nombreUsuario String del usuario del admin
     * @throws SQLException
     */
    public void agregarAdmin(String avatar, String nombre, String apellidos, String correo, String contrasenna, String nombreUsuario) throws SQLException {
        Admin admin = new Admin(1, avatar, nombre, apellidos, correo, contrasenna, nombreUsuario);
        if (siExiste("select 1 from admin") == false) {
            try {
                adminDAO.guardarAdmin(admin);
                alertasInformacion("Registro","Administrador registrado con éxito");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            creacionAlertas("Ya existe un administrador registrado");
        }
    }

    /**
     * @param correo      String del correo para verificar sesión
     * @param contrasenna String de contraseña para verificar sesión
     * @return true or false según valide si la información corresponde al administrador
     * @throws SQLException
     */
    public boolean verificarSesionAdmin(String correo, String contrasenna) throws SQLException {
        boolean validacion = false;
        Statement query = connection.createStatement();
        ResultSet resultadoAdmin = query.executeQuery("select * from admin where correo = '" + correo + "'");
        //ResultSet resultadoUsuario = query.executeQuery("select * from usuario_final");
        if (correo != null && contrasenna != null) {
            //System.out.println(correo + ", "+contrasenna);
            if (resultadoAdmin.next()) {
                if (resultadoAdmin.getString("correo").equals(correo) && resultadoAdmin.getString("contrasenna").equals(contrasenna)) {
                    validacion = true;
                }
            }
        }

        return validacion;
    }

    /**
     * Guarda un usuario en la base de datos
     * @param avatar directorio de la imagen del avatar
     * @param nombre nombre del usuario
     * @param apellidos apellidos del usuario
     * @param correo correo del usuario
     * @param contrasenna contraseña del usuario
     * @param fechaNac fecha de nacimiento del usuario
     * @param nombrePais país de nacimiento del usuario
     * @param id identificación del usuario
     * @param nombreUsuario nombre de usuario de la app
     * @throws SQLException
     */
    public void agregarUsuario(String avatar, String nombre, String apellidos, String correo, String contrasenna, LocalDate fechaNac, String nombrePais, String id, String nombreUsuario) throws SQLException {
        int otp = 0;
        ArrayList<ListaReproduccion> listasRep = new ArrayList<>();
        ArrayList<Cancion> canciones = new ArrayList<>();
        Pais pais = pais(nombrePais);

        UsuarioFinal usuario = new UsuarioFinal(1, avatar, nombre, apellidos, correo, contrasenna, fechaNac, pais, id, nombreUsuario, otp, listasRep, canciones);
        String query = "select * from usuario_final where identificacion = '" + id + "'";
        if (siExiste(query) == false) {
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
     * @param correo      String del correo de usuario para verificar sesión
     * @param contrasenna String de la contraseña del usuario para verificar sesión
     * @return true or false según valide si la información corresponde a un  usuario registrado
     * @throws SQLException
     */
    public Boolean verificarSesionUsuario(String correo, String contrasenna) throws SQLException {
        boolean validacion = false;
        Statement query = connection.createStatement();
        ResultSet resultado = query.executeQuery("select * from usuario_final where correo = '" + correo + "'");
        if (correo != null && contrasenna != null) {
            if (resultado.next()) {
                if (resultado.getString("correo").equals(correo) && resultado.getString("contrasenna").equals(contrasenna)) {
                    validacion = true;
                }
            }
        }

        return validacion;
    }

    /**
     * Esta función crea una alerta de erorr JavaFx
     *
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
     *
     * @param titulo String del titulo de la alerta
     * @param info   String que describe la información de la alerta
     */
    public void alertasInformacion(String titulo, String info) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(info);
        alert.showAndWait();
    }

    /**
     * Calcula la edad según una fecha localDate
     * @param fecha recibe un localDate para calcular la fecha
     * @return
     */
    public int calcEdad(LocalDate fecha) {
        Calendar c1 = Calendar.getInstance();
        int dia = c1.get(Calendar.DATE);
        int mes = c1.get(Calendar.MONTH);
        int annio = c1.get(Calendar.YEAR);
        int edad = 0;

        if (fecha.getMonthValue() < mes) { // si el mes de nacimiento ya pasó, ya cumplió años
            edad = annio - fecha.getYear();
        } else if (fecha.getDayOfMonth() < dia && fecha.getMonthValue() == mes) { // si el dia de nacimiento ya pasó, ya cumplió años
            edad = annio - fecha.getYear();
        } else if (fecha.getDayOfMonth() > dia && fecha.getMonthValue() == mes) { //si el mes de nacimiento es igual al actual y el dia de nacimiento es mayor al dia actual, aún no ha cumplido años este año
            edad = (annio - fecha.getYear()) - 1;
        } else if (fecha.getMonthValue() > mes) { //si el mes de nacimiento es mayor al actual, aún no ha cumplido años este año
            edad = (annio - fecha.getYear()) - 1;
        }

        return edad;
    }


    /**
     * Actualiza los datos del administrador
     * @param avatar        Path del avatar
     * @param nombre        Nombre actualizado del admin
     * @param apellidos     Apellidos actualizados del admin
     * @param nombreUsuario nombre de usuario actualizado del admin
     * @throws SQLException
     */
    public void actualizarAdmin(String avatar, String nombre, String apellidos, String nombreUsuario) throws SQLException {
        adminDAO.actualizarDatosAdmin(avatar, nombre, apellidos, nombreUsuario);
    }

    /**
     * Devuelve el administrador de la base de datos
     *
     * @return nuevo Objeto Admin de la BD
     * @throws SQLException
     */
    public Admin getAdmin() throws SQLException {
        ResultSet resultado = queryAdmin.executeQuery();
        Admin nuevo = new Admin();
        if (resultado.next()) {
            nuevo.setAvatarUsuario(resultado.getString("avatar"));
            nuevo.setNombre(resultado.getString("nombre"));
            nuevo.setApellidosUsuario(resultado.getString("apellidos"));
            nuevo.setCorreoUsuario(resultado.getString("correo"));
            nuevo.setContrasennaUsuario(resultado.getString("contrasenna"));
            nuevo.setNombreUsuarioAdmin(resultado.getString("nombreUsuario"));
        }

        return nuevo;
    }

    /**
     * Guarda los datos del compositor en la base de datos
     * @param nombre nombre del compositor
     * @param apellidos apellidos del compositor
     * @param paisNacimiento pais de nacimiento del compositor
     * @param fechaNacimiento fecha de nacimiento del compositor
     * @throws SQLException
     */
    public void guardarCompositor(String nombre, String apellidos, String paisNacimiento, LocalDate fechaNacimiento) throws SQLException {
            Pais paisNac = pais(paisNacimiento);
            int edadCompositor = calcEdad(fechaNacimiento);
            Compositor compositor = new Compositor(1,nombre,apellidos,paisNac,fechaNacimiento,edadCompositor);
            if(siExiste("select * from compositor where nombre '"+nombre+"' and apellidos = '"+apellidos+"'") == false) {
                compositorDAO.guardarCompositor(compositor);
            } else {
                creacionAlertas("Compositor ya existente");
            }

    }

    /**
     * Guarda un artista en la base de datos
     * @param nombre nombre del artista
     * @param apellidos apellidos del artista
     * @param nombreArtistico nombre artístico
     * @param fechaNacimiento fecha nacimiento del artista
     * @param fechaFallecimiento fecha fallecimiento del artista, si aplica
     * @param paisNacimiento país nacimiento del artista
     * @param generoMusical género musical del artista
     * @param descripcion descripción del artista
     * @throws SQLException
     */
    public void guardarArtista(String nombre, String apellidos, String nombreArtistico,LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String paisNacimiento, String generoMusical, String descripcion) throws SQLException {
        Pais paisNac = pais(paisNacimiento);
        int edadArtista = calcEdad(fechaNacimiento);
        Genero generoArt = getGenero(generoMusical);
        Artista artista = new Artista(1,nombre,apellidos,nombreArtistico,fechaNacimiento,fechaFallecimiento,paisNac,generoArt,edadArtista,descripcion);
        if(siExiste("select * from artista where nombreArtistico = '"+nombreArtistico+"'") == false) {
            
            artistaDAO.guardarArtista(artista);
        } else {
            creacionAlertas("Artista ya existente");
        }

    }

    /**
     * Guarda un género musical en la base de datos
     * @param nombre nombre del género
     * @param descripcion descripción del género
     * @throws SQLException
     */
    public void guardarGenero(String nombre, String descripcion) throws SQLException {
        Genero genero = new Genero(1,nombre,descripcion);
        if(siExiste("select * from genero where nombre = '"+nombre+"'") == false) {
            generoDAO.guardarGenero(genero);
        } else {
            creacionAlertas("Género existente");
        }
    }

    //--CARGA DE ESCENAS ADMIN--
    /**
     * Este método carga el escenario de canciones
     * @param event evento que se genera cuando se aprieta el botón de canciones
     * @throws IOException
     */
    public void escenarioCanciones(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdminCanciones.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de guardar canciones
     * @param event evento que se genera cuando se aprieta el botón de registrar canciones
     * @throws IOException
     */
    public void escenarioGuardarCanciones(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/agregarCanciones.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }
    
    /**
     * Este método carga el escenario de compositores
     * @param event evento que se genera cuando se aprieta el botón de compositores
     * @throws IOException
     */
    public void escenarioCompositores(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdminCompositores.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de agregar compositores
     * @param event evento que se genera cuando se aprieta el botón de crear compositor
     * @throws IOException
     */
    public void escenarioCrearCompositores(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/crearCompositores.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }
    

    /**
     * Este método carga el escenario de inicio de admin
     * @param event evento que se genera cuando se aprieta el botón de inicio
     * @throws IOException
     */
    public void escenarioInicioAdmin(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdmin.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de artistas
     * @param event evento que se genera cuando se aprieta el botón de artistas
     * @throws IOException
     */
    public void escenarioArtistas(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdminArtistas.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de agregar artistas
     * @param event evento que se genera cuando se aprieta el botón de crear artista
     * @throws IOException
     */
    public void escenarioCrearArtistas(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/crearArtistas.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de géneros
     * @param event evento que se genera cuando se aprieta el botón de géneros
     * @throws IOException
     */
    public void escenarioGeneros(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdminGeneros.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de crear géneros
     * @param event evento que se genera cuando se aprieta el botón de registrar géneros
     * @throws IOException
     */
    public void escenarioCrearGeneros(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/crearGeneros.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga el escenario de álbumes
     * @param event evento que se genera cuando se aprieta el botón de álbumes
     * @throws IOException
     */
    public void escenarioAlbumes(ActionEvent event, Stage window) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/vistas_admin/menuAdminAlbumes.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }
    
    
    //--FIN CARGA DE ESCENAS ADMIN--
    
}



