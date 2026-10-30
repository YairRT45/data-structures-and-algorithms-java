import static entradasalida.SalidaPorDefecto.consola;

import utils.matematicas.Polinomio;

public class PruebaPolinomio {
    public static void main(String[] args) {
        Polinomio polinomio = new Polinomio();
        polinomio.agregarMonomios();
        // polinomio.mostrar();
        int resultado = polinomio.evaluarPolinomio();
        consola("Resultado = " + resultado);
    }
}
