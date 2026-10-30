// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import registros.binarios.ConvertidorBinarios;
import registros.binarios.VerificadorBinario;

public class PruebaBinarios {
    

    public static void main(String[] args) {
        ConvertidorBinarios convertidor = new ConvertidorBinarios();
        int numero = 151;
        consola("La conversión a binario de " + numero + " es: " + convertidor.aBinario(numero));
        
        // VerificadorBinario verificadorBinario = new VerificadorBinario();
        // long numero = 100011;
        // if (verificadorBinario.esBinario(numero)) {
        //     consola("El número " + numero + " es binario");
        // } else {
        //     consola("El número " + numero + " no es binario");
        // }
    }
}
