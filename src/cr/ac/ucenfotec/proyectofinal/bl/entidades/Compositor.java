package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Compositor {
    private String nombre;
    private String apellidos;
    private String paisNacimientoCompositor;
    private String fechaNacimientoCompositor;
    private int edadCompositor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPaisNacimientoCompositor() {
        return paisNacimientoCompositor;
    }

    public void setPaisNacimientoCompositor(String paisNacimientoCompositor) {
        this.paisNacimientoCompositor = paisNacimientoCompositor;
    }

    public String getFechaNacimientoCompositor() {
        return fechaNacimientoCompositor;
    }

    public void setFechaNacimientoCompositor(String fechaNacimientoCompositor) {
        this.fechaNacimientoCompositor = fechaNacimientoCompositor;
    }

    public int  getEdadCompositor() {
        return edadCompositor;
    }

    public void setEdadCompositor(int   edadCompositor) {
        this.edadCompositor = edadCompositor;
    }

    public Compositor() {}

    public Compositor(String nombre, String apellidos, String paisNacimientoCompositor,
                      String fechaNacimientoCompositor, int edadCompositor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.paisNacimientoCompositor = paisNacimientoCompositor;
        this.fechaNacimientoCompositor = fechaNacimientoCompositor;
        this.edadCompositor = edadCompositor;
    }

    @Override
    public String toString() {
        return "Compositores{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", paisNacimientoCompositor='" + paisNacimientoCompositor + '\'' +
                ", fechaNacimientoCompositor='" + fechaNacimientoCompositor + '\'' +
                ", edadCompositor='" + edadCompositor + '\'' +
                '}';
    }
}
