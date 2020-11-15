package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class UsuarioFinal extends Usuario{
    private String fechaNacimientoUsuario;
    private String paisProcedenciaUsuario;
    private String identificacionUsuario;
    private String nombreUsuario;
    private int otp;
    private ArrayList<ListaReproduccion> listasReproduccion;
    private ArrayList<Cancion> cancionesUsuario;
    //private int edadUsuario;

    public String getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public void setFechaNacimientoUsuario(String fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

    public String getPaisProcedenciaUsuario() {
        return paisProcedenciaUsuario;
    }

    public void setPaisProcedenciaUsuario(String paisProcedenciaUsuario) {
        this.paisProcedenciaUsuario = paisProcedenciaUsuario;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public ArrayList<ListaReproduccion> getListasReproduccion() {
        return listasReproduccion;
    }

    public void setListasReproduccion(ListaReproduccion lista) {
        this.listasReproduccion.add(lista);
    }
    public ArrayList<Cancion> getCancionesUsuario() {
        return cancionesUsuario;
    }

    public void setCancionesUsuario(Cancion cancionUsuario) {
        this.cancionesUsuario.add(cancionUsuario);
    }


    public UsuarioFinal() {}

    public UsuarioFinal(String avatarUsuario, String nombre, String apellidosUsuario,
                        String correoUsuario, String contrasennaUsuario,
                        String fechaNacimientoUsuario, String paisProcedenciaUsuario,
                        String identificacionUsuario, String nombreUsuario, int otp,
                        ArrayList<ListaReproduccion> listasReproduccion, ArrayList<Cancion> cancionesUsuario) {
        super(avatarUsuario, nombre, apellidosUsuario, correoUsuario, contrasennaUsuario);
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
        this.paisProcedenciaUsuario = paisProcedenciaUsuario;
        this.identificacionUsuario = identificacionUsuario;
        this.nombreUsuario = nombreUsuario;
        this.otp = otp;
        this.listasReproduccion = listasReproduccion;
        this.cancionesUsuario = cancionesUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioFinal{" +
                "fechaNacimientoUsuario='" + fechaNacimientoUsuario + '\'' +
                ", paisProcedenciaUsuario='" + paisProcedenciaUsuario + '\'' +
                ", identificacionUsuario='" + identificacionUsuario + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", otp='" + otp + '\'' +
                ", listasReproduccion='" + listasReproduccion + '\'' +
                ", cancionesUsuario='" + cancionesUsuario + '\'' +
                "} " + super.toString();
    }

}
