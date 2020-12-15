
package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladres_admin;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;
import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */

public class CtrlAdminArtistas implements Initializable {

    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;

    @FXML
    private TableColumn<Artista, Integer> columnEdad;

    @FXML
    private Button btnCanciones;

    @FXML
    private TableColumn<Artista, Integer> columnId;

    @FXML
    private TableColumn<Artista, String> columnApellido;

    @FXML
    private Button inicio;

    @FXML
    private Button btnArtista;

    @FXML
    private Button btnCrearGenero;

    @FXML
    private TableView<Artista> tablaArtistas;

    @FXML
    private Button btnGenero;

    @FXML
    private TableColumn<Artista, String> columnGenero;

    @FXML
    private Button btnActualizar;

    @FXML
    private TextField fieldId;

    @FXML
    private Button cerrarSesion;

    @FXML
    private TableColumn<Artista, String> columnDescripcion;

    @FXML
    private TextField fieldNomActualizar;

    @FXML
    private TableColumn<Artista, String> columnNomArtistico;

    @FXML
    private TextField fieldDescActualizar;

    @FXML
    private Button btnCompositor;

    @FXML
    private TableColumn<Artista, String> columnNombre;

    @FXML
    private TableColumn<Artista, String> columnPais;

    @FXML
    private TextField fieldBusqueda;

    @FXML
    private Button btnAlbum;

    @FXML
    private TableColumn<Artista, LocalDate> columnFechaFallecimiento;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Artista, LocalDate> columnFechaNac;

    private FilteredList<Artista> artistasFilt;

    {
        try {
            artistasFilt = gestor.cargaArtistas();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
     * Este método carga la escena de registro de compositores
     * @param event evento que se genera cuando se aprieta el botón de crear compositor
     * @throws IOException
     */
    public void escenaCrearArtista(ActionEvent event) throws IOException {
        gestor.escenarioCrearArtistas(event,window);
    }

    public boolean buscadorGenero(Artista artista, String textoBuscado){
        return artista.getNombreArtistico().toLowerCase().equals(textoBuscado.toLowerCase());
    }

    private Predicate<Artista> crearPredicate(String textoBuscado){
        return artista -> {
            if (textoBuscado == null || textoBuscado.isEmpty()) return true;
            return buscadorGenero(artista, textoBuscado);
        };
    }



    /**
     * Llama al gestor para actualizar el artista seleccionado
     */
    public void actualizarArtista() {
        Genero genero = new Genero(parseInt(fieldId.getText()),fieldNomActualizar.getText(),fieldDescActualizar.getText() );
        try {
            gestor.actualizarGenero(genero);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*ObservableList<Genero> datos = null;
        try {
            datos = gestor.cargaGeneros();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         */

        tablaArtistas.setItems(artistasFilt);

    }

    /**
     * Llama al gestor para eliminar el artista seleccionado
     */
    public void eliminarArtista() {
        Genero genero = new Genero(parseInt(fieldId.getText()),fieldNomActualizar.getText(),fieldDescActualizar.getText() );
        try {
            gestor.eliminarGenero(genero);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*
        ObservableList<Genero> datos = null;
        try {
            datos = gestor.cargaGeneros();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */

        tablaArtistas.setItems(artistasFilt);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory(new PropertyValueFactory<Artista, Integer>("idGenero"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<Artista, String>("nombreGenero"));

        //columnDescripcion.setCellValueFactory(new PropertyValueFactory<Genero, String>("descripcionGenero"));


        /*
        ObservableList<Genero> datos = null;
        try {
            datos = gestor.cargaGeneros();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */
        //System.out.println(datos);
        //columnNombre.setEditable(true);
        //columnDescripcion.setEditable(true);

        //tablaGeneros.setEditable(true);
        tablaArtistas.setItems(artistasFilt);

        tablaArtistas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TablePosition pos = tablaArtistas.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

                Artista artista = tablaArtistas.getItems().get(row);
                fieldId.setText(String.valueOf(gen.getId()));
                fieldNomActualizar.setText(gen.getNombreGenero());
                fieldDescActualizar.setText(gen.getDescripcionGenero());
            }
        });

        fieldBusqueda.textProperty().addListener((observable, oldValue, newValue) ->
                artistasFilt.setPredicate(crearPredicate(newValue))
        );
    }


}
