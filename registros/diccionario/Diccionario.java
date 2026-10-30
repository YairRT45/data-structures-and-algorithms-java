package registros.diccionario;

import static entradasalida.SalidaPorDefecto.consola;
import static entradasalida.EntradaPorDefecto.consolaCadenas;
import entradasalida.EntradaPorDefecto;
import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.ListaInfoDinamicaClave;
import estructuraslineales.auxiliares.NodoClaveValor;
import utils.Concepto;

public class Diccionario {
    protected ListaInfoDinamicaClave listaConceptos;

    public Diccionario(){
        listaConceptos = new ListaInfoDinamicaClave();
    }

    public boolean agregarConcepto(){
        consola("Ingrese el concepto: ");
        String clave = consolaCadenas();
        consola("Ingrese la definición de " + clave + ": ");
        String definicion = consolaCadenas();
        consola("Ingrese el tipo de concepto: ");
        String tipo = consolaCadenas();
        Concepto concepto = new Concepto(definicion, tipo);
        return listaConceptos.insertar(clave, concepto);      
    }

    public void buscarConcepto(String concepto){
        NodoClaveValor resultado = buscarConcepto(listaConceptos.getApuntadorInicial(), concepto);
        if (resultado == null) {
            consola("El concepto no se encontro\n");
        } else {
            consola(resultado.getClave() + ": " + resultado.getDato() + "\n");
        }
    }

    private NodoClaveValor buscarConcepto(NodoClaveValor nodo, String concepto){
        if (nodo == null) {
            return null;
        } else if (nodo.getClave().equals(concepto)) {
            return nodo;
        } else {
            return buscarConcepto(nodo.getEnlaceDer(), concepto);
        }
    }

    public void mostrarConceptos(Object palabraClave){
        ListaInfoDinamicaClave conceptosEncontrados = buscarPalabraClave(listaConceptos.getApuntadorInicial(), palabraClave);
        mostrarTodos(conceptosEncontrados.getApuntadorInicial());
    }

    private ListaInfoDinamicaClave buscarPalabraClave(NodoClaveValor nodo, Object palabraClave){
        ListaInfoDinamicaClave conceptosEncontrados = new ListaInfoDinamicaClave();

        if (nodo == null) {
            return null;
        } 
        ListaInfoDinamica palabras = separarCadena(nodo.getDato().toString(), 0);
        if (palabras.encontrar(palabraClave) != null) {
            conceptosEncontrados.insertar(nodo.getClave(), nodo.getDato());
        }
        conceptosEncontrados.agregarLista( buscarPalabraClave(nodo.getEnlaceDer(), palabraClave) );

        return conceptosEncontrados;
    }

    public void mostrarTodos(){
        NodoClaveValor inicio = listaConceptos.getApuntadorInicial();
        mostrarTodos(inicio);
    }

    private void mostrarTodos(NodoClaveValor nodo){
        if (nodo == null ) {
            return;
        }
        consola(nodo.getClave() + ": " + nodo.getDato() + "\n");
        mostrarTodos(nodo.getEnlaceDer());
    }

    private ListaInfoDinamica separarCadena(String cadena, int indice) {
        if (indice >= cadena.length()) {
            return new ListaInfoDinamica();
        }

        ListaInfoDinamica palabras = new ListaInfoDinamica();
        String palabra = "";

        while (indice < cadena.length() && cadena.charAt(indice) != ' ') {
            palabra = palabra + cadena.charAt(indice);
            indice = indice + 1;
        }

        palabras.insertar(palabra);
        palabras.agregarLista(separarCadena(cadena, indice + 1));

        return palabras;
    }

}
