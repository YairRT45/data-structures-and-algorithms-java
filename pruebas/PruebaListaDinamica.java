// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;
import estructuraslineales.ListaInfoDinamica;
// import estructuraslineales.ListaInfoEstatica;
// import estructurasnolineales.MatrizInfo2;
// import utils.comunes.TipoTabla;

public class PruebaListaDinamica {
    public static void main(String[] args) {
        ListaInfoDinamica lista = new ListaInfoDinamica();
        // 
        // ListaInfoDinamica lista2; //= new ListaInfoDinamica();
        // ListaInfoEstatica listaEstatica;
        // MatrizInfo2 tabla;
        // tabla.cambiar(0, 1, "W");
        // tabla.cambiar(1, 0, "X");
        // tabla.cambiar(1, 1, "Y");

        lista.insertar("B");
        lista.insertar("C");
        lista.insertar("H");
        lista.insertar("R");
        lista.insertar("Z");
        // lista.insertar("H");
        consola("Lista Dinamica: ");
        consola("\n");
        lista.mostrar();
        consola("\n");
        consola("Elemento eliminado del indice 2: " + lista.borrar(2) + "\n");
        consola("Lista: \n");
        lista.mostrar();

        // consola("Matriz generada de 2 por 3:\n");
        // tabla.mostrarXRenglon();
        // consola("\nLista de 'C' encontradas:  \n");
        // listaEstatica = lista.encontrarValores("C");
        // listaEstatica.mostrar();
        // lista.redimensionar(3);
        // lista.mostrar();
        // lista2 = (ListaInfoDinamica) lista.clonar();
        // consola("\nSegunda lista: \n");
        // lista2.mostrar();
        // consola("\n");
        // if (lista.esIgual(lista2)) {
        //     consola("Son iguales");
        // } else {
        //     consola("Son diferentes");
        }
        // consola("\nObteniendo elemento del indice 3: " + lista.obtener(3));
        // lista.cambiar(2, "J");
        // consola("\nCambiando indice 2 por 'J': \n");
        // lista.mostrar();
        // consola("\nCambiando 2 'C' por 'T'\n");

        // lista.cambiar("C", "T", 2);
        // lista.mostrar();
        // consola("Lista invertida: \n");
        // lista.invertir();
        // lista.mostrar();

        // consola("Cantidad de 'C': " + lista.contar("C"));
        // lista.rellenar("Y", 4);
        // consola("Lista con 4 'Y':\n");
        // lista.mostrar();
        
        // lista.vaciar();
        // consola("Lista vacia: \n");
        // lista.mostrar();
        // consola("Matriz: \n");
        // tabla.mostrarXRenglon();

        // lista.agregarMatriz2D(tabla, TipoTabla.COLUMNA);
        // consola("Lista con matriz agregada por columna: \n");
        // lista.mostrar();
        // lista2 = (ListaInfoDinamica)lista.clonar();

        // consola("Lista clonada: ");
        // lista2.mostrar();
        // consola("\n");
        // consola("Lista Dinamica mas 'P': ");
        // lista.insertar("P");
        // lista.mostrar();
        // consola("\n");
        // consola("Lista clonada: ");
        // lista2.mostrar();
        // MatrizInfo2 matriz = lista.aMatriz2D(3, 2);
        // matriz.mostrarXRenglon();
        // lista2.insertar("J");
        // lista2.insertar("K");
        // lista2.insertar("L");
        // consola("Datos a agregar: \n");
        // lista2.mostrar();
        // consola("\n");
        // lista.agregarLista(lista2);
        // consola("Lista final: \n");
        // lista.mostrar();
        // ListaInfoEstatica listaNueva = lista.aListaEstatica(listaEstatica);
        // consola("Lista nueva: \n");
        // listaNueva.mostrar();
        // lista.borrar();
        // lista.mostrar();
        // ListaInfoEstatica listaEstatica = lista.aListaEstatica();
        // listaEstatica.mostrar();
        // ListaInfoDinamica lista2 = lista.encontrarLista("B");
        // lista2.mostrar();

        // lista.mostrarOI();
        // consola("\n");

        // // lista.insertarInicio("A");
        // // lista.mostrar();
        // // consola("\n");
        // // consola("Eliminando al principio " + lista.borrarInicio() + "\n");
        // // lista.mostrar();
        // consola("Buscando Z: " + lista.encontrar("Z") + "\n");
        // consola("Buscando H: " + lista.encontrar("H") + "\n");
        
        // consola("Eliminando al final: " + lista.borrar() + "\n");
        // lista.mostrar();
        // consola("\n");
    
}
