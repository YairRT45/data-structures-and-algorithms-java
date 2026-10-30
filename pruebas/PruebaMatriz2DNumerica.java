// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.MatrizInfo2Numerica;

public class PruebaMatriz2DNumerica {
    public static void main(String[] args) {
        MatrizInfo2Numerica matriz1 = new MatrizInfo2Numerica(2, 2);

        matriz1.cambiar(0, 0, 5);
        matriz1.cambiar(0, 1, 3);
        matriz1.cambiar(1, 0, 8);
        matriz1.cambiar(1, 1, 1);
        matriz1.mostrarXRenglon();

        SalidaPorDefecto.consola("\n");
        matriz1.invertirVertical();
        matriz1.mostrarXRenglon();

       


    }
    

}
