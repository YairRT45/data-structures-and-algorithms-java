// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import utils.matematicas.ExpresionesAritmeticas;

public class PruebaExpresiones {
    public static void main(String[] args) {
        ExpresionesAritmeticas.evaluarPosfija("a2.0-f*2c8-^/");
        consola("Evaluando abcd-*zy^/+: " + ExpresionesAritmeticas.evaluarPosfija("2352-*12^/+") + "\n");
        // consola("Evaluando prefija +a/*b-cd^zy: " + ExpresionesAritmeticas.evaluarPrefija("+2/*3-52^12") + "\n");
        // consola(ExpresionesAritmeticas.infijaPosfija("(a-2.0)*f/2^(c-8)"));
        
    }
    
}
