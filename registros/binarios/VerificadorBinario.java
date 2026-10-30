package registros.binarios;

public class VerificadorBinario {
    
    public boolean esBinario(long numero){
        if (numero == 0 || numero == 1) {
            return true;
        } else if (numero % 10 > 1) {
            return false;
        } else {
            return esBinario(numero / 10);
        }
    }
}
