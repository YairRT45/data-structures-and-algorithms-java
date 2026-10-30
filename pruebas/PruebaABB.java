

import static entradasalida.SalidaPorDefecto.consola;

import estructurasnolineales.ArbolBinarioBusqueda;

public class PruebaABB {
    public static void main(String[] args) {
        ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();

        abb.insertar(7);
        abb.insertar(5);
        abb.insertar(9);
        abb.insertar(3);
        abb.insertar(6);
        abb.insertar(8);
        abb.insertar(10);
        abb.insertar(11);
        abb.insertar(9);
        abb.insertar(15);
        abb.insertar(25);
        abb.insertar(12);
        abb.insertar(13);
        abb.insertar(23);
        abb.insertar(7);
        abb.insertar(5);
        abb.insertar(6);
        abb.insertar(2);
        abb.insertar(8);
        // consola("Postorden recursivo: ");
        // abb.innorden();
        // consola("\nPostorden no recursivo: ");
        // abb.postordenNoRecursivo();
        // abb.innorden();
        // consola("\nEliminando 7 (raíz con dos hijos)\n");
        // abb.preorden();
        // consola("\n");
        // abb.postorden();
        // abb.eliminar(7);
        // abb.innorden();
    }
}
