package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.UsuarioFinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 * @version 1.1
 */

public class UsuarioFinalDAO {
    Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryClientes;

    private final String TEMPLATE_CMD_INSERTAR = "insert into usuario_final (avatar,nombre,apellidos,correo,contrasenna,fechaNacimiento,idPais,identificacion,nombreUsuario)" +
            " values (?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSUSUARIOS = "select * from usuario_final";

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public UsuarioFinalDAO(Connection conexion){
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryClientes = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSUSUARIOS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
     * @param nuevo objeto UsuarioFinal que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarUsuario(UsuarioFinal nuevo) throws SQLException{
        if(this.cmdInsertar != null) {
            String pathImg = nuevo.getAvatarUsuario().replace("\\", "\\\\");
            this.cmdInsertar.setString(1,pathImg);
            this.cmdInsertar.setString(2,nuevo.getNombreUsuario());
            this.cmdInsertar.setString(3, nuevo.getApellidosUsuario());
            this.cmdInsertar.setString(4, nuevo.getCorreoUsuario());
            this.cmdInsertar.setString(5, nuevo.getContrasennaUsuario());
            this.cmdInsertar.setDate(6, Date.valueOf(nuevo.getFechaNacimientoUsuario()));
            this.cmdInsertar.setInt(7, nuevo.getPaisProcedenciaUsuario().getIdPais());
            this.cmdInsertar.setString(8, nuevo.getIdentificacionUsuario());
            this.cmdInsertar.setString(9, nuevo.getNombreUsuario());
            this.cmdInsertar.execute();
        } else {
            System.out.println("No se pudo guardar el cliente");
        }
    }
}
