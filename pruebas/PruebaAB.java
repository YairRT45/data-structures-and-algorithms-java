// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaInfoEstatica;
import estructurasnolineales.ArbolBinario;

public class PruebaAB {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        arbol.nuevoArbol();

        consola("Arbol mostrado por anchura: ");
        arbol.mostrarPorAnchura();
        consola("\nArbol mostrado por anchura con pila: ");
        arbol.anchuraConPila();
        // arbol.postorden();
        // consola("\n");
        // arbol.postordenNoRecursivo();
        // arbol.tipoNodo("C");
        // ListaInfoEstatica conteos = arbol.contarElementosPorNivel();
        // for (int i = 0; i < conteos.cantidad(); i++) {
        //     consola("Elementos en el nivel " + (i+1) + ": " + conteos.obtener(i) + "\n");
        // }
        // if (arbol.tieneHermano("F")) {
        //     consola("E si tiene hermano");
        // } else {
        //     consola("E no tiene hermano");
        // }
        // consola("Altura: "+ arbol.obtenerAltura() + "\n");

        // consola("Nivel donde se ubica 'C': " + arbol.encontrarNivel("C"));
        // arbol.postorden();
        // consola("\n");

        // arbol.innorden();
        // consola("\n");
    }
}
