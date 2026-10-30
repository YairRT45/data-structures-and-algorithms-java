// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.PilaInfoEstatica;

public class PruebaPila {
    public static void main(String[] args) {
        PilaInfoEstatica pila = new PilaInfoEstatica(5);

        pila.poner("Juan");
        pila.poner("Rosa");
        pila.poner("Jelipe");
        pila.poner("María");
        pila.poner("Josefa");
        pila.poner("Lupe");

        pila.mostrar();

        SalidaPorDefecto.consola("\n");

        SalidaPorDefecto.consola("Quitando: " + pila.quitar() + "\n");
        SalidaPorDefecto.consola("\n");
        pila.mostrar();

        pila.poner("Nuevo");
        SalidaPorDefecto.consola("\n");
        pila.mostrar();

    }
}
