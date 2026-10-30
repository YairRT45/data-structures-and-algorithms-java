// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.MatrizInfo3;

public class PruebaLista3D {
    public static void main(String[] args) {
        MatrizInfo3 matriz = new MatrizInfo3(5, 4, 3);

        matriz.rellenar(0);
        matriz.mostrarXColumna();

        matriz.cambiar(0, 0, 0, 1);
        matriz.cambiar(1, 0, 1, 1);
        matriz.cambiar(2, 0, 2, 1);
        matriz.cambiar(3, 0, 1, 1);
        matriz.cambiar(4, 0, 0, 1);

        matriz.cambiar(0, 1, 0, 2);
        matriz.cambiar(1, 1, 1, 2);
        matriz.cambiar(2, 1, 2, 2);
        matriz.cambiar(3, 1, 1, 2);
        matriz.cambiar(4, 1, 0, 2);

        SalidaPorDefecto.consola("-----------------------------------\n");
        matriz.mostrarXColumna();
    }
}