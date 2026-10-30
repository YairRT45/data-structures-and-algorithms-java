// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.SalidaPorDefecto;
import registros.cripto.Encriptador;

public class PruebaEncriptador {
    public static void main(String[] args) {
        // ListaInfoEstatica posiciones = new ListaInfoEstatica(2);
        // posiciones.insertar(28);
        // posiciones.insertar(5);
        // ListaInfoEstatica tamanios = new ListaInfoEstatica(2);
        // tamanios.insertar(5);
        // tamanios.insertar(3);
        // String mensaje = "La contraseña del WiFi es HolaMundo";
        
        // consola("Mensaje: " + mensaje + "\n");
        // consola("Posiciones: ");
        // for (int i = 0; i < posiciones.cantidad(); i++) {
        //     consola((int)posiciones.obtener(i) + ", ");
        // }
        // consola("\n");
        // consola("Tamaños: ");
        // for (int i = 0; i < posiciones.cantidad(); i++) {
        //     consola((int)tamanios.obtener(i) + ", ");
        // }
        // consola("\n");
        // SalidaPorDefecto.consola("Mensaje encriptado: " + Encriptador.encriptarMensaje(mensaje, posiciones, tamanios));
        String mensajeEncriptado = "Hola soy Y(ria) y qu(orei) una ca(maug)a";
        consola("Mensaje encriptado: " + mensajeEncriptado + "\n");
        SalidaPorDefecto.consola("Mensaje desencriptado: " + Encriptador.desencriptarMensaje(mensajeEncriptado));
    }
}
