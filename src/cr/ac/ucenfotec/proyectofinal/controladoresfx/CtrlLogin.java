package cr.ac.ucenfotec.proyectofinal.controladoresfx;

import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CtrlLogin implements Initializable {
    Gestor gestor = new Gestor();
    Connection cnx;

    Scene escenaLogin;
    Stage window;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField fiedlContrasenna;

    @FXML
    private TextField fiedlCorreo;


    public void setEscenaRegistro(Scene scene) {
        escenaLogin = scene;
    }

    public void abrirEscenaRegistro(ActionEvent actionEvent) {
        Stage stageRegistro = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stageRegistro.setScene(escenaLogin);
    }

    public void cambiarEscena(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../vistas/admin.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
