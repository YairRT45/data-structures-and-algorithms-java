package utils;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;

public class Menu {
    String[] opciones;

    public Menu(String[] opciones) {
        this.opciones = opciones;
    }

    public void mostrarMenu(){
        //System.out.println("Ingresa la opción deseada");
        for(int iterador = 0; iterador <= opciones.length-1; iterador++){
            SalidaPorDefecto.consola(((iterador + 1) + ". " + opciones[iterador]) + "\n");
        }
    }
    
    public int seleccionarDesdeMenu(){
        SalidaPorDefecto.consola("Selecciona una opcion: ");
        double seleccion = EntradaPorDefecto.consolaDouble();
        SalidaPorDefecto.consola("\n");
        return (int)seleccion;
    }
}
