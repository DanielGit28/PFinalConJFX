package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Daniel Zúñiga Rojas
 * @version 1.0
 */
public class ListaPaises {
    private ArrayList<Pais> paises = new ArrayList<>();
    public String[] locales = Locale.getISOCountries();

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ListaPaises paises = new ListaPaises();
        //paises.run();
        String[] locales = Locale.getISOCountries();
        System.out.println("Ingrese el código de país que desea: ");
        String codPais = entrada.next();
        for (String countryCode : locales) {

            Locale paises2 = new Locale("", countryCode);
            if(codPais.equals(countryCode)) {
                System.out.println("Código del país = " + paises2.getCountry()
                        + ", nombre del país = " + paises2.getDisplayCountry());
                break;
            }
        }
    }

    public void getPaises() {
        int contador = 1;
        for (String countryCode : locales) {

            Locale paises = new Locale("", countryCode);

            System.out.println("Código del país = " + paises.getCountry()
                    + ", nombre del país = " + paises.getDisplayCountry() + ", país número = " + contador);
            contador += 1;
        }
        System.out.println(contador);
        System.out.println("Done");
    }
}
