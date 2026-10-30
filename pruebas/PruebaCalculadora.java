// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import registros.calculadora.Calculadora;

public class PruebaCalculadora {
    public static void main(String[] args) {
        String expresion = "(a-2.0)*f/2^(c-8)";
        consola(expresion + "\n");
        Calculadora.encontrarVariables(expresion);
        Calculadora.pedirValoresVariables();
        // Calculadora.mostrarListas();
        consola("El resultado es: " + Calculadora.evaluar(expresion));
        
    }
}
