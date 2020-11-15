package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Usuario {
    private String avatarUsuario;
    private String nombre;
    private String apellidosUsuario;
    private String correoUsuario;
    private String contrasennaUsuario;

    public String getAvatarUsuario() {
        return avatarUsuario;
    }

    public void setAvatarUsuario(String avatarUsuario) {
        this.avatarUsuario = avatarUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasennaUsuario() {
        return contrasennaUsuario;
    }

    public void setContrasennaUsuario(String contrasennaUsuario) {
        this.contrasennaUsuario = contrasennaUsuario;
    }

    public Usuario() {}

    public Usuario(String avatarUsuario, String nombre, String apellidosUsuario,
                   String correoUsuario, String contrasennaUsuario) {
        this.avatarUsuario = avatarUsuario;
        this.nombre = nombre;
        this.apellidosUsuario = apellidosUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasennaUsuario = contrasennaUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "avatarUsuario='" + avatarUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidosUsuario='" + apellidosUsuario + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", contrasennaUsuario='" + contrasennaUsuario + '\'' +
                '}';
    }
}
