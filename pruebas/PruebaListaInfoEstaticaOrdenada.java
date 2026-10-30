// package pruebas;

import entradasalida.SalidaPorDefecto;
// import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.ListaInfoEstaticaOrdenada;
import utils.comunes.TipoOrdenamiento;

public class PruebaListaInfoEstaticaOrdenada {
    public static void main(String[] args) {
        TipoOrdenamiento orden = TipoOrdenamiento.INC;
        ListaInfoEstaticaOrdenada lista1 = new ListaInfoEstaticaOrdenada(10, orden);
        ListaInfoEstaticaOrdenada lista2 = new ListaInfoEstaticaOrdenada(4, orden);
        ListaInfoEstaticaOrdenada lista3 = new ListaInfoEstaticaOrdenada(4, orden);

        lista1.insertar("F");
        lista1.insertar("A");
        lista1.insertar("H");
        lista1.insertar("B");
        lista1.insertar("C");
        lista1.mostrar();
        //SalidaPorDefecto.consola("Esta en la posicion: " + lista1.encontrar("Z") + "\n");
        //lista1.cambiar(1, "Z");
        SalidaPorDefecto.consola("\n");
        
        lista2.insertar("A");
        lista2.insertar("B");
        lista2.insertar("C");
        lista2.insertar("D");
        lista2.mostrar(); 
        SalidaPorDefecto.consola("\n");
        lista3.insertar("Z");
        lista3.insertar("Y");
        lista3.insertar("X");
        lista3.insertar("W");
        lista3.mostrar(); 
        SalidaPorDefecto.consola("\n");
        lista1.cambiarLista(lista2, lista3);
        lista1.mostrar();
        
        // lista2.insertar("H");
        // lista2.mostrar();
        // SalidaPorDefecto.consola("\n");
        // lista1.agregarLista(lista2);
        // lista1.mostrar();
    }
}
