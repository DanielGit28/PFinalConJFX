package cr.ac.ucenfotec.proyectofinal;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorRegistro {

    Scene escenaRegistro;

    public void setEscenaRegistro(Scene scene) {
         escenaRegistro = scene;
    }

    public void abrirEscenaRegistro(ActionEvent actionEvent) {
        Stage stageRegistro = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stageRegistro.setScene(escenaRegistro);
    }
}
