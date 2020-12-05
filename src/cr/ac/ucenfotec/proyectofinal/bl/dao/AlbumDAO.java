package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Album;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 * @version 1.0
 */

public class AlbumDAO {
    Connection cnx;

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public AlbumDAO(Connection conexion){
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

    /**
     *
     * @param nuevo objeto album que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarAlbum(Album nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into lista_reproduccion_usuario" +
                "(idLista,nombreAlbum,fechaLanzamiento,artista,imagenAlbum,idAlbumCanciones) values ('";
        insertar += "1";
        insertar += ",";
        insertar += nuevo.getNombreAlbum();
        insertar += "','";
        insertar += nuevo.getFechaLanzamiento();
        insertar += "',";
        insertar += nuevo.getArtistaAlbum();
        insertar += "',";
        insertar += nuevo.getImagenAlbum();
        insertar += ")";
        insert.execute(insertar);
    }
}
