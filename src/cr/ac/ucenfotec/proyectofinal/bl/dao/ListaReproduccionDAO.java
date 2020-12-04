package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.ListaReproduccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListaReproduccionDAO {
    Connection cnx;

    public ListaReproduccionDAO(Connection conexion){
        this.cnx = conexion;
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public List<Admin> obtenerTodosLosClientes() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from tcliente");
        ArrayList<Admin> listaClientes = new ArrayList<>();
        while (resultado.next()){
            Admin leido = new Admin();
            leido.setAvatarUsuario(resultado.getString("avatar"));
            leido.setNombre(resultado.getString("nombre"));
            //leido.setPuntos(resultado.getInt("puntos"));
            listaClientes.add(leido);
        }
        return listaClientes;
    }

    public void guardarListaReproduccion(ListaReproduccion nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into lista_reproduccion_usuario" +
                "(idLista,fechaCreacion,nombreLista) values ('";
        insertar += "1";
        insertar += ",";
        insertar += nuevo.getFechaCreacionListaReproduccion();
        insertar += "','";
        insertar += nuevo.getNombreListaReproduccion();
        insertar += "',";
        insertar += nuevo.getCalificacionReproduccion();
        insertar += ")";
        insert.execute(insertar);
    }
}
