package cr.ac.ucenfotec.proyectofinal;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.UsuarioHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    /*
    *INFO IMPORTANTE DE ESTILOS DEL PROGRAMA
    * TONO NARANJA: #ff8000
    * TONO AZUL:    #26a8d4
     */


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Cargar primer escena
        Stage inicio = primaryStage;
        Parent rootInicio = FXMLLoader.load(getClass().getResource("vistas/inicio.fxml"));
        inicio.setTitle("Baúl de los recuerdos");
        inicio.setScene(new Scene(rootInicio, 790, 790));
        inicio.setResizable(false);
        //inicio.setResizable(false);
        inicio.show();

    }


    public static void main(String[] args) throws IOException, SQLException {
        UsuarioHolder holder = new UsuarioHolder();
        launch(args);
    }

}
