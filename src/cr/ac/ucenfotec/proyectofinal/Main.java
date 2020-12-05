package cr.ac.ucenfotec.proyectofinal;

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
        inicio.setScene(new Scene(rootInicio, 590, 590));
        inicio.setResizable(false);
        //inicio.setResizable(false);
        inicio.show();
        
        //cargar segunda escena
        /*
        Stage registro = primaryStage;
        Parent rootRegistro = FXMLLoader.load(getClass().getResource("registroAdmin.fxml"));
        registro.setTitle("Registro");
        registro.setScene(new Scene(rootRegistro, 800, 800));
        registro.setResizable(false);

        //Inyectando escena registro en en controlador inicio
        CtrlInicio controladorInicio = new CtrlInicio();
        controladorInicio.setEscenaRegistro(registro);
    */

/*
        PropertiesHandler propertiesHandler = new PropertiesHandler();
        propertiesHandler.loadProperties();

        String driver = propertiesHandler.getDriver();
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("LOADED DRIVER ---> " + driver);
        String url= propertiesHandler.getCnxStr();
        Connection con = DriverManager.getConnection(url, propertiesHandler.getUser(), propertiesHandler.getPassword());
        System.out.println("CONNECTED TO ---> "+ url);

        Statement insertcmd = con.createStatement();

        //Es mejor utilizar esta forma con los nombres de las columnas, para evitar los posibles desacomodos en la tabla de la base de datos
        ResultSet results2 = insertcmd.executeQuery("select * from tpersona");
        while(results2.next()) {
            System.out.println(results2.getString("cedula") + " " + results2.getString("nombre") + " "  + results2.getString("apellido1") + " "  + results2.getString("apellido2"));
        }
        con.close();
        System.out.println("Cerrada?"+con.isClosed());

 */
    }


    public static void main(String[] args) throws IOException, SQLException {

        launch(args);
    }

}
