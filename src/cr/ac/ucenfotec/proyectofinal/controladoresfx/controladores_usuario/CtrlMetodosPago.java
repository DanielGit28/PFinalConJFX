package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladores_usuario;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.MetodoPago;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.UsuarioFinal;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.UsuarioHolder;
import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.1
 */

public class CtrlMetodosPago implements Initializable {
    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;

    private String pathImg;

    @FXML
    private Button cerrarSesion;

    @FXML
    private Button btnListaReproduccion;

    @FXML
    private TextField fieldNumeroTarjetaRegistrar;

    @FXML
    private Button btnCanciones;

    @FXML
    private Button bntMetodosPago;

    @FXML
    private PasswordField fieldCodigoSeguridad;

    @FXML
    private Button btnRegistrarTarjeta;

    @FXML
    private Button btnSubirCancion;

    @FXML
    private DatePicker fieldFechaVenc;

    @FXML
    private Button btnAlbum;

    @FXML
    private DatePicker fieldFechaVencRegistrar;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnComprarCancion;

    @FXML
    private Button btnEliminarTarjeta;

    @FXML
    private TextField fieldNumeroTarjeta;

    @FXML
    private ComboBox<Integer> fieldTarjeta;


    UsuarioFinal usuario;


    /**
     * Este método carga el escenario de canciones
     * @param event evento que se genera cuando se aprieta el botón de canciones
     * @throws IOException
     */
    public void escenaCanciones(ActionEvent event) throws IOException {
        gestor.escenarioCancionesUsuario(event, window);
    }

    /**
     * Este método carga el escenario de compositores
     * @param event evento que se genera cuando se aprieta el botón de compositores
     * @throws IOException
     */
    public void escenaSubirCanciones(ActionEvent event) throws IOException {
        gestor.escenarioSubirCancionesUsuario(event, window);
    }

    /**
     * Este método carga el escenario de artistas
     * @param event evento que se genera cuando se aprieta el botón de artistas
     * @throws IOException
     */
    public void escenaComprarCancion(ActionEvent event) throws IOException {
        gestor.escenarioComprarCancionUsuario(event, window);
    }

    /**
     * Este método carga el escenario de géneros
     * @param event evento que se genera cuando se aprieta el botón de géneros
     * @throws IOException
     */
    public void escenaListasReproduccion(ActionEvent event) throws IOException {
        gestor.escenarioListasReproduccionUsuario(event,window);
    }

    /**
     * Este método carga el escenario de álbumes
     * @param event evento que se genera cuando se aprieta el botón de álbumes
     * @throws IOException
     */
    public void escenaAlbumes(ActionEvent event) throws IOException {
        gestor.escenarioAlbumesUsuario(event, window);
    }

    /**
     * Este método carga el escenario de métodos de pago del usuario
     * @param event evento que se genera cuando se aprieta el botón de métodos de pago
     * @throws IOException
     */
    public void escenaMetodosPago(ActionEvent event) throws IOException {
        gestor.escenarioMetodosPago(event, window);
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
     * Esta función carga los paises de la BD en el ComboBox paisNacimiento
     * @throws SQLException
     */
    public void cargarTarjetas() throws SQLException {
        if(fieldTarjeta.getItems().size() == 0) {
            gestor.cargarTarjetasComboBox(fieldTarjeta, usuario.getId());
            cargarDatosTarjeta(usuario.getId(), fieldTarjeta.getValue());
        }
    }

    public void cargarDatosTarjeta(int idUsuario, int numeroTarjeta) throws SQLException {
        MetodoPago tarjeta = gestor.getMetodoPagoByIdUsuario(idUsuario, numeroTarjeta);
        fieldNumeroTarjeta.setText(String.valueOf(tarjeta.getNumeroTarjeta()));
        fieldFechaVenc.setValue(tarjeta.getFechaVencimiento());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //datosRecibidos(usuario);

        //UsuarioHolder holder = UsuarioHolder.getInstance();
        usuario = UsuarioHolder.getInstance().getUsuario();

        //System.out.println(usuario.toString());

        //System.out.println("Usuario en menuUsuario "+usuario.toString());

    }
}
