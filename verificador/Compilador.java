package verificador;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.EntradaPorDefecto;

public class Compilador {
    public static void main(String[] args) {
        consola("Selecciona cualquier opción: \n");
        consola("1. Analizar archivo. \n");
        consola("2. Analizar expresión. \n");
        int seleccion = EntradaPorDefecto.consolaInt();
        

        switch (seleccion) {
            case 1:
                consola("Ingrese la ruta del archivo: \n");
                String ruta = EntradaPorDefecto.consolaCadenas();
                VerificadorDeErrores verificador = new VerificadorDeErrores(ruta);
                verificador.verificarBalance();
                verificador.verificarComentarios();
                verificador.verificarVariables();
                verificador.mostrarErrores();
                verificador.mostrarVariables();
                break;
            case 2:
                consola("Ingrese la expresión a analizar: \n");
                String cadena = EntradaPorDefecto.consolaCadenas();
                VerificadorDeErrores verificadorCadena = new VerificadorDeErrores(cadena, 5);
                verificadorCadena.verificarBalance();
                verificadorCadena.mostrarErrores();
                break;
            default:
                break;
        }
    }
}
