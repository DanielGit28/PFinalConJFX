package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {
    Connection cnx;

    public CancionDAO(Connection conexion){
        this.cnx = conexion;
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public List<Cancion> obtenerTodosLasCanciones() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from tcliente");
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        while (resultado.next()){
            Cancion leido = new Cancion();
            leido.setNombreCancion(resultado.getString("nombre"));
            //leido.setArtistaCancion(resultado.getString("artista"));
            //leido.setPuntos(resultado.getInt("puntos"));
            listaCanciones.add(leido);
        }
        return listaCanciones;
    }

    public void guardarCancion(Cancion nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into cancion" +
                "(nombre,) values ('";
        insertar += "1";
        insertar += ",";
        //insertar += nuevo.getFechaCreacionListaReproduccion();
        insertar += "','";
        //insertar += nuevo.getNombreListaReproduccion();
        insertar += "',";
        //insertar += nuevo.getCalificacionReproduccion();
        insertar += ")";
        insert.execute(insertar);
    }
}