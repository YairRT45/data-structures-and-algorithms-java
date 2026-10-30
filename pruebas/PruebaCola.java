// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ColaInfoEstatica;

public class PruebaCola {
    public static void main(String[] args) {
        ColaInfoEstatica cola = new ColaInfoEstatica(5);
        cola.poner("A");
        cola.poner("B");
        cola.poner("C");
        cola.poner("D");
        cola.poner("E");
        cola.poner("F");

        cola.mostrar();
        SalidaPorDefecto.consola("\n");

        SalidaPorDefecto.consola("Quitando: " + cola.quitar() + "\n");
        SalidaPorDefecto.consola("Quitando: " + cola.quitar() + "\n");

        cola.poner("G");
        cola.poner("H");

        cola.mostrar();
    }
}
