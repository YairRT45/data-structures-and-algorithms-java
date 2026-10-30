// package pruebas;

import registros.indices.GestorIndices;

public class PruebaIndices {
    static boolean app;

    public static void main(String[] args) {
        app = true;
        GestorIndices gestor = new GestorIndices(1000);

        while (app) {
            gestor.menuPrincipal();
        }
    }
}
