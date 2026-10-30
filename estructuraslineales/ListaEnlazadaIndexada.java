package estructuraslineales;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.auxiliares.Nodo;

public class ListaEnlazadaIndexada {
    protected Nodo apuntadorInicial;
    protected int cantidad;

    public ListaEnlazadaIndexada(){
        apuntadorInicial = null;
    }

    public int cantidad(){
        return cantidad;
    }

    public boolean insertar(int indice, Object valor){
        if (indice < 0) {
            return false;
        }
        apuntadorInicial = insertar(apuntadorInicial, valor, indice, 0);
        cantidad = cantidad + 1; 
        return true;
    }

    private Nodo insertar(Nodo temporal, Object valor, int indice, int indiceActual){
        if (temporal == null || indice == 0) { //Si esta vacia o es el primer elemento
            Nodo nuevoNodo = new Nodo(valor);
            nuevoNodo.setEnlaceDer(temporal);
            return nuevoNodo;
        } else if (indiceActual == indice - 1) { //Cuando lleguemos a la posición anterior de donde insertaremos
            Nodo nuevoNodo = new Nodo(valor);
            nuevoNodo.setEnlaceDer(temporal.getEnlaceDer()); //Enlazamos al elemento que va en el indice que queremos
            temporal.setEnlaceDer(nuevoNodo); //Enlazamos el nodo nuevo con el que va antes de el
        } else {
            temporal.setEnlaceDer(insertar(temporal.getEnlaceDer(), valor, indice, indiceActual + 1)); //Llamamos la recursión si no es el que buscamos
        }
        return temporal;
    }

    public Object eliminar(int indice){
        if (indice < 0 || indice >= cantidad()) {
            return null;
        } else {
            cantidad = cantidad - 1;
            return eliminar(apuntadorInicial, indice, 0);
        }
    }

    private Object eliminar(Nodo temporal, int indice, int indiceActual){
        if (indice == 0) {
            Object valor = temporal.getDato();
            apuntadorInicial = apuntadorInicial.getEnlaceDer();
            return valor;
        } else if (indiceActual == indice - 1) {
            Object valor = temporal.getEnlaceDer().getDato();
            temporal.setEnlaceDer(temporal.getEnlaceDer().getEnlaceDer());
            return valor;
        } else {
            return eliminar(temporal.getEnlaceDer(), indice, indiceActual + 1);
        }
    }

    public void mostrar(){
        mostrar(apuntadorInicial);
        consola("null\n");
    }

    private void mostrar(Nodo temporal){
        if (temporal == null) {
            return;
        } else {
            consola(temporal.getDato() + " -> ");
            mostrar(temporal.getEnlaceDer());
        }
    }

    public Object obtener(int indice){
        if (indice < 0 || indice >= cantidad()) {
            return null;
        } else {
            return obtener(apuntadorInicial, indice, 0);
        }
    }

    private Object obtener(Nodo temporal, int indice, int indiceActual){
        if (indiceActual == indice) {
            return temporal.getDato();
        } else {
            return obtener(temporal.getEnlaceDer(), indice, indiceActual + 1);
        }
    }
}
