package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Daniel
 * @version 1.0
 */

public class AdminDAO {
    Connection cnx;

    /**
     *
     * @param conexion realiza la conexión de la clase con la base
     */
    public AdminDAO(Connection conexion){
        this.cnx = conexion;
    }
/*
    public Admin encontrarPorId(String cedula){
        return null;
    }

    public Admin obtenerAdmin() throws SQLException {
        Statement query = cnx.createStatement();
        ResultSet resultado = query.executeQuery("select * from admin");

        Admin leido = new Admin();
        leido.setAvatarUsuario(resultado.getString("avatar"));
        leido.setNombre(resultado.getString("nombre"));
        leido.setAvatarUsuario(resultado.getString("apellidos"));
        leido.setNombre(resultado.getString("correo"));
        leido.setAvatarUsuario(resultado.getString("contrasenna"));
        leido.setNombre(resultado.getString("nombreUsuario"));
            //leido.setPuntos(resultado.getInt("puntos"));

        return leido;
    }*/

    /**
     *
     * @param nuevo administrador que se va a guardar
     * @throws SQLException
     */
    public void guardarAdmin(Admin nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        Statement query = cnx.createStatement();

        ResultSet resultado = query.executeQuery("select * from admin");
        if(resultado.getRow() != 0) {
            System.out.println("Admin ya registrado");
        } else {
            String insertar = "insert into admin(idAdmin,avatar,nombre,apellidos,correo,contrasenna,nombreUsuario) values ('";
            insertar += "1";
            insertar += "','";
            insertar += nuevo.getAvatarUsuario();
            insertar += "','";
            insertar += nuevo.getNombre();
            insertar += "','";
            insertar += nuevo.getApellidosUsuario();
            insertar += "','";
            insertar += nuevo.getCorreoUsuario();
            insertar += "','";
            insertar += nuevo.getContrasennaUsuario();
            insertar += "','";
            insertar += nuevo.getNombreUsuarioAdmin();
            insertar += "')";
            System.out.println(insertar);
            insert.execute(insertar);
        }

    }

}
