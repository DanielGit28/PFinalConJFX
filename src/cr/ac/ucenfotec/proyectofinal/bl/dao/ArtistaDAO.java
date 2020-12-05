package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;

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

public class ArtistaDAO {
    Connection cnx;

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public ArtistaDAO(Connection conexion){
        this.cnx = conexion;
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public List<Artista> obtenerTodosLosArtistas() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from tcliente");
        ArrayList<Artista> listaArtistas = new ArrayList<>();
        while (resultado.next()){
            Artista leido = new Artista();
            leido.setNombreArtista(resultado.getString("nombre"));
            leido.setApellidoArtista(resultado.getString("apellido"));
            leido.setNombreArtistico(resultado.getString("nombreArtistico"));
            leido.setFechaNacimientoArtista(resultado.getString("fechaNacimiento"));
            leido.setFechaFallecimientoArtista(resultado.getString("fechaFallecimiento"));
            leido.setPaisNacimiento(resultado.getString("paisNacimiento"));
            //leido.setGeneroMusicalArtista(resultado.getString("generoMusical"));
            leido.setEdadArtista(resultado.getInt("edadArtista"));
            leido.setDescripcionArtista(resultado.getString("descripcion"));

            //leido.setPuntos(resultado.getInt("puntos"));
            listaArtistas.add(leido);
        }
        return listaArtistas;
    }

    /**
     *
     * @param nuevo objeto artista que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarArtista(Artista nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into album" +
                "(nombre,apellido,nombreArtistico,fechaNacimiento,fechaFallecimiento,paisNacimiento,generoMusical,edadArtista,descripcion) values ('";
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
