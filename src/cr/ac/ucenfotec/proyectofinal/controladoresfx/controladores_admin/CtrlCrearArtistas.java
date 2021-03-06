package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladores_admin;

import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */

public class CtrlCrearArtistas {
    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;


    @FXML
    private Button cerrarSesion;

    @FXML
    private Button btnCanciones;

    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private Button btnCompositor;

    @FXML
    private Button btnArtista;

    @FXML
    private TextField fiedlNombre;

    @FXML
    private Button btnAlbum;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnInicio;

    @FXML
    private TextField fiedlNombreArtistico;

    @FXML
    private ComboBox<String> paisNacimiento;

    @FXML
    private ComboBox<String> generoArtista;

    @FXML
    private Button btnGenero;

    @FXML
    private DatePicker fechaFallecimiento;

    @FXML
    private TextField fieldApellidos;

    @FXML
    private TextArea areaDescripcion;


    /**
     * Guarda el artista en la BD
     * @throws SQLException
     */
    public void guardarArtista() throws SQLException {
        String nombre = fiedlNombre.getText();
        String apellidos = fieldApellidos.getText();
        String nomArtistico = fiedlNombreArtistico.getText();
        String generoMusical = String.valueOf(generoArtista.getValue());
        String pais = String.valueOf(paisNacimiento.getValue());
        LocalDate fechaNac = fechaNacimiento.getValue();
        LocalDate fechaFa = fechaFallecimiento.getValue();
        String descripcion = areaDescripcion.getText();
        if(nombre != null && apellidos != null && pais != null && fechaNac != null && nomArtistico != null && generoMusical != null && descripcion != null) {
            gestor.guardarArtista(nombre,apellidos,nomArtistico,fechaNac,fechaFa,pais,generoMusical,descripcion);
            //gestor.alertasInformacion("Artista","Artista registrado con éxito");
        } else {
            gestor.creacionAlertas("Debe rellenar todos los campos menos el de fallecimiento, si fuera el caso");
        }

    }

    /**
     * Este método devuelve al escenario inicial
     * @param event evento que se genera cuando se aprieta el botón de cerrarSesion
     * @throws IOException
     */
    public void cerrarSesion(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/login.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método devuelve al escenario inicial del admin
     * @param event evento que se genera cuando se aprieta el botón de Inicio
     * @throws IOException
     */
    public void inicio(ActionEvent event) throws IOException {
        gestor.escenarioInicioAdmin(event, window);
    }

    /**
     * Este método carga el escenario de canciones
     * @param event evento que se genera cuando se aprieta el botón de canciones
     * @throws IOException
     */
    public void canciones(ActionEvent event) throws IOException {
        gestor.escenarioCanciones(event, window);
    }

    /**
     * Este método carga el escenario de compositores
     * @param event evento que se genera cuando se aprieta el botón de compositores
     * @throws IOException
     */
    public void compositores(ActionEvent event) throws IOException {
        gestor.escenarioCompositores(event, window);
    }

    /**
     * Este método carga el escenario de artistas
     * @param event evento que se genera cuando se aprieta el botón de artistas
     * @throws IOException
     */
    public void artistas(ActionEvent event) throws IOException {
        gestor.escenarioArtistas(event, window);
    }

    /**
     * Este método carga el escenario de géneros
     * @param event evento que se genera cuando se aprieta el botón de géneros
     * @throws IOException
     */
    public void generos(ActionEvent event) throws IOException {
        gestor.escenarioGeneros(event,window);
    }

    /**
     * Este método carga el escenario de álbumes
     * @param event evento que se genera cuando se aprieta el botón de álbumes
     * @throws IOException
     */
    public void albumes(ActionEvent event) throws IOException {
        gestor.escenarioAlbumes(event, window);
    }

    /**
     * Esta función carga los paises de la BD en el ComboBox paisNacimiento
     * @throws SQLException
     */
    public void cargarPaises() throws SQLException {
        if(paisNacimiento.getItems().size() == 0) {
            gestor.cargarPaisesComboBox(paisNacimiento);
        }
    }

    /**
     * Esta funcion carga los generos musicales de la BD en el comboBox generoArtista
     * @throws SQLException
     */
    public void cargarGeneros() throws SQLException {
        if(generoArtista.getItems().size() == 0) {
            gestor.cargarGenerosComboBox(generoArtista);
        }
    }

}
