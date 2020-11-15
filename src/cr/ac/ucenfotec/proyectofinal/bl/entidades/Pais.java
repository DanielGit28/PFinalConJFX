package cr.ac.ucenfotec.proyectofinal.bl.entidades;
/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class Pais {

    private String codigoPais;
    private String nombrePais;

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    public Pais() {
    }

    public Pais(String codigoPais, String nombrePais) {
        this.codigoPais = codigoPais;
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "codigo país='" + codigoPais + '\'' +
                ", nombre país='" + nombrePais + '\'' +
                '}';
    }
}
