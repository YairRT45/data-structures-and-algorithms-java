package registros.cripto;

import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.PilaInfoEstatica;

public class Encriptador {
    public static String encriptarMensaje(String mensaje, ListaInfoEstatica posiciones, ListaInfoEstatica tamanios){
        PilaInfoEstatica pila = new PilaInfoEstatica(mensaje.length());
        String mensajeEncriptado = "";
        for (int posToken = 0; posToken < mensaje.length(); posToken++) { //Tkens del mensaje

            for (int indicePosiciones = 0; indicePosiciones < posiciones.cantidad(); indicePosiciones++) { //Posiciones agregadas
                if (posToken == (int)posiciones.obtener(indicePosiciones)) {
                    mensajeEncriptado = mensajeEncriptado + "(";

                    for (int tamanio = 0; tamanio < (int)tamanios.obtener(indicePosiciones); tamanio++) { //Tamaño de lo encriptado
                        pila.poner(mensaje.charAt(posToken + tamanio));
                    }
                    for (int tamanio = 0; tamanio < (int)tamanios.obtener(indicePosiciones); tamanio++) { //Tamaño de lo encriptado
                        mensajeEncriptado = mensajeEncriptado + pila.quitar();
                    }
                    mensajeEncriptado = mensajeEncriptado + ")";
                    posToken = posToken + (int)tamanios.obtener(indicePosiciones);
                    posiciones.borrar(indicePosiciones);
                    tamanios.borrar(indicePosiciones);
                    indicePosiciones = 0;
                }
            }
            mensajeEncriptado = mensajeEncriptado + mensaje.charAt(posToken);
        }
        return mensajeEncriptado;
    }

    public static String desencriptarMensaje(String mensaje){
        PilaInfoEstatica pila = new PilaInfoEstatica(mensaje.length());
        String mensajeDesencriptado = "";

        for (int posToken = 0; posToken < mensaje.length(); posToken++) {
            char token = mensaje.charAt(posToken);
            if (token == '(') {
                pila.poner(token + "");
                int posEncriptado = posToken + 1;
                char tokenEncriptado = mensaje.charAt(posEncriptado);
                while (tokenEncriptado != ')') {
                    tokenEncriptado = mensaje.charAt(posEncriptado);
                    pila.poner(tokenEncriptado);
                    posEncriptado = posEncriptado + 1; 
                }
                pila.quitar();
                posToken = posEncriptado - 2;
            } else if (token == ')') {
                if (pila.vacio() == false) {
                    while (pila.verFinal().toString().charAt(0) != '(') {
                        mensajeDesencriptado = mensajeDesencriptado + pila.quitar();
                    }
                    pila.quitar(); //Quitar el "(" de la pila
                }
            } else {
                mensajeDesencriptado = mensajeDesencriptado + token;
            }
        }
        return mensajeDesencriptado;
    }

}
