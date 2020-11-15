package cr.ac.ucenfotec.proyectofinal;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.*;
import cr.ac.ucenfotec.proyectofinal.bl.logica.Gestor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class ControladorInicio {

    @FXML
    private TextArea tituloEscena;
    @FXML
    private TextField descripcionInicio;
    @FXML
    private Button botonRegistrarAdmin;

    Scene escenaInicio;

    public void setEscenaRegistro(Scene scene) {
        escenaInicio = scene;
    } 

    public void abrirEscenaRegistro(ActionEvent actionEvent) {
        Stage stageInicio = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stageInicio.setScene(escenaInicio);
    }
    
/*
    public void cambiarEscena(ActionEvent actionEvent, window, Scene scene) {
        botonRegistrarAdmin.setOnAction(event -> {
            window.setScene(scene);
        });
    }
    */
}
