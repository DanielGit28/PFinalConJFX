package cr.ac.ucenfotec.proyectofinal.bl.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    Connection cnx;

    public AdminDAO(Connection conexion){
        this.cnx = conexion;
    }

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
    }

    public void guardarAdmin(Admin nuevo) throws SQLException{
        Statement insert = cnx.createStatement();
        Statement query = cnx.createStatement();

        ResultSet resultado = query.executeQuery("select * from admin");
        if(resultado != null) {
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
