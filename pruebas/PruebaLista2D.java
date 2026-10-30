// package pruebas;

import estructurasnolineales.MatrizInfo2;
// import estructurasnolineales.MatrizInfo3;
// import utils.comunes.TipoColumna;
// import utils.comunes.TipoRenglon;

import static entradasalida.SalidaPorDefecto.consola;

// import estructuraslineales.ListaInfoEstatica;

public class PruebaLista2D {
    public static void main(String[] niSeUsan) {
        MatrizInfo2 matriz = new MatrizInfo2(2, 2, "A");
        matriz.cambiar(0, 1, "B");
        matriz.cambiar(0, 1, "C");
        matriz.mostrarXRenglon();
        // MatrizInfo2 matriz2 = new MatrizInfo2(2, 2, "B");
        // MatrizInfo2 matriz3 = new MatrizInfo2(2, 2, "C");
        // MatrizInfo2 matriz4 = new MatrizInfo2(2, 2, "D");
        // ListaInfoEstatica lista = new ListaInfoEstatica(3);
        // lista.insertar(matriz2);
        // lista.insertar(matriz3);
        // lista.insertar(matriz4);
        // MatrizInfo3 m3d = matriz.aMatriz3(lista);
        // m3d.mostrarXColumna();
        // matriz.mostrarXRenglon();
        consola("\n");
        // matriz.eliminarColumna(1);
        //matriz.vectorRenglon(3, "Z");
        // matriz.mostrarXRenglon();
    }
}
