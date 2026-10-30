// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import estructurasnolineales.ArbolExpresionesA;

public class PruebaAEA {
    public static void main(String[] args) {
        ArbolExpresionesA arbol = new ArbolExpresionesA();

        arbol.nuevoArbol();
        arbol.obtenerListas();
        arbol.mostrarArboles();
        // arbol.mostrarListas();
        // arbol.nuevoArbolPostfija("ab+nc*4/8^3*-");

        // arbol.innorden();

        // consola("\n");
    }
}
