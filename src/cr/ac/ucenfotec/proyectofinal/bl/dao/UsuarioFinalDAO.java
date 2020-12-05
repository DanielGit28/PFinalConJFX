package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.UsuarioFinal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFinalDAO {
    Connection cnx;

    /**
     *
     * @param conexion conexión de la clase con la base de datos
     */
    public UsuarioFinalDAO(Connection conexion){
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
     * @param nuevo objeto UsuarioFinal que se va a guardar en la base de datos
     * @throws SQLException
     */
    public void guardarUsuario(UsuarioFinal nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        //insert into tcliente(cedula,nombre,puntos) values ('10000','Silvana',0)
        String insertar = "insert into usuario_final" +
                "(avatar,nombre,apellidos,correo,contrasenna,fechaNacimiento,pais,nombreUsuario) values ('";
        insertar += "1";
        insertar += ",";
        insertar += nuevo.getAvatarUsuario();
        insertar += "','";
        insertar += nuevo.getApellidosUsuario();
        insertar += "',";
        insertar += nuevo.getCorreoUsuario();
        insertar += "',";
        insertar += nuevo.getContrasennaUsuario();
        insertar += "',";
        insertar += nuevo.getFechaNacimientoUsuario();
        insertar += "',";
        insertar += nuevo.getPaisProcedenciaUsuario();
        insertar += "',";
        insertar += nuevo.getNombreUsuario();
        insertar += ")";
        insert.execute(insertar);
    }
}
