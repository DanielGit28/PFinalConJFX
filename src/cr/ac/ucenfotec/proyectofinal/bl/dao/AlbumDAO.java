package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 * @version 1.1
 */

public class AlbumDAO {
    Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryAlbum;

    private final String TEMPLATE_CMD_INSERTAR = "insert into usuario_final (avatar,nombre,apellidos,correo,contrasenna,fechaNacimiento,pais,nombreUsuario)" +
            " values (?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSALBUMES = "select * from album";

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public AlbumDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryAlbum = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSALBUMES);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public ArrayList<Album> obtenerAlbumes() throws SQLException {

        ResultSet resultado = queryAlbum.executeQuery();
        ArrayList<Album> listaAlbumes = new ArrayList<>();
        while (resultado.next()){
            Album leido = new Album();
            //leido.setAvatarUsuario(resultado.getString("avatar"));
            //leido.setNombre(resultado.getString("nombre"));
            //leido.setPuntos(resultado.getInt("puntos"));
            listaAlbumes.add(leido);
        }
        return listaAlbumes;
    }

    /**
     *
     * @param nuevo objeto album que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarAlbum(Album nuevo) throws SQLException{
        if(this.cmdInsertar != null) {
            this.cmdInsertar.setString(1,nuevo.getNombreAlbum());
            this.cmdInsertar.setDate(2, Date.valueOf(nuevo.getFechaLanzamiento()));
            this.cmdInsertar.setString(3, nuevo.getArtistaAlbum().getNombreArtistico());
            this.cmdInsertar.setString(4, nuevo.getImagenAlbum());
            this.cmdInsertar.setString(5, nuevo.getNombreAlbum());//SOLUCIONAR RELACION ENTRE TABLAS PARA GUARDAR CANCIONES
            //SE DEBERÍA ENVIAR EL ID DE LA BIBLIOTECA QUE TIENE LAS CANCIONES DEL ALBUM
            this.cmdInsertar.execute();
        } else {
            System.out.println("No se pudo guardar el album");
        }
    }
}
