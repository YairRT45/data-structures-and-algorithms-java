// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;

public class PruebaListaInfoEstatica {
    public static void main(String[] args) {
        ListaInfoEstatica lista1 = new ListaInfoEstatica(10);
        ListaInfoEstatica lista2 = new ListaInfoEstatica(5);

        lista1.insertar("A");
        lista1.insertar("B");
        lista1.insertar("C");
        lista1.insertar("D");
        lista1.insertar("E");
        lista1.insertar("F");
        lista1.mostrar();
        lista1.rellenar("X", 3);
        
        //Object elemento = lista1.obtener(2);
        SalidaPorDefecto.consola("\n");

        lista2.insertar("Z");
        lista2.insertar("Y");
        lista2.insertar("X");
        lista2.insertar("W");
        lista2.insertar("V");
        lista2.insertar("F");
        // ListaInfoEstatica lista3 = lista2.encontrarValores("A");
        // lista2.mostrar();
        SalidaPorDefecto.consola("Se rellenaron 3 espacios con 'X' \n");
        //lista1.agregarLista(lista2);
        // if (lista1.cambiar(1, "Z")) {
        //     SalidaPorDefecto.consola("Se cambio 'B' por 'Z' \n");
        // }
        lista1.mostrar();

        
        
        
    }
}
