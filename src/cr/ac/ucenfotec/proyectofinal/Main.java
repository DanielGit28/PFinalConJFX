package cr.ac.ucenfotec.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Cargar primer escena
        Stage inicio = primaryStage;
        Parent rootInicio = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        inicio.setTitle("Baúl de los recuerdos");
        inicio.setScene(new Scene(rootInicio, 650, 600));
        //inicio.setResizable(false);
        inicio.show();
        
        //cargar segunda escena
        /*
        Stage registro = primaryStage;
        Parent rootRegistro = FXMLLoader.load(getClass().getResource("registro.fxml"));
        registro.setTitle("Registro");
        registro.setScene(new Scene(rootRegistro, 800, 800));
        registro.setResizable(false);

        //Inyectando escena registro en en controlador inicio
        ControladorInicio controladorInicio = new ControladorInicio();
        controladorInicio.setEscenaRegistro(registro);
    */

    }


    public static void main(String[] args) {
        launch(args);
    }
}
