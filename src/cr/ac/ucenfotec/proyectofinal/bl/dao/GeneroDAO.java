package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    Connection cnx;

    public GeneroDAO(Connection conexion){
        this.cnx = conexion;
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public List<Genero> obtenerTodosLosGeneros() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from tcliente");
        ArrayList<Genero> listaGeneros = new ArrayList<>();
        while (resultado.next()){
            Genero leido = new Genero();
            leido.setNombreGenero(resultado.getString("nombre"));
            leido.setDescripcionGenero(resultado.getString("descripcion"));
            //leido.setPuntos(resultado.getInt("puntos"));
            listaGeneros.add(leido);
        }
        return listaGeneros;
    }

    public void guardarGenero(Genero nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into lista_reproduccion_usuario" +
                "(idLista,fechaCreacion,nombreLista) values ('";
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