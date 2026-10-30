package estructuraslineales;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.auxiliares.Nodo;
import estructuraslineales.auxiliares.NodoBuscado;
import estructurasnolineales.MatrizInfo2;
import utils.comunes.TipoTabla;

public class ListaInfoDinamica implements ListaInfo{
    protected Nodo apuntadorInicial;
    protected Nodo apuntadorFinal;
    protected Nodo iterador;

    public ListaInfoDinamica(){
        apuntadorInicial = null;
        apuntadorFinal = null;
    }

    public Nodo getApuntadorInicial(){
        return apuntadorInicial;
    }

    public boolean estaVacia(){
        if (apuntadorInicial == null) {
            return true;
        } else {
            return false;
        }
    }

    public Integer insertar(Object valor){
        Nodo nodoNuevo = new Nodo(valor); //paso 1
        if (nodoNuevo != null) { //Asumo que me prestaron memoria
            if (estaVacia() == true) { //a
                apuntadorInicial = nodoNuevo; //paso 2
                apuntadorFinal = nodoNuevo;
            } else { //b, c
                apuntadorFinal.setEnlaceDer(nodoNuevo);//paso 2
                apuntadorFinal = nodoNuevo;//paso 3
            }
            return 0;
        } else { //No me prestaron memoria
            return -1;
        }
    }

    public Integer insertarInicio(Object valor){
        Nodo nodoNuevo = new Nodo(valor);
        if (nodoNuevo != null) { //Se pudo
            if (estaVacia() == true) { //a
                apuntadorInicial = nodoNuevo; //paso 2
                apuntadorFinal = nodoNuevo;
            } else { //b, c
                nodoNuevo.setEnlaceDer(apuntadorInicial);
                apuntadorInicial = nodoNuevo;
            }
            return 0;
        } else { //Hay error, o equivalente a lleno
            return -1;
        }
    }

    private NodoBuscado encontrarNodo(Object valor){
        Nodo temporal = apuntadorInicial;
        Nodo anterior = apuntadorInicial;

        //Mientras existan elementos donde buscar y mientras no sea igual a lo que busco
        while (temporal != null && valor.toString().equalsIgnoreCase(temporal.getDato().toString()) == false) {
            anterior = temporal;
            temporal = temporal.getEnlaceDer(); //i = i + 1
        }
        NodoBuscado busqueda = new NodoBuscado();
        busqueda.setBuscado(temporal);
        busqueda.setAnterior(anterior);
        return busqueda;
    }
    
    public Object encontrar(Object valor){
        NodoBuscado busqueda = encontrarNodo(valor);
        
        if (busqueda.getBuscado() != null) { //Hay algo
            return busqueda.getBuscado().getDato();
        } else { //No hay nada o no esta
            return null;
        }
    }

    public Object borrar(Object valor){
        NodoBuscado busqueda = encontrarNodo(valor);
        if (busqueda.getBuscado() == null) {//a), d) no esta, ya sea por vacio o porque no existe el elemento
            return null;
        } else { //Si esta el elemento buscado
            //Paso 1
            Object respaldo = busqueda.getBuscado().getDato();
            if (apuntadorInicial == apuntadorFinal) { //b), unico
                //paso 2
                apuntadorInicial = null;
                apuntadorFinal = null;
            } else if(apuntadorInicial == busqueda.getBuscado()) { //c), es el primero
                //paso 2
                apuntadorInicial = apuntadorInicial.getEnlaceDer();
            } else if (apuntadorFinal == busqueda.getBuscado()) { //e), es el ultimo
                //paso 2
                Nodo anterior = busqueda.getAnterior();
                apuntadorFinal = anterior;
                //paso 3
                apuntadorFinal.setEnlaceDer(null);
            } else { //f) cuando hay varios y esta en medio
                //paso 2 
                Nodo anterior = busqueda.getAnterior();
                Nodo temporal = busqueda.getBuscado();
                anterior.setEnlaceDer(temporal.getEnlaceDer());
            }
            return respaldo;
        }
       
    }

    public Object borrarInicio(){
        if (estaVacia() == false) {
            //paso 1
            Object respaldo = apuntadorInicial.getDato();
            if (apuntadorInicial == apuntadorFinal) { //b
                //paso 2
                apuntadorInicial = null;
                apuntadorFinal = null;
            } else {
                //paso 2
                apuntadorInicial = apuntadorInicial.getEnlaceDer();
            }
            return respaldo;
        } else { //a, no hay nada
            return null;
        }
    }

    public void mostrar(){
        Nodo temporal = apuntadorInicial;
        while (temporal != null) {
            if (temporal.getDato() == null) {
                SalidaPorDefecto.consola("null" + " -> ");
            } else {
                SalidaPorDefecto.consola(temporal.toString() + " -> ");
            }
            temporal = temporal.getEnlaceDer();
        }
        SalidaPorDefecto.consola("null");
    }

    public void mostrarOI(){
        PilaInfoDinamica pila = new PilaInfoDinamica();
        Nodo temporal = apuntadorInicial;
        while (temporal != null) {
            pila.poner(temporal);
            temporal = temporal.getEnlaceDer();
        }
        SalidaPorDefecto.consola("null -> ");
        while (pila.vacio() == false) {
            SalidaPorDefecto.consola(pila.quitar().toString() + " -> ");
        }
        SalidaPorDefecto.consola("null");
    }

    public ListaInfoDinamica encontrarLista(Object valor){
        Nodo temporal = apuntadorInicial;
        ListaInfoDinamica listaRetorno = new ListaInfoDinamica();
        while (temporal != null) {
            if (temporal.getDato() == valor) {
                listaRetorno.insertar(valor);
            }
            temporal = temporal.getEnlaceDer();
        }
        return listaRetorno;
    }

    public ListaInfoEstatica aListaEstatica(){
        int cuantos = 0;
        Nodo temporal = apuntadorInicial;
        while (temporal != null) {
            cuantos = cuantos + 1;
            temporal = temporal.getEnlaceDer();
        }

        temporal = apuntadorInicial;
        ListaInfoEstatica listaEstatica = new ListaInfoEstatica(cuantos);
        while (temporal != null) {
            listaEstatica.insertar(temporal.getDato());
            temporal = temporal.getEnlaceDer();
        }
        return listaEstatica;
    }

    public ListaInfoEstatica aListaEstatica(ListaInfoEstatica elementosADescartar){
        Nodo temporal = apuntadorInicial;
        int cantidadElementos = 0;
        while (temporal != null) {
            temporal = temporal.getEnlaceDer();
            cantidadElementos = cantidadElementos + 1;
        }

        temporal = apuntadorInicial;
        ListaInfoEstatica listaEstatica = new ListaInfoEstatica(cantidadElementos);

        while (temporal != null) {
            boolean agregar = true;
            for (int indiceEstatica = 0; indiceEstatica < elementosADescartar.cantidad(); indiceEstatica++) {
                if (temporal.getDato() == elementosADescartar.obtener(indiceEstatica)) {
                    agregar = false;
                }
            }
            if (agregar == true) {
                    listaEstatica.insertar(temporal.getDato());
            }
            temporal = temporal.getEnlaceDer();
        }
        return listaEstatica;
    }

    public MatrizInfo2 aMatriz2D(int filas, int columnas){
        MatrizInfo2 matriz = new MatrizInfo2(filas, columnas);
        Nodo temporal = apuntadorInicial;

        for (int cadaRenglon = 0; cadaRenglon < filas; cadaRenglon++) {
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
                if (temporal != null) {
                    matriz.cambiar(cadaRenglon, cadaColumna, temporal.getDato());
                    temporal = temporal.getEnlaceDer();    
                }
            }
        }
        return matriz;
    }

    public boolean agregarLista(ListaInfo listaDatos2){
        while (listaDatos2.estaVacia() == false) {
            if (insertar(listaDatos2.borrarInicio()) == -1) {
                return false;
            }
        }
        return true;
    }

    public ListaInfo clonar(){
        ListaInfo clon = new ListaInfoDinamica();
        Nodo actual = apuntadorInicial;
        while (actual != null) {
            Object dato = actual.getDato();
            clon.insertar(dato);
            actual = actual.getEnlaceDer();
        }
        return clon;
    }

    public boolean agregarMatriz2D(MatrizInfo2 tabla ,TipoTabla tipo){
        if (tipo == TipoTabla.RENGLON) {
            for (int cadaRenglon = 0; cadaRenglon < tabla.obtenerRenglones(); cadaRenglon++) {
                for (int cadaColumna = 0; cadaColumna < tabla.obtenerColumnas(); cadaColumna++) {
                    if (insertar(tabla.obtener(cadaRenglon, cadaColumna)) == -1) { //No se pudo insertar el valor
                        return false;
                    }
                }
            }
            return true;
        } else {
            for (int cadaColumna = 0; cadaColumna < tabla.obtenerColumnas(); cadaColumna++){
                for (int cadaRenglon = 0; cadaRenglon < tabla.obtenerRenglones(); cadaRenglon++) {
                    if (insertar(tabla.obtener(cadaRenglon, cadaColumna)) == -1) { //No se pudo insertar el valor
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public Object obtener(int indice){
        Nodo temporal = apuntadorInicial;
        int iterador = 0;
        Object retorno = null;
        while (temporal != null && iterador <= indice) {
            if (iterador == indice) {
                retorno = temporal.getDato();
            }
            iterador = iterador + 1;
            temporal = temporal.getEnlaceDer();
        }
        return retorno;
    }

    public boolean esIgual(ListaInfo listaDatos2){
        if (listaDatos2 instanceof ListaInfoDinamica) {
            ListaInfoEstatica listaEstatica = aListaEstatica();
            ListaInfoEstatica listaEstatica2 = ((ListaInfoDinamica)listaDatos2).aListaEstatica();

            return listaEstatica.esIgual(listaEstatica2);
        } else {
            return false;
        }
    }

    public Object redimensionar(int maximo){
        ListaInfoDinamica respaldo = new ListaInfoDinamica();
        Nodo temporal = apuntadorInicial;
        for (int contador = 0; contador < maximo; contador++) {
            if (temporal != null) {
                respaldo.insertar(obtener(contador));
                temporal.getEnlaceDer();
            }
        }
        vaciar();
        Nodo tempRes = respaldo.apuntadorInicial;
        while (tempRes != null) {
            insertar(tempRes.getDato());
            tempRes = tempRes.getEnlaceDer();
        }
        return respaldo;
    }

    public ListaInfoDinamica Borrar(Object valor){
        Nodo temporal = apuntadorInicial;
        ListaInfoDinamica listaBorrados = new ListaInfoDinamica();
        while (temporal != null) {
            if (temporal.getDato() == valor) {
                listaBorrados.insertar(borrar(valor));
            }
            temporal = temporal.getEnlaceDer();
        }
        return listaBorrados;
    }

    public Object borrar(int indice){
        Object valorABorrar = obtener(indice);
        return borrar(valorABorrar);
    }
    
    public boolean esIgual(Object lista2){
        return esIgual(lista2);
    }

    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces){
        Nodo temporal = apuntadorInicial;
        int contador = 0;
        while (temporal != null && contador < numVeces) {
            if (temporal.getDato() == valorViejo) {
                temporal.setDato(valorNuevo);
                contador = contador + 1;
            }
            temporal = temporal.getEnlaceDer();
        }
        return true;
    }

    public boolean cambiar(int indice, Object valor){
        Nodo temporal = apuntadorInicial;
        int iterador = 0;
        while (temporal != null && iterador <= indice) {
            if (iterador == indice) {
                temporal.setDato(valor);
            }
            iterador = iterador + 1;
            temporal = temporal.getEnlaceDer();
        }
        return true;
    }

    public ListaInfoEstatica encontrarValores(Object valor){
        Nodo temporal = apuntadorInicial;
        ListaInfoDinamica listaEncontrados = new ListaInfoDinamica();
        while (temporal != null) {
            if (temporal.getDato() == valor) {
                listaEncontrados.insertar(temporal.getDato());
            }
            temporal = temporal.getEnlaceDer();
        }
        return listaEncontrados.aListaEstatica();
    }

    public Object borrar(){
        NodoBuscado busqueda = encontrarNodo(apuntadorFinal.getDato());
        //busqueda.getBusquedo.getEnlaceDer == null  //Es el ultimo
        if (busqueda.getBuscado() != null) { //Siuuuuuuuuuuuu hay algo
            //paso 1
            Object respaldo = apuntadorFinal.getDato();
            if (apuntadorInicial == apuntadorFinal) { //unico, b)
                //paso 2
                apuntadorInicial = null;
                apuntadorFinal = null;
            } else {
                //paso 2
                Nodo anterior = busqueda.getAnterior();
                anterior.setEnlaceDer(null);
                //paso 3
                apuntadorFinal = anterior;
            }
            return respaldo;
        } else { // No hay nada, a)
            return null;
        }         
    }

    public void vaciar(){
        apuntadorInicial = null;
        apuntadorFinal = null;
    }
    
    public boolean agregarLista(Object lista2){
        if (lista2 instanceof ListaInfoDinamica == true) {
            ListaInfoDinamica lista2Dinamica = (ListaInfoDinamica) lista2;
            Nodo temporal = lista2Dinamica.apuntadorInicial;
            while (temporal != null) {
                insertar(temporal.getDato());
                temporal = temporal.getEnlaceDer();
            }
            return true;
        } else {
            return false;
        }
    }

    public void invertir(){
        ColaInfoDinamica cola = new ColaInfoDinamica();
        while (estaVacia() == false) {
            cola.poner(borrar());
        }
        while (cola.vacio() == false) {
            insertar(cola.quitar());
        }
    }

    public int contar(Object valor){
        int cantidad = 0;
        Nodo temporal = apuntadorInicial;
        while (temporal != null) {
            if (temporal.getDato() == valor) {
                cantidad = cantidad + 1;
            }
            temporal = temporal.getEnlaceDer();
        }
        return cantidad;
    }

    public boolean borrarLista(Object lista2){
        if (lista2 instanceof ListaInfoDinamica == true) {
            ListaInfoDinamica lista2Dinamica = (ListaInfoDinamica)lista2;
            Nodo temporal = lista2Dinamica.apuntadorInicial;
            while (temporal != null) {
                borrar(temporal.getDato());
                temporal = temporal.getEnlaceDer();
            }
            return true;
        } else {
            return false;
        }
    }

    public void rellenar(Object valor, int cantidad){
        for (int contador = 0; contador < cantidad; contador++) {
            insertar(valor);
        }
    }

    public ListaInfoEstatica subLista(int indiceInicial, int indiceFinal){
        ListaInfoEstatica listaEstatica = aListaEstatica();
        return listaEstatica.subLista(indiceInicial, indiceFinal);
    }

    public void rellenar(Object valor){
        rellenar(valor, 10);
    }

    public ListaInfoEstatica arregloDesordenado(){
        ListaInfoEstatica listaEstatica = aListaEstatica();
        return listaEstatica.arregloDesordenado();
    }

    public boolean esSublista(ListaInfoEstaticaOrdenada lista2){
        Nodo temporal = apuntadorInicial;
        int indiceActual = 0;
        int indicesIguales = 0;
        while (temporal != null || indiceActual < lista2.cantidad()) {
            if(temporal.getDato().equals(lista2.obtener(indiceActual))){
                indicesIguales = indicesIguales+1;
            }
            temporal.getEnlaceDer();
            indiceActual = indiceActual+1;
        }
        if(indicesIguales == indiceActual){
            return true;
        }else{
            return false;
        }
    }

    public boolean cambiarLista(ListaInfoEstaticaOrdenada lista2, ListaInfoEstaticaOrdenada lista2Nuevos){
        int indlista2 = 0;
        int indLista2Nuevos = 0;
        
        if (!estaVacia() && lista2 != null && !lista2.estaVacia() && lista2Nuevos != null && !lista2Nuevos.estaVacia()) {
            while (indlista2 < lista2.cantidad() && indLista2Nuevos < lista2Nuevos.cantidad()) {
                Object valorLista2 = lista2.obtener(indlista2);
                Object valorLista2Nueva = lista2Nuevos.obtener(indLista2Nuevos);
                NodoBuscado nodoBuscado = encontrarNodo(valorLista2);

                if (nodoBuscado.getBuscado() != null) {
                    borrar(nodoBuscado.getBuscado().getDato());
                    insertar(valorLista2Nueva); 
                    indLista2Nuevos = indLista2Nuevos+ 1;
                }
                indlista2 = indlista2+1;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean retenerLista(ListaInfoEstaticaOrdenada lista2){
        ListaInfoDinamica listaTemp = new ListaInfoDinamica();
        int indLista2 = 0;
        while (indLista2 < lista2.cantidad()) {
            Object elementoLista2 = lista2.obtener(indLista2);
            if (encontrar(elementoLista2) != null) {
                listaTemp.insertar(elementoLista2);
            }
            indLista2 = indLista2+1;
        }
        vaciar();
        agregarLista(listaTemp);
        return true;
    }

    public boolean insertar(int indice, Object info){
        Nodo nodoInsertar = new Nodo(info); 
        if (indice < 0) {
            return false;
        } else if (indice == 0) {
            nodoInsertar.setEnlaceDer(apuntadorInicial);
            apuntadorInicial = nodoInsertar;
            return true;
        } else {
            Nodo temporal = apuntadorInicial;
            int contador = 0;
            while (temporal != null) {
                if (contador == indice - 1) {
                    Nodo siguiente = temporal.getEnlaceDer(); 
                    nodoInsertar.setEnlaceDer(siguiente); 
                    temporal.setEnlaceDer(nodoInsertar); 
                    return true;
                }
            contador = contador + 1;
            temporal = temporal.getEnlaceDer();
            }
            return false;
        } 
    }

    public boolean copiarLista(Object lista2){
        if (lista2 instanceof ListaInfoDinamica) {
            ListaInfoDinamica listaTemp = (ListaInfoDinamica) lista2;
            vaciar();
            agregarLista(listaTemp);
            return true; 
        }else{
            return false;
        }
    }

    public Object verFinal(){
        if (apuntadorFinal != null) {
            return apuntadorFinal.getDato();    
        } else {
            return null;
        }
        
    }

    public Object verInicial(){
        if (apuntadorInicial != null) {
            return apuntadorInicial.getDato();
        } else {
            return null;
        }
    }

    public void iniciarIterador(){
        iterador = apuntadorInicial;

    }

    public boolean iteradorNulo(){
        if(iterador == null){
            return true;
        }else{
            return false;
        }
    }

    public Object obtenDato(){
        if(iteradorNulo() == false){
            Object dato = iterador.getDato();
            iterador = iterador.getEnlaceDer();
            return dato;
        }else{
            return null;
        }
    }

    public int cantidad(){
        Nodo temporal = apuntadorInicial;
        int cantidad = 0;
        while (temporal != null) {
            cantidad = cantidad + 1;
            temporal = temporal.getEnlaceDer();
        }
        return cantidad;
    }

}
