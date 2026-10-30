import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaEnlazadaIndexada;

public class PruebaListaEnlazadaIndexada {
    public static void main(String[] args) {
        ListaEnlazadaIndexada lista = new ListaEnlazadaIndexada();

        lista.insertar(4, "A");
        lista.insertar(0, "C");
        lista.insertar(9, "Z");
        lista.insertar(2, "H");
        lista.mostrar();
        consola("Cantidad de elementos: " + lista.cantidad());
        consola("\n");
        consola("Elemento en el indice 2: " + lista.obtener(2));
        consola("\n");
        consola("Eliminando elemento del indice 1: " + lista.eliminar(1));
        consola("\n");
        lista.mostrar();
        consola("Cantidad de elementos: " + lista.cantidad());
    }
}
