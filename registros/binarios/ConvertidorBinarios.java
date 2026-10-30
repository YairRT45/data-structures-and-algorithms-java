package registros.binarios;

public class ConvertidorBinarios {
    public String aBinario(int numero){
        if (numero == 0) {
            return "";
        } else if (numero % 2 == 0) { //Si es par agregamos el 0 y llamamos mandar el metodo con la mitad
           return aBinario(numero / 2) + "0";
        } else { //Si es impar agregamos el uno y mandamos llamar el metodo con la mitad despues de restarle uno
            return aBinario((numero - 1) / 2) + "1";
        }
    }
}
