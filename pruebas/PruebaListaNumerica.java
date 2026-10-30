// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstaticaNumerica;

public class PruebaListaNumerica {
    public static void main(String[] args) {
        ListaInfoEstaticaNumerica lista1 = new ListaInfoEstaticaNumerica(10);
        ListaInfoEstaticaNumerica lista2 = new ListaInfoEstaticaNumerica(10);

        lista1.insertar(1);
        lista1.insertar(2);
        lista1.insertar(3);
        // lista1.insertar(6);
        lista1.mostrar();
        SalidaPorDefecto.consola("\n");
        // double escalar = 3;
        // SalidaPorDefecto.consola("" + escalar + "\n");
        // lista1.aplicarPotencia(escalar);
        // SalidaPorDefecto.consola("\n");
        // lista1.mostrar();
        lista2.insertar(2);
        lista2.insertar(3);
        lista2.insertar(2);
        lista2.mostrar();
        SalidaPorDefecto.consola("\n");
        lista1.aplicarPotencia(lista2);
        lista1.mostrar();
    }
}
