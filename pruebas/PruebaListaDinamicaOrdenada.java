// package pruebas;

import estructuraslineales.ListaInfoDinamicaOrdenada;
// import estructuraslineales.ListaInfoEstatica;
import estructurasnolineales.MatrizInfo2;
import utils.comunes.TipoOrdenamiento;
import static entradasalida.SalidaPorDefecto.consola;

public class PruebaListaDinamicaOrdenada {

    public static void main(String[] args) {
        // ListaInfoEstatica lista2 = new ListaInfoEstatica(5);
        // lista2.insertar("Y");
        // lista2.insertar("B");
        // lista2.insertar("S");
        MatrizInfo2 matriz = new MatrizInfo2(2, 2);
        matriz.cambiar(0, 0, "F");
        matriz.cambiar(0, 1, "K");
        matriz.cambiar(1, 0, "B");
        matriz.cambiar(1, 1, "W");

        ListaInfoDinamicaOrdenada lista = new ListaInfoDinamicaOrdenada(TipoOrdenamiento.INC);

        lista.insertar("C");
        lista.insertar("Y");
        lista.insertar("K");
        lista.insertar("B");

        lista.mostrar();
        consola("\n Borrando elemento 'K'\n");
        lista.borrar("K");
        // lista.invertir();
        // lista2.mostrar();
        // matriz.mostrarXRenglon();
        // lista.agregarMatriz2D(matriz);
        // lista.agregarLista(lista2);
        // lista.borrarLista(lista2);
        // lista.borrarMatriz2D(matriz);
        // consola("\n");
        
        
        // consola("\nInsertando 'A' al inicio\n");
        // lista.insertarInicio("A");

        // consola("\nInsertando 'G' al final\n");
        // lista.insertarFinal("G");
        
        lista.mostrar();
        consola("\n");
        // lista.mostrarOI();

        // Object dato = lista.buscar(2);

        // consola("\nEl dato en el indice 2 es: " + dato);
    }
}
