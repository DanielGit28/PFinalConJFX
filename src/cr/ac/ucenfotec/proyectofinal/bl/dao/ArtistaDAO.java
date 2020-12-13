package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 * @version 1.1
 */

public class ArtistaDAO {
    Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryArtista;

    private final String TEMPLATE_CMD_INSERTAR = "insert into album (nombre,apellido,nombreArtistico,fechaNacimiento,fechaFallecimiento,paisNacimiento,generoMusical,edadArtista,descripcion)" +
            " values (?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_BUSCARARTISTAS = "select * from artista";

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public ArtistaDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryArtista = cnx.prepareStatement(TEMPLATE_QRY_BUSCARARTISTAS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
            leido.setFechaNacimientoArtista(resultado.getDate("fechaNacimiento").toLocalDate());
            leido.setFechaFallecimientoArtista(resultado.getDate("fechaFallecimiento").toLocalDate());
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
        if(this.cmdInsertar != null) {
            this.cmdInsertar.setString(1,nuevo.getNombreArtista());
            this.cmdInsertar.setString(2,nuevo.getApellidoArtista());
            this.cmdInsertar.setString(3, nuevo.getNombreArtistico());
            this.cmdInsertar.setDate(4, Date.valueOf(nuevo.getFechaNacimientoArtista()));
            this.cmdInsertar.setDate(5, Date.valueOf(nuevo.getFechaFallecimientoArtista()));
            this.cmdInsertar.setString(6, nuevo.getPaisNacimiento());
            this.cmdInsertar.setString(7, nuevo.getGeneroMusicalArtista().getNombreGenero());
            /*
            *HACER CAMBIO EN LA BASE DE DATOS CON LAS RELACIONES ENTRE OBJETOS, DEBERIA IR EL ID DEL GENERO DE LA BASE DE DATOS, NO EL NOMBRE
             */
            this.cmdInsertar.setInt(8, nuevo.getEdadArtista());
            this.cmdInsertar.setString(9, nuevo.getDescripcionArtista());
            this.cmdInsertar.execute();
        } else {
            System.out.println("No se pudo guardar el artista");
        }
    }
}
