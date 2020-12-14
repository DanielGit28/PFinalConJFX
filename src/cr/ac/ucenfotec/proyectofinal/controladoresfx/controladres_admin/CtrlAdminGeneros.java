package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladres_admin;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;
import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */

public class CtrlAdminGeneros implements Initializable {

    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;

    @FXML
    private Button btnCrearGenero;

    @FXML
    private Button cerrarSesion;

    @FXML
    private TableView<Genero> tablaGeneros;

    @FXML
    private Button btnAlbum;

    @FXML
    private TableColumn<Genero, String> columnDescripcion;

    @FXML
    private TableColumn<Genero, String> columnId;

    @FXML
    private Button btnCanciones;

    @FXML
    private Button btnCompositor;

    @FXML
    private Button inicio;

    @FXML
    private TableColumn<Genero, String> columnNombre;

    @FXML
    private Button btnArtista;

    @FXML
    private Button btnGenero;

    @FXML
    private TextField fieldBusqueda;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;


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
     * Este método carga la escena de registro de géneros
     * @param event evento que se genera cuando se aprieta el botón de crear género
     * @throws IOException
     */
    public void escenaCrearGenero(ActionEvent event) throws IOException {
        gestor.escenarioCrearGeneros(event,window);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        TableColumn<Genero, String> colNom = new TableColumn<>("Nombre");
        colNom.setMinWidth(170);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Genero, String> colDesc = new TableColumn<>("Descripción");
        colNom.setMinWidth(330);
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        */
        columnId.setCellValueFactory(new PropertyValueFactory<Genero, String>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<Genero, String>("nombreGenero"));
        columnNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescripcion.setCellValueFactory(new PropertyValueFactory<Genero, String>("descripcionGenero"));
        columnDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<Genero> datos = null;
        try {
            datos = gestor.generosObservable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(datos);
        tablaGeneros.setItems(datos);

    }
}
