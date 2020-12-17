
package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladres_admin;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */

public class CtrlCrearAlbumes implements Initializable {

    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;

    @FXML
    private TableColumn<Cancion, String> columnRecurso;

    @FXML
    private Button btnCanciones;

    @FXML
    private TableColumn<Cancion, Integer> columnId;

    @FXML
    private TableColumn<Cancion, Integer> columnCancionSimple;

    @FXML
    private Button inicio;

    @FXML
    private Button btnArtista;

    @FXML
    private TableView<Cancion> tablaCanciones;


    @FXML
    private Button btnCrearCanción;


    @FXML
    private TableColumn<Cancion, LocalDate> columnFechaLanz;

    @FXML
    private Button btnGenero;

    @FXML
    private TableColumn<Cancion, String> columnAlbum;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button cerrarSesion;

    @FXML
    private TableColumn<Cancion, String> columnCompositor;

    @FXML
    private DatePicker fieldFechaLanz;


    @FXML
    private TableColumn<Cancion, String> columnNombre;

    @FXML
    private TextField fieldBusqueda;

    @FXML
    private TableColumn<Cancion, String> columnArtista;

    @FXML
    private TableColumn<Cancion, String> columnGenero;

    @FXML
    private Button btnAlbum;

    @FXML
    private Button btnQuitarCancion;

    @FXML
    private Button btnAgregarCancion;

    @FXML
    private TextField fiedlNombre;

    @FXML
    private ComboBox<String> fieldArtista;

    @FXML
    private DatePicker fechaLanzamiento;

    @FXML
    private ImageView ivImagen;

    @FXML
    private Button btnImagen;


    Cancion cancionSeleccionada = new Cancion();
    ArrayList<Cancion> cancionesSeleccionadas = new ArrayList<>();
    String pathImg;

    private FilteredList<Cancion> cancionFilt;


    {
        try {
            cancionFilt = gestor.cargaCanciones();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Este método devuelve al escenario inicial del admin
     *
     * @param event evento que se genera cuando se aprieta el botón de Inicio
     * @throws IOException
     */
    public void inicio(ActionEvent event) throws IOException {
        gestor.escenarioInicioAdmin(event, window);
    }

    /**
     * Este método carga el escenario de canciones
     *
     * @param event evento que se genera cuando se aprieta el botón de canciones
     * @throws IOException
     */
    public void canciones(ActionEvent event) throws IOException {
        gestor.escenarioCanciones(event, window);
    }

    /**
     * Este método carga el escenario de compositores
     *
     * @param event evento que se genera cuando se aprieta el botón de compositores
     * @throws IOException
     */
    public void compositores(ActionEvent event) throws IOException {
        gestor.escenarioCompositores(event, window);
    }

    /**
     * Este método carga el escenario de artistas
     *
     * @param event evento que se genera cuando se aprieta el botón de artistas
     * @throws IOException
     */
    public void artistas(ActionEvent event) throws IOException {
        gestor.escenarioArtistas(event, window);
    }

    /**
     * Este método carga el escenario de géneros
     *
     * @param event evento que se genera cuando se aprieta el botón de géneros
     * @throws IOException
     */
    public void generos(ActionEvent event) throws IOException {
        gestor.escenarioGeneros(event, window);
    }

    /**
     * Este método carga el escenario de álbumes
     *
     * @param event evento que se genera cuando se aprieta el botón de álbumes
     * @throws IOException
     */
    public void albumes(ActionEvent event) throws IOException {
        gestor.escenarioAlbumes(event, window);
    }

    /**
     * Este método devuelve al escenario inicial
     *
     * @param event evento que se genera cuando se aprieta el botón de cerrarSesion
     * @throws IOException
     */
    public void cerrarSesion(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../../vistas/login.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    /**
     * Este método carga la escena de registro de canciones
     *
     * @param event evento que se genera cuando se aprieta el botón de crear canciones
     * @throws IOException
     */
    public void escenaCrearCancion(ActionEvent event) throws IOException {
        gestor.escenarioGuardarCanciones(event, window);
    }

    public boolean buscadorCancion(Cancion cancion, String textoBuscado) {
        return cancion.getNombreCancion().toLowerCase().contains(textoBuscado.toLowerCase()) ||
                cancion.getAlbumCancion().getNombreAlbum().toLowerCase().contains(textoBuscado.toLowerCase()) ||
                cancion.getGeneroCancion().getNombreGenero().toLowerCase().contains(textoBuscado.toLowerCase()) ||
                cancion.getArtistaCancion().getNombreArtistico().toLowerCase().contains(textoBuscado.toLowerCase());
    }

    private Predicate<Cancion> crearPredicate(String textoBuscado) {
        return cancion -> {
            if (textoBuscado == null || textoBuscado.isEmpty()) return true;
            return buscadorCancion(cancion, textoBuscado);
        };
    }

    /**
     * Este método carga la imagen que se selecciona en el registro
     */
    public void cargarImagen() {
        btnImagen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            File imgFile = fileChooser.showOpenDialog(window);

            // Mostar la imagen
            if (imgFile != null) {
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                ivImagen.setImage(image);
                pathImg = String.valueOf(imgFile);
                //System.out.println("DIR "+pathImg);
            }
        });
    }

    /**
     * Esta función carga los paises de la BD en el ComboBox fieldArtista
     *
     * @throws SQLException
     */
    public void cargarArtistas() throws SQLException {
        if (fieldArtista.getItems().size() == 0) {
            gestor.cargarArtistasComboBox(fieldArtista);
        }
    }

    /**
     * Esta función quita la canción seleccionada, en la tabla, del ArrayList de canciones local del controlador
     */
    public void quitarCancionArray() {

    }

    /**
     * Esta función agrega la canción seleccionada, en la tabla al ArrayList de canciones local del controlador
     */
    public void agregarCancionArray() {
        if(cancionSeleccionada.getAlbumCancion() != null) {
            gestor.creacionAlertas("No se puede agregar esta canción porque ya pertence a otro álbum");
        }else {
            cancionesSeleccionadas.add(cancionSeleccionada);
        }

    }

    public void guardarAlbum() {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cancionFilt = gestor.cargaCanciones();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        columnId.setCellValueFactory(new PropertyValueFactory<Cancion, Integer>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<Cancion, String>("nombreCancion"));
        columnArtista.setCellValueFactory(new PropertyValueFactory<Cancion, String>("nombreArtista"));
        columnCompositor.setCellValueFactory(new PropertyValueFactory<Cancion, String>("nombreCompositor"));
        columnFechaLanz.setCellValueFactory(new PropertyValueFactory<Cancion, LocalDate>("fechaLanzamientoCancion"));
        columnGenero.setCellValueFactory(new PropertyValueFactory<Cancion, String>("nombreGenero"));
        columnCancionSimple.setCellValueFactory(new PropertyValueFactory<Cancion, Integer>("cancionSimple"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Cancion, String>("nombreAlbum"));
        columnRecurso.setCellValueFactory(new PropertyValueFactory<Cancion, String>("recurso"));

        tablaCanciones.setItems(cancionFilt);

        tablaCanciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TablePosition pos = tablaCanciones.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

                cancionSeleccionada = tablaCanciones.getItems().get(row);

            }
        });

        fieldBusqueda.textProperty().addListener((observable, oldValue, newValue) ->
                cancionFilt.setPredicate(crearPredicate(newValue))
        );
    }


}
