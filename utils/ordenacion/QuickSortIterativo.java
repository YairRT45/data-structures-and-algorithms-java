package utils.ordenacion;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.PilaInfoDinamica;

public class QuickSortIterativo {
    
    private static void intercambiar(ListaInfoEstatica arreglo, int i, int j){
        int temp = (int)arreglo.obtener(i);
        arreglo.cambiar(i, arreglo.obtener(j));
        arreglo.cambiar(j, temp);
    }

    private static int particion(ListaInfoEstatica arreglo, int inicio, int fin){
        int pivote = (int)arreglo.obtener(fin); 
        int indiceUltimoPequeño = inicio - 1;

        for (int elementoActual = inicio; elementoActual < fin; elementoActual++){
            if ((int)arreglo.obtener(elementoActual) < pivote) {
                indiceUltimoPequeño = indiceUltimoPequeño + 1;
                intercambiar(arreglo, indiceUltimoPequeño, elementoActual);
            }
        }
        intercambiar(arreglo, indiceUltimoPequeño + 1, fin);

        return indiceUltimoPequeño + 1; //Regresamos la posición del pivote
    }

    public static void quickSortIterativo(ListaInfoEstatica arreglo, int inicio, int fin){
        PilaInfoDinamica pila = new PilaInfoDinamica();
        pila.poner(inicio);
        pila.poner(fin);

        while (pila.vacio() == false) {
            fin = (int)pila.quitar();
            inicio = (int)pila.quitar();
            int indicePivote = particion(arreglo, inicio, fin);

            if (indicePivote - 1 > inicio) { //Verifica que haya elementos por ordenar en el subarreglo izquiero del pivote
                pila.poner(inicio);
                pila.poner(indicePivote - 1);
            }

            if (indicePivote + 1 < fin) { //Verifica que haya elementos en el subarreglo derecho del pivote
                pila.poner(indicePivote + 1);
                pila.poner(fin);
            }
        }
    }

    public static void main(String[] args) {
        ListaInfoEstatica arreglo = new ListaInfoEstatica(8);
        arreglo.insertar(5);
        arreglo.insertar(3);
        arreglo.insertar(7);
        arreglo.insertar(6);
        arreglo.insertar(8);
        arreglo.insertar(4);
        arreglo.insertar(9);
        arreglo.insertar(6);

        consola("Arreglo desordenados:\n");
        arreglo.mostrar();
        int n = arreglo.cantidad();
        quickSortIterativo(arreglo, 0, n-1);
        consola("Arreglo ordenado:\n");
        arreglo.mostrar();
    }
}
