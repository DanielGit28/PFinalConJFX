package cr.ac.ucenfotec.proyectofinal.controladoresfx.controladres_admin;

import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */

public class CtrlAdminCanciones {

    Gestor gestor = new Gestor();

    Scene escenaRegistro;
    Stage window;


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

}
