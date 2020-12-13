package cr.ac.ucenfotec.proyectofinal.controladoresfx.contraladores_usuario;

import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.1
 */

public class CtrlMenuUsuario {
    Gestor gestor = new Gestor();
    Connection cnx;

    Scene escenaRegistro;
    Stage window;

    @FXML
    private Button cerrarSesion;

    /**
     * Este método devuelve al escenario inicial
     * @param event evento que se genera cuando se aprieta el botón de cerrarSesion
     * @throws IOException
     */
    public void cerrarSesion(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../vistas/inicio.fxml"));
        Scene vistaLogin = new Scene(login);

        //Esta linea agarra la informacion del escenario (stage o window)
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaLogin);
        window.show();
    }
}
