package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class CancionCompra extends Cancion{
    private int precioCancion;

    public int getPrecioCancion() {
        return precioCancion;
    }

    public void setPrecioCancion(int precioCancion) {
        this.precioCancion = precioCancion;
    }

    public CancionCompra() {}

    public CancionCompra(String nombreCancion, Artista artistaCancion, Compositor compositorCancion,
                         String fechaLanzamientoCancion, Genero generoCancion,
                         int cancionSimple, Album albumCancion, String recurso, int precioCancion) {
        super(nombreCancion, artistaCancion, compositorCancion, fechaLanzamientoCancion,
                generoCancion, cancionSimple, albumCancion, recurso);
        this.precioCancion = precioCancion;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CancionCompra{" +
                "precioCancion=" + precioCancion +
                "} ";
    }
}
