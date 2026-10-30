// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.ListaInfoDinamicaClave;
import estructuraslineales.ListaInfoEstatica;
import estructurasnolineales.MatrizInfo2;

public class PruebaListaDinamicaClave {
    public static void main(String[] args) {
        ListaInfoDinamicaClave lista = new ListaInfoDinamicaClave();
        // ListaInfoDinamicaClave lista2 = new ListaInfoDinamicaClave();
        // ListaInfoEstatica claves= new ListaInfoEstatica(4);
        // ListaInfoEstatica datos = new ListaInfoEstatica(4);
        // ListaInfoDinamica claves = new ListaInfoDinamica();
        // ListaInfoDinamica datos = new ListaInfoDinamica();
        MatrizInfo2 matriz = new MatrizInfo2(2, 2);
        
        lista.insertar(1, "A");
        lista.insertar(2, "B");
        lista.insertar(3, "C");
        lista.mostrar();

        matriz.cambiar(0, 0, 7);
        matriz.cambiar(1, 0, 8);
        matriz.cambiar(0, 1, "R");
        matriz.cambiar(1, 1, "S");
        matriz.mostrarXRenglon();

        lista.agregarMatriz2(matriz);
        lista.mostrar();
        // claves.insertar(5);
        // claves.insertar(6);
        // claves.mostrar();
        // consola("\n");
        // datos.insertar("K");
        // datos.insertar("L");
        // datos.mostrar();
        // consola("\n");
        // lista.agregarListasDinamicas(claves, datos);
        // lista.mostrar();
        // consola("\n");
        // consola("Tamaño de la lista: " + lista.cantidad());
        // lista.borrarContenido("C");
        // lista2.insertar(8, "Z");
        // lista2.insertar(9, "X");
        // lista2.insertar(0, "Y");
        // // lista.vaciar();
        // lista2.mostrar();
        // consola("\n");
        // lista.agregarLista(lista2);
        // lista.mostrar();
        // MatrizInfo2 matriz = lista.aMatriz2();
        // matriz.mostrarXRenglon();
        // ListaInfoDinamica listas = lista.aListasDinamicas();
        // ListaInfoDinamica listaClaves = (ListaInfoDinamica)listas.obtener(0);
        // listaClaves.mostrar();
        // consola("\n");
        // ListaInfoDinamica listaDatos = (ListaInfoDinamica)listas.obtener(1);
        // listaDatos.mostrar();
        // lista.cambiarValor("B", "H");
        // consola("Objeto con valor C: " + lista.encontrarContenido("C"));
        // if (lista.vacia()) {
        //     consola("La lista esta vacia\n");
        // } else {
        //     consola("La lista contiene elementos\n");
        // }

        // lista.mostrar();
        // lista.mostrarClaves();
        // lista.mostrarValores();
    
    }
}
