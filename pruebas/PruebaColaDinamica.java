// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ColaInfoDinamica;

public class PruebaColaDinamica {
    public static void main(String[] args) {
        ColaInfoDinamica cola = new ColaInfoDinamica();

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
