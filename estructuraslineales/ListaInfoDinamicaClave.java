package estructuraslineales;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.auxiliares.Nodo;
import estructuraslineales.auxiliares.NodoBuscadoClave;
import estructuraslineales.auxiliares.NodoClaveValor;
import estructurasnolineales.MatrizInfo2;

public class ListaInfoDinamicaClave {
    protected NodoClaveValor apuntadorInicial;
    protected NodoClaveValor apuntadorFinal;

    public ListaInfoDinamicaClave(){
        apuntadorInicial = null;
        apuntadorFinal = null;
    }

    public boolean insertar(Object clave, Object valor){
        NodoClaveValor nodoNuevo = new NodoClaveValor(clave, valor);
        if (nodoNuevo != null) { //Asumo que me prestaron memoria
            if (vacia() == true) {
                apuntadorInicial = nodoNuevo; //paso 2
                apuntadorFinal = nodoNuevo;
            } else { 
                NodoClaveValor temporal = apuntadorInicial;
                boolean repetido = false;
                while (temporal != null) {
                    if (temporal.getClave() == clave) {
                        repetido = true;
                    }
                    temporal = temporal.getEnlaceDer();
                }
                if (repetido == false) { //Si la clave no esta repetida, agrega el nodo
                    apuntadorFinal.setEnlaceDer(nodoNuevo);//paso 2
                    apuntadorFinal = nodoNuevo;//paso 3
                }
            }
            return true;
        } else { //No me prestaron memoria
            return false;
        }
    }

    public Object borrar(Object clave){
        NodoClaveValor actual = apuntadorInicial;
        NodoClaveValor anterior = null;

        while (actual != null) {
            if (actual.getClave().equals(clave)) {
                if (anterior == null) { // Si el nodo a eliminar es el primero de la lista
                    apuntadorInicial = actual.getEnlaceDer();
                } else { // Si el nodo a eliminar está en medio o al final de la lista
                    anterior.setEnlaceDer(actual.getEnlaceDer());
                }
                if (actual == apuntadorFinal) { // Si el nodo a eliminar es el último de la lista
                    apuntadorFinal = anterior;
                }
                return actual.getDato();
            }
            anterior = actual;
            actual = actual.getEnlaceDer();
        }
        return null;
    }

    public Object borrarContenido(Object valor){
        NodoBuscadoClave busqueda = encontrarNodo(valor);
        if(busqueda.getBuscado() == null){
            return null;
        }else{// si hay el elemetno buscado
            Object respaldo = busqueda.getBuscado().getDato(); // paso 1
            if(apuntadorFinal == apuntadorInicial){//b) unico
                apuntadorInicial = null; //paso 2
                apuntadorFinal = null;
            }else if(apuntadorInicial == busqueda.getBuscado()){// c), es el primero
                apuntadorInicial = apuntadorInicial.getEnlaceDer(); // paso 2

            }else if(apuntadorFinal == busqueda.getBuscado()){
                NodoClaveValor anterior = busqueda.getAnterior();
                apuntadorFinal = anterior; // paso 2
                apuntadorFinal.setEnlaceDer(null);
            }else{ // f) cuando hay varios y esta en medio
                NodoClaveValor anterior = busqueda.getAnterior();
                NodoClaveValor temporal = busqueda.getBuscado();
                anterior.setEnlaceDer(temporal.getEnlaceDer()); // paso 2
            }
            return respaldo;
        }
    }

    public Object encontrar(Object clave) {
        NodoClaveValor actual = apuntadorInicial;

        while (actual != null) {
            if (actual.getClave().equals(clave)) {
                return actual.getDato();
            }
            actual = actual.getEnlaceDer();
        }
        return null;
    }

    public Object encontrarContenido(Object valor) { // Encuentra un valor entre la lista
        NodoBuscadoClave busqueda = encontrarNodo(valor);
        if(busqueda.getBuscado() != null){
            return busqueda.getBuscado().getDato();
        }else{// no hay nada o no esta
            return null;
        }
    }

    public boolean cambiar(Object clave, Object valor) {// Con ayuda de la clave cambia el valor antiguo con el actual.
        NodoClaveValor actual = apuntadorInicial;
    
        while (actual != null) {
            if (actual.getClave().equals(clave)) {
                actual.setDato(valor);
                return true; 
            }
            actual = actual.getEnlaceDer();
        }
        return false;
    }

    public boolean cambiarValor(Object valorViejo, Object valorNuevo) { // Cambia el valor identificado por uno nuevo pasado como parametro
        NodoBuscadoClave busqueda = encontrarNodo(valorViejo);
        NodoClaveValor nodoBuscado = busqueda.getBuscado();
    
        if (nodoBuscado != null) {
            nodoBuscado.setDato(valorNuevo);
            return true; 
        }
        return false;
    }

    public void mostrar(){
        NodoClaveValor temporal = apuntadorInicial;
        while (temporal != null) {
                SalidaPorDefecto.consola(temporal.toString() +" -> ");
            temporal = temporal.getEnlaceDer();
        }
        SalidaPorDefecto.consola("null\n");
    }

    public void mostrarClaves(){
        NodoClaveValor temporal = apuntadorInicial;
        while (temporal != null) {
            SalidaPorDefecto.consola(temporal.getClave() + " -> ");
            temporal = temporal.getEnlaceDer();
        }
        SalidaPorDefecto.consola("null\n");
    }

    public void mostrarValores(){
        NodoClaveValor temporal = apuntadorInicial;
        while (temporal != null) {
            SalidaPorDefecto.consola(temporal.getDato() + " -> ");
            temporal = temporal.getEnlaceDer();
        }
        SalidaPorDefecto.consola("null\n");
    }

    public ListaInfoEstatica aListasEstaticas() { // Convierte la listaDinamicaClave en lista estatica en donde se guardan las claves y valores
        NodoClaveValor Busqueda = apuntadorInicial;

        ListaInfoEstatica listaClaves = new ListaInfoEstatica(cantidad());
        ListaInfoEstatica listaContenidos = new ListaInfoEstatica(cantidad());

        while (Busqueda != null) {
            Object contenido = Busqueda.getDato();
            Object clave = Busqueda.getClave();
            listaClaves.insertar(clave);
            listaContenidos.insertar(contenido);
            Busqueda = Busqueda.getEnlaceDer();
        }

        ListaInfoEstatica listaResultado = new ListaInfoEstatica(2);
        listaResultado.insertar(listaClaves);
        listaResultado.insertar(listaContenidos);
    
        return listaResultado;
    }

    public ListaInfoDinamica aListasDinamicas() {
        ListaInfoDinamica listaClaves = new ListaInfoDinamica();
        ListaInfoDinamica listaContenidos = new ListaInfoDinamica();
    
        NodoClaveValor actual = apuntadorInicial;
        while (actual != null) {
            Object clave = actual.getClave();
            Object valor = actual.getDato();
            
            listaClaves.insertar(clave);
            listaContenidos.insertar(valor);
        
            actual = actual.getEnlaceDer();
        }
    
        ListaInfoDinamica listaResultado = new ListaInfoDinamica();
        listaResultado.insertar(listaClaves);
        listaResultado.insertar(listaContenidos);
    
        return listaResultado;
    }

    public MatrizInfo2 aMatriz2() {
        NodoClaveValor actual = apuntadorInicial;
        int numNodos = 0;
        while (actual != null) {
            numNodos = numNodos + 1;
            actual = actual.getEnlaceDer();
        }
        
        MatrizInfo2 matriz = new MatrizInfo2(numNodos, 2);
        actual = apuntadorInicial;
        int fila = 0;

        while (actual != null) {
            matriz.cambiar(fila, 0, actual.getClave());
            matriz.cambiar(fila, 1, actual.getDato());
            fila = fila + 1;
            actual = actual.getEnlaceDer();
        }
    
        return matriz;
    }

    public void vaciar() { // Vacia los datos actuales
        apuntadorInicial = null;
        apuntadorFinal = null;
    }

    public Object obtener(Object clave) {
        NodoClaveValor nodoActual = apuntadorInicial;
        
        while (nodoActual != null) {
            if (nodoActual.getClave().equals(clave)) {
                return nodoActual.getDato();
            }
            nodoActual = nodoActual.getEnlaceDer();
        }
        return null;
    }

    public boolean vacia(){
        if (apuntadorInicial == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarLista(ListaInfoDinamicaClave lista2) {
        boolean seAgrego = false;

        if (lista2 != null) {
            NodoClaveValor nodoLista2 = lista2.apuntadorInicial;
    
            while (nodoLista2 != null) {
                Object clave = nodoLista2.getClave();
                Object valor = nodoLista2.getDato();
    
                if (encontrar(clave) == null) {
                    insertar(clave, valor);
                    seAgrego = true; 
                }
                nodoLista2 = nodoLista2.getEnlaceDer();
            }
        }
        return seAgrego;
    }

    public int cantidad() {
        int contador = 0;
        NodoClaveValor nodoActual = apuntadorInicial;
        
        while (nodoActual != null) {
            contador = contador+1;
            nodoActual = nodoActual.getEnlaceDer();
        }
        return contador;
    }

    public boolean agregarListasEstaticas(ListaInfoEstatica arregloClaves, ListaInfoEstatica arregloValores) {
        if (arregloClaves.cantidad() != arregloValores.cantidad()) {
            return false;
        }
    
        for (int actual = 0; actual < arregloClaves.cantidad(); actual++) {
            Object clave = arregloClaves.obtener(actual);
            Object valor = arregloValores.obtener(actual);
            
            if (encontrar(clave) == null) {
                insertar(clave, valor);
            }
        }
    
        return true;
    }

    public boolean agregarListasDinamicas(ListaInfoDinamica listaClaves, ListaInfoDinamica listaValores) {
        Nodo nodoClave = listaClaves.getApuntadorInicial();
        Nodo nodoValor = listaValores.getApuntadorInicial();
    
        while (nodoClave != null && nodoValor != null){
            Object clave = nodoClave.getDato();
            Object valor = nodoValor.getDato();
            
            if (encontrar(clave) == null) {
                insertar(clave, valor);
            }
            nodoClave = nodoClave.getEnlaceDer();
            nodoValor = nodoValor.getEnlaceDer();
        }
        
        return true;
    }

    public boolean agregarMatriz2(MatrizInfo2 matriz) {
        if (matriz.obtenerColumnas() != 2) {
            return false; 
        }
        for (int fila = 0; fila < matriz.obtenerRenglones(); fila++) {
            Object clave = matriz.obtener(fila, 0);
            Object valor = matriz.obtener(fila, 1);
            
            if (encontrar(clave) == null) {
                insertar(clave, valor);
            } 
        }
        return true;
    }

    private NodoBuscadoClave encontrarNodo(Object valor){ //Encuentra el nodo en la lista
        NodoClaveValor temporal = apuntadorInicial;
        NodoClaveValor anterior = apuntadorInicial;
        //Mientras existan elementos en donde buscar que siga buscando.
        while (temporal != null && valor.toString().equalsIgnoreCase(temporal.getDato().toString()) == false) {
            anterior = temporal;
            temporal = temporal.getEnlaceDer(); // i = i + 1
        }
        NodoBuscadoClave busqueda = new NodoBuscadoClave();
        busqueda.setAnterior(anterior);
        busqueda.setBuscado(temporal);
        return busqueda;
    }

    public NodoClaveValor getApuntadorInicial(){
        return apuntadorInicial;
    }
    
}
