package estructuraslineales;

import estructuraslineales.auxiliares.Nodo;
import estructuraslineales.auxiliares.NodoBuscado;
import estructurasnolineales.MatrizInfo2;
import utils.ComparadorObjetos;
import utils.comunes.TipoOrdenamiento;

public class ListaInfoDinamicaOrdenada extends ListaInfoDinamica{
    
    protected TipoOrdenamiento orden;

    public ListaInfoDinamicaOrdenada(TipoOrdenamiento orden){
        this.orden = orden;
    }

    //Busca el elemento ubicado en cierto indice
    public Object buscar(int indice){
        int contador = 0;
        Nodo temporal = apuntadorInicial;
        while (temporal != null && contador <= indice ) {
            if (contador == indice) {
                return temporal.getDato();
            } 
            contador = contador + 1;
            temporal = temporal.getEnlaceDer();
        }
        return null;
    }

    //Imprime la lista
    public void mostrar(){
        super.mostrar();
    }

    //Muestra la lista en orden inverso
    public void mostrarOI(){
        super.mostrarOI();
    }

    //Inserta el valor en la posició correcta de acuerdo a su orden
    public Integer insertar(Object valor){
        Nodo nodoNuevo = new Nodo(valor); //paso 1
        NodoBuscado nodoBuscado = encontrarNodo(valor);

        if (nodoNuevo != null) { //Asumo que me prestaron memoria
            if (estaVacia() == true) { //Si la lista esta vacia
                apuntadorInicial = nodoNuevo; //paso 2
                apuntadorFinal = nodoNuevo;
            } else if (apuntadorInicial == nodoBuscado.getBuscado()){ //Si el valor debe ir al inicio y no esta vacia
                apuntadorInicial = nodoNuevo;
                apuntadorInicial.setEnlaceDer(nodoBuscado.getBuscado());
            } else { //Si va en cualquier otro lugar
                nodoBuscado.getAnterior().setEnlaceDer(nodoNuevo);
                nodoNuevo.setEnlaceDer(nodoBuscado.getBuscado());
            }

            // Actualizar apuntadorFinal
            Nodo temp = apuntadorInicial;
            while (temp.getEnlaceDer() != null) {
                temp = temp.getEnlaceDer();
            }
            apuntadorFinal = temp;
            
            return 0;
        } else { //No me prestaron memoria
            return -1;
        }
    }

    //Elimina el valor metido como parametro
    public Object borrar(Object valor){
        return super.borrar(valor);
    }
    
    //Inserta el elemento al final si es menor o mayor que el ultimo depende el orden
    public Integer insertarFinal(Object valor){
        ComparadorObjetos comparador = new ComparadorObjetos();
        
        if (orden == TipoOrdenamiento.INC) {
            if (comparador.compararObjetos(valor, apuntadorFinal.getDato()) > 0) {
                insertar(valor);
                return 0; //Se inserto el elemento
            } else {
                return -1; //No se inserto al no ser mayor que el ultimo elemento
            }
        } else {
            if (comparador.compararObjetos(valor, apuntadorFinal.getDato()) <= 0) {
                insertar(valor);
                return 0; //Se inserto el elemento
            } else {
                return -1; //No se inserto al no ser menor que el ultimo elemento
            }
        }
    }

    //Inserta el elemento al inicio si es menor o mayor que el primero depende el orden
    public Integer insertarInicio(Object valor){
        ComparadorObjetos comparador = new ComparadorObjetos();
        
        if (orden == TipoOrdenamiento.INC) {
            if (comparador.compararObjetos(valor, apuntadorFinal.getDato()) <= 0) {
                super.insertarInicio(valor);
                return 0; //Se inserto el elemento
            } else {
                return -1; //No se inserto al no ser mayor que el ultimo elemento
            }
        } else {
            if (comparador.compararObjetos(valor, apuntadorFinal.getDato()) >= 0) {
                super.insertarInicio(valor);
                return 0; //Se inserto el elemento
            } else {
                return -1; //No se inserto al no ser menor que el ultimo elemento
            }
        }
    }
    
    //Elimina el ultimo elemento de la lista
    public Object borrarFinal(){
        return super.borrar();
    }

    //Elimina el primer elemento de la lista
    public Object borrarInicio(){
        return super.borrarInicio();
    }

    //Agrega los elementos de una lista pasada como argumento
    public boolean agregarLista(ListaInfo listaDatos2){
        while (listaDatos2.estaVacia() == false) {
            if (insertar(listaDatos2.borrarInicio()) == -1) {
                return false;
            }
        }
        return true;
    }

    //Agrega los elementos gaurdados en una matriz
    public boolean agregarMatriz2D(MatrizInfo2 tabla){
        for (int cadaRenglon = 0; cadaRenglon < tabla.obtenerRenglones(); cadaRenglon++) {
            for (int cadaColumna = 0; cadaColumna < tabla.obtenerColumnas(); cadaColumna++) {
                if (insertar(tabla.obtener(cadaRenglon, cadaColumna)) == -1) { //No se pudo insertar el valor
                    return false;
                }
            }
        }
        return true;
    }

    //Elimina los elementos de una lista pasada como parametro
    public boolean borrarLista(ListaInfo listaDatos2){
        while (listaDatos2.estaVacia() == false) {
            if (borrar(listaDatos2.borrarInicio()) == null) {
                return false;
            }
        }
        return true;
    }
   
    //Borra los elementos gaurdados en una matriz
    public boolean borrarMatriz2D(MatrizInfo2 tabla){
        for (int cadaRenglon = 0; cadaRenglon < tabla.obtenerRenglones(); cadaRenglon++) {
            for (int cadaColumna = 0; cadaColumna < tabla.obtenerColumnas(); cadaColumna++) {
                borrar(tabla.obtener(cadaRenglon, cadaColumna));
            }
        }
        return true;
    }
    
    //Invierte el orden de los elementos
    public void invertir(){
        if (orden == TipoOrdenamiento.INC) {
            orden = TipoOrdenamiento.DEC;
        } else {
            orden = TipoOrdenamiento.INC;
        }
        super.invertir();
    }
    
    public boolean cambiar(int indice, Object valor){
        Nodo temporal = apuntadorInicial;
        int contador = 0;
        while (temporal != null) {
            if (contador == indice) {
                borrar(temporal.getDato());
                insertar(valor);
            }
            temporal = temporal.getEnlaceDer();
            contador = contador + 1; 
        }
        return true;
    }

    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces){
        Nodo temporal = apuntadorInicial;
        int contador = 0;
        while (temporal != null && contador < numVeces) {
            if (temporal.getDato() == valorViejo) {
                borrar(temporal.getDato());
                insertar(valorNuevo);
                contador = contador + 1;
            }
            temporal = temporal.getEnlaceDer();
        }
        return true;
    }
    
    private NodoBuscado encontrarNodo(Object valor){
        ComparadorObjetos comparador = new ComparadorObjetos();
        Nodo temporal = apuntadorInicial;
        Nodo anterior = apuntadorInicial;

        // Busca mientras halla elementos donde buscar, y mientras no encuentre el que busco
        if (orden == TipoOrdenamiento.INC){
            while (temporal != null && comparador.compararObjetos(valor, temporal.getDato()) > 0) {
                anterior = temporal;
                temporal = temporal.getEnlaceDer(); // i = i +1
            }
        } else {
            while (temporal != null && comparador.compararObjetos(valor, temporal.getDato()) < 0) {
                anterior = temporal;
                temporal = temporal.getEnlaceDer(); // i = i +1
            }
        }
        NodoBuscado busqueda = new NodoBuscado();
        busqueda.setAnterior(anterior);
        busqueda.setBuscado(temporal);
        return busqueda;
    }

}
