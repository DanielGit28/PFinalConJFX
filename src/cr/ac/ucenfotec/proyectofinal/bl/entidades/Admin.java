package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Admin extends Usuario{
    private String nombreUsuarioAdmin;

    public String getNombreUsuarioAdmin() {
        return nombreUsuarioAdmin;
    }

    public void setNombreUsuarioAdmin(String nombreUsuarioAdmin) {
        this.nombreUsuarioAdmin = nombreUsuarioAdmin;
    }
    public Admin() {}

    public Admin(String id, String avatarUsuario, String nombre, String apellidosUsuario,
                 String correoUsuario, String contrasennaUsuario, String nombreUsuarioAdmin) {
        super(id, avatarUsuario, nombre, apellidosUsuario, correoUsuario, contrasennaUsuario);
        this.nombreUsuarioAdmin = nombreUsuarioAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nombreUsuarioAdmin='" + nombreUsuarioAdmin + '\'' +
                ", id='" + id + '\'' +
                ", avatarUsuario='" + avatarUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidosUsuario='" + apellidosUsuario + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", contrasennaUsuario='" + contrasennaUsuario + '\'' +
                '}';
    }
}
