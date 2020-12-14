package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Album;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Compositor;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.ListaReproduccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 * @version 1.1
 */

public class CompositorDAO {
    Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryCompositores;

    private final String TEMPLATE_CMD_INSERTAR = "insert into compositor (nombre,apellidos,idPaisCompositor,fechaNacimiento,edad)" +
            " values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSCOMPOSITORES = "select * from compositor";

    /**
     *
     * @param conexion  conexión de la clase con la base de datos
     */
    public CompositorDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryCompositores = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSCOMPOSITORES);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Admin encontrarPorId(String cedula){
        return null;
    }

    public List<Compositor> obtenerTodosLosCompositores() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from tcliente");
        ArrayList<Compositor> listaCompositores = new ArrayList<>();
        while (resultado.next()){
            Compositor leido = new Compositor();
            //leido.setAvatarUsuario(resultado.getString("avatar"));
            //leido.setNombre(resultado.getString("nombre"));
            //leido.setPuntos(resultado.getInt("puntos"));
            listaCompositores.add(leido);
        }
        return listaCompositores;
    }

    /**
     *
     * @param nuevo objeto Album que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarCompositor(Compositor nuevo) throws SQLException{
        if(this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2,nuevo.getApellidos());
            this.cmdInsertar.setInt(3, nuevo.getPaisNacimientoCompositor().getIdPais());
            this.cmdInsertar.setDate(4, Date.valueOf(nuevo.getFechaNacimientoCompositor()));
            this.cmdInsertar.setInt(5, nuevo.getEdadCompositor());
            this.cmdInsertar.execute();
        } else {
            System.out.println("No se pudo guardar el compositor");
        }
    }
}
