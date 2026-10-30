// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import registros.hexadecimal.ConvertidorHexadecimal;

public class PruebaHexadecimal {
    public static void main(String[] args) {
        ConvertidorHexadecimal convertidor = new ConvertidorHexadecimal();
        int numero = 65029;
        consola("El número " + numero + " en notación hexadecimal es " + convertidor.aHexadecimal(numero));
        
    }
}
