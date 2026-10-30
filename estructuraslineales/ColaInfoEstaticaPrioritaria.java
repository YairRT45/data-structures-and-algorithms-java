package estructuraslineales;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.auxiliares.ObjetoPriorizable;

public class ColaInfoEstaticaPrioritaria { //Clase que sirve para acomodar valores dependiendo de la prioridad asignada
    protected ObjetoPriorizable[] datos;
    protected int posInicial;
    protected int posFinal;
    protected int capacidad;

    public ColaInfoEstaticaPrioritaria(int tamanio) { 
        capacidad = tamanio;
        datos = new ObjetoPriorizable[capacidad];
        posFinal = -1;
        posInicial = -1;
    }

    public boolean vacia() { //Verifica si hay valores en la cola
        if (posInicial == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean llena() { //Verifica si hay espacio en la cola
        if (((posFinal + 1) % capacidad) == posInicial) {
            return true;
        } else {
            return false;
        }
        // return ((posFinal + 1) % capacidad) == posInicial;
    }

    public boolean poner(Object valor, int prioridad) { // Inserta el valor dependiendo si esta vacio, lleno o hay igualdades
        if (llena() == false) {
            ObjetoPriorizable nuevoValor = new ObjetoPriorizable(valor, prioridad);
            if (vacia() == true) {
                posInicial = 0;
                posFinal = 0;
            } else {
                posFinal = (posFinal + 1) % capacidad;
            }
            datos[posFinal] = nuevoValor;
            ordenarPrioridad();
            return true;
        } else {
            return false;
        }
    }

    public ObjetoPriorizable quitarMayorPrioridad() { //Elimina el valor con mas prioridad en la cola
        if (vacia() == false) {
            ObjetoPriorizable elementoEliminado = datos[posInicial];
            datos[posInicial] = null; 
            if (posInicial == posFinal) { 
                posInicial = -1;
                posFinal = -1;
            } else {
                posInicial = (posInicial + 1) % capacidad;
            }
            return elementoEliminado;
        } else {
            return null;
        }
    }

    public ObjetoPriorizable quitarPorPrioridad(int prioridad) { //Elimina el valor con la prioridad que se asigno.
        if (vacia() == false) {
            int indiceActual = posInicial;
            while (indiceActual != posFinal) {
                if (datos[indiceActual].getPrioridad() == prioridad) {
                    ObjetoPriorizable valorEliminar = datos[indiceActual];
                    while (indiceActual != posFinal) {
                        datos[indiceActual] = datos[(indiceActual + 1) % capacidad];
                        indiceActual = (indiceActual + 1) % capacidad;
                    }
                    datos[posFinal] = null; 
                    if (posFinal == posInicial) {
                        posInicial = -1;
                        posFinal = -1;
                    } else {
                        posFinal = (posFinal - 1 + capacidad) % capacidad; 
                    }
                    return valorEliminar;
                }
                indiceActual = (indiceActual + 1) % capacidad;
            }
        }
        return null;
    }

    private void ordenarPrioridad() { // Ordena si es que encuentra un valor con prioridad menor hasta que no haya ninguno y todos cumplan correctamente la prioridad
        if (vacia() == false && posInicial != posFinal) {
            boolean huboIntercambio;
            do {
                huboIntercambio = false;
                int indiceInicial = posInicial;
                while ((indiceInicial % capacidad) != posFinal) {
                    int indActual = (indiceInicial + 1) % capacidad;
                    if (datos[indiceInicial].getPrioridad() < datos[indActual].getPrioridad()) {
                        ObjetoPriorizable temp = datos[indiceInicial];
                        datos[indiceInicial] = datos[indActual];
                        datos[indActual] = temp;
                        huboIntercambio = true;
                    }
                    indiceInicial = indActual;
                }
            } while (huboIntercambio); 
        }
    }

    public void mostrar() { // Muestra todo lo que contiene la cola
        if (vacia() == false) {
            int indiceActual = posInicial;
            while (indiceActual != posFinal) { //Puede ser mejor sumar 1 a posFinal aqui y quitar el print fuera del while
                SalidaPorDefecto.consola(datos[indiceActual].getValor() + " Prioridad: " + datos[indiceActual].getPrioridad()+"\n");
                indiceActual = (indiceActual + 1) % capacidad;
            }
            SalidaPorDefecto.consola(datos[posFinal].getValor() + " Prioridad: " + datos[posFinal].getPrioridad()+"\n");
        } else {
            SalidaPorDefecto.consola("Esta vacio.");
        }
    }
}
