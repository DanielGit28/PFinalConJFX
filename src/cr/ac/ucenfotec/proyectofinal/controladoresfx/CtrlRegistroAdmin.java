package cr.ac.ucenfotec.proyectofinal.controladoresfx;

import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class CtrlRegistroAdmin implements Initializable {
    Gestor gestor = new Gestor();
    Connection cnx;

    Scene escenaRegistro;
    Stage window;
    private String pathImg;

    @FXML
    private TextField lblUsuario;

    @FXML
    private TextField lblNombre;

    @FXML
    private TextField lblCorreo;

    @FXML
    private TextField lblContrasenna;

    @FXML
    private Button registrarAdmin;

    @FXML
    private ImageView ivImagen;

    @FXML
    private TextField lblApellidos;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button inicio;

    public void setEscenaRegistro(Scene scene) {
         escenaRegistro = scene;
    }

    public void abrirEscenaRegistro(ActionEvent actionEvent) {
        Stage stageRegistro = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stageRegistro.setScene(escenaRegistro);
    }

    /*
    @FXML
    private void login(javafx.event.ActionEvent event) throws IOException {
        if (pwf1.getText().equals("dolphin")) {
            Parent blah = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } else {
            label1.setText("Password is incorrect. Please Try Again");
        }
    }*/

    public void cambiarEscena(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../vistas/inicio.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    public void cargarImagen() {
        btnBuscar.setOnAction(event -> {
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

    public void registrarDatosAdmin() throws SQLException {
        String avatar = pathImg;
        String nombre = lblNombre.getText();
        String apellidos = lblApellidos.getText();
        String contrasenna = lblContrasenna.getText();
        String correo = lblCorreo.getText();
        String nombreUsuario = lblUsuario.getText();
        if(gestor.verificarContrasenna(contrasenna) == false ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Contraseña con el formato incorrecto. Debe incluir mayúscula, minúscula, caracteres especiales y números.");
            alert.showAndWait();
        } else {
            gestor.agregarAdmin(avatar,nombre,apellidos,correo,contrasenna,nombreUsuario);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
