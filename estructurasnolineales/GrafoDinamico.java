package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.PilaInfoDinamica;

import estructurasnolineales.auxiliares.Vertice;

public class GrafoDinamico {
    protected ListaInfoDinamica listaAdyacencia;

    public GrafoDinamico(){
        listaAdyacencia = new ListaInfoDinamica();
    }

    private ListaInfoDinamica encontrarSubListaVertice(Object valor){
        //Recorremos la lista roja(Las listas de adyacencia)
        listaAdyacencia.iniciarIterador(); //Crear metodos en listaDinamica
        
        while (listaAdyacencia.iteradorNulo() == false) {
            //Sacamos la lista de cada nodo
            ListaInfoDinamica subListaVertice = (ListaInfoDinamica)listaAdyacencia.obtenDato();
            //Obtenemos el primer elemento de la sublista
            Vertice verticeInicial = (Vertice)subListaVertice.verInicial();
            //Comparar este elemento inicial con el parametro

            if (valor.toString().equalsIgnoreCase(verticeInicial.getDato().toString())) {
                return subListaVertice; //Si encontro el vertice se va a salir con el return
            }
        }
        return null; //Nunca lo encuentra
    }

    public boolean agregarVertice(Object valor){
        ListaInfoDinamica subListaVerticeNuevo = encontrarSubListaVertice(valor);

        if (subListaVerticeNuevo != null) { //Ya existe
            return false;
        } else { //No existe
            Vertice verticeNuevo = new Vertice();
            verticeNuevo.setDato(valor);

            ListaInfoDinamica nuevaListaVertice = new ListaInfoDinamica();
            nuevaListaVertice.insertar(verticeNuevo);

            int retorno = listaAdyacencia.insertar(nuevaListaVertice); 
            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean agregarArista(Object origen, Object destino){
        ListaInfoDinamica subListaOrigen = encontrarSubListaVertice(origen);

        ListaInfoDinamica subListaDestino = encontrarSubListaVertice(destino);

        if (subListaOrigen != null && subListaDestino != null) {
            //Los 2 existen
            //Sacar la dirección de memoria del destino del primer elemento de su lista
            Vertice verticeDestino = (Vertice)subListaDestino.verInicial();

            int retorno = subListaOrigen.insertar(verticeDestino);

            if (retorno == -1) {
                return false;
            } else {
                return true;
            }
        } else { //Uno o los 2 no existen
            return false;
        }
    }

    public void mostrar(){
        //Recorremos la lista roja

        listaAdyacencia.iniciarIterador();

        while (listaAdyacencia.iteradorNulo() == false) {
            //Sacamos la lista de cada vertice en cada nodo
            ListaInfoDinamica subListaVertice = (ListaInfoDinamica)listaAdyacencia.obtenDato();

            subListaVertice.mostrar();
            consola("\n");
        }
    }

    //Recorrido en profundidad
    public ListaInfoDinamica recorridoProfundidad(Object origen){
        ListaInfoDinamica marcados = new ListaInfoDinamica();
        PilaInfoDinamica pila = new PilaInfoDinamica();
        ListaInfoDinamica salida = new ListaInfoDinamica();

        ListaInfoDinamica sublistaVerticeOrigen = encontrarSubListaVertice(origen);
        
        if (sublistaVerticeOrigen != null) { //Existe
            Vertice verticeOrigen = (Vertice) sublistaVerticeOrigen.verInicial();
            //1. Partir de un vértice origen, marcarlo y enpilarlo
            marcados.insertar(verticeOrigen);
            pila.poner(verticeOrigen);

            while (pila.vacio() == false) {
                //2. Mientras existan elementos en la pila, sacar uno y mandarlo a la salida (este vértice es el vértice actual)
                Vertice verticeActual = (Vertice)pila.quitar();
                salida.insertar(verticeActual.getDato());

                //3. Los vértices adyacentes al vértice actual (y que no esten marcados) se enpilan y se marcan
                marcarYEnpilarAdyacentes(verticeActual, marcados, pila);
            }
        } else { //No existe
            return null;
        }
        return salida;
    }

    //3. Los vértices adyacentes al vértice actual (y que no esten marcados) se enpilan y se marcan
    private void marcarYEnpilarAdyacentes(Vertice verticeActual, ListaInfoDinamica marcados, PilaInfoDinamica pila){

        ListaInfoDinamica subListaVerticeActual = encontrarSubListaVertice(verticeActual.getDato());

        //Recorrer las aristas de la sublista a partir del segundo elemento
        subListaVerticeActual.iniciarIterador();
        subListaVerticeActual.obtenDato(); //Me brinco el primero
        while (subListaVerticeActual.iteradorNulo() == false) {
            //Todos a partir del segundo
            Vertice verticeDestino = (Vertice)subListaVerticeActual.obtenDato();
            if (marcados.encontrar(verticeDestino) == null) { 
                //Si no esta en los marcados, no esta marcado
                marcados.insertar(verticeDestino);
                pila.poner(verticeDestino);
            }
        }
    }

    public Object eliminarVertice(Object valor){
        ListaInfoDinamica sublistaVerticeAEliminar = encontrarSubListaVertice(valor);

        if (sublistaVerticeAEliminar != null) {
            //Eliminar la sublista del vértice a eliminar
            listaAdyacencia.borrar(sublistaVerticeAEliminar);
            //Eliminar las aristas que apuntan al vértice a eliminar
            listaAdyacencia.iniciarIterador();
            while (listaAdyacencia.iteradorNulo() == false) {
                ListaInfoDinamica subListaVertice = (ListaInfoDinamica) listaAdyacencia.obtenDato();
                Vertice verticeDestino = (Vertice) subListaVertice.encontrar(valor);
                Vertice verticeOrigen = (Vertice) subListaVertice.verInicial();
                if (verticeDestino != null) {
                    eliminarArista(verticeOrigen.getDato(), valor);
                }
            }
            return valor;
        } else {
            return null;
        }
    }

    public boolean esAdyacente(Object origen, Object destino){
        ListaInfoDinamica sublistaVerticeOrigen = encontrarSubListaVertice(origen);

        if (sublistaVerticeOrigen.encontrar(destino) != null && sublistaVerticeOrigen.encontrar(destino) != sublistaVerticeOrigen.verInicial()) {
            //Si encuentra el destino en la sublista y no es el primer elemento entonces es adyacente
            return true;
        } else {
            //Si no se encuentra el destino en la lista no son adyacentes
            return false;
        }
    }

    public boolean eliminarArista(Object origen, Object destino){
        ListaInfoDinamica sublistaVerticeOrigen = encontrarSubListaVertice(origen); //Se obtiene la sublista del origen

        if (sublistaVerticeOrigen != null) {
            Vertice verticeDestino = (Vertice) sublistaVerticeOrigen.encontrar(destino); //Obtenemos el vertice destino
            if (verticeDestino != null) {
                sublistaVerticeOrigen.borrar(verticeDestino); 
                return true;
            } else { //No se encontro el destino
                return false;
            }
        } else { //No se encontro el origen
            return false;
        }
    }

    public Object buscarVertice(Object vertice){
        ListaInfoDinamica sublistaVerticeOrigen = encontrarSubListaVertice(vertice);

        if (sublistaVerticeOrigen != null) {
            return sublistaVerticeOrigen.verInicial();
        } else {
            return null;
        }
    }

    public boolean esPseudografo(){
        listaAdyacencia.iniciarIterador();

        while (listaAdyacencia.iteradorNulo() == false) {
            //Obtenemos cada uno de los vertices
            ListaInfoDinamica sublistaActual = (ListaInfoDinamica)listaAdyacencia.obtenDato();
            Vertice verticeActual = (Vertice)sublistaActual.verInicial();
            //Si existe bucle es pseudografo
            sublistaActual.iniciarIterador();
            sublistaActual.obtenDato(); //Brinco el primer elemento
            while (sublistaActual.iteradorNulo() == false) {
                Vertice verticeAdyacente = (Vertice) sublistaActual.obtenDato();
                if (verticeAdyacente != null) {
                    if (verticeActual.getDato().equals(verticeAdyacente.getDato())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean esMultigrafo(){
        listaAdyacencia.iniciarIterador();
        int contador = 0;
        while(listaAdyacencia.iteradorNulo() == false){
            ListaInfoDinamica subLista = (ListaInfoDinamica) listaAdyacencia.obtenDato();
            subLista.iniciarIterador();
            Vertice verticeInicio = (Vertice) subLista.obtenDato(); 
            // System.out.println(verticeInicio.getDato());
            while(subLista.iteradorNulo() == false){
                Vertice verticeDestino = (Vertice) subLista.obtenDato();
                if(esAdyacente(verticeDestino.getDato(), verticeInicio.getDato())){
                    return true;
                }
            }
            contador = contador + 1; 
            listaAdyacencia.iniciarIterador();
            for(int veces = 0; veces < contador; veces++){
                listaAdyacencia.obtenDato();
            }
        }
        return false;
    }

    public int gradoVertice(Object vertice) {
        ListaInfoDinamica sublistaVertice = encontrarSubListaVertice(vertice);

        sublistaVertice.iniciarIterador();
        sublistaVertice.obtenDato();
        int contador = 0;
        while (sublistaVertice.iteradorNulo() == false) { //Recorremos la sublista del vértice
            contador = contador + 1; //Se incrementa el contador
            sublistaVertice.obtenDato();
        }
        return contador;
    }

    public boolean hayRuta(Object origen, Object destino){
        ListaInfoDinamica visitados = new ListaInfoDinamica();
        return hayRuta(origen, destino, visitados);
    }

    private boolean hayRuta(Object origen, Object destino, ListaInfoDinamica visitados){
        ListaInfoDinamica sublistaOrigen = encontrarSubListaVertice(origen);
        if (sublistaOrigen != null) {

            if (origen.equals(destino)) { //Si el origen es igual al destino obviamos que hay ruta
                return true;
            }

            sublistaOrigen.iniciarIterador();
            sublistaOrigen.obtenDato(); //Brincamos el primer elemento

            visitados.insertar(origen); //Se inserta el origen en visitados, para que ya no vuelva a buscar ahí

            while (sublistaOrigen.iteradorNulo() == false) {
                Vertice verticeActual = (Vertice) sublistaOrigen.obtenDato(); //Iteramos sobre la sublista del origen

                if (visitados.encontrar(verticeActual.getDato()) == null) {
                    if (destino.equals(verticeActual.getDato())) { //Si el destino es igual al actual si hay ruta
                        return true;
                    } else if (hayRuta(verticeActual.getDato(), destino, visitados) == true) { //Buscamos recursivamente dando como origen el actual, si lo encuentra hay ruta desde la primer invocacion
                        return true;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean esConexo(){
        listaAdyacencia.iniciarIterador();
        ListaInfoDinamica todosLosVertices = new ListaInfoDinamica();

        while (listaAdyacencia.iteradorNulo() == false) { //Obtengo una lista con todos los vertices
            ListaInfoDinamica sublistaVerticeActual = (ListaInfoDinamica)listaAdyacencia.obtenDato();
            Vertice verticeActual = (Vertice)sublistaVerticeActual.verInicial();
            todosLosVertices.insertar(verticeActual.getDato());
        }
        boolean conexo = true;
        for (int verticeActual = 0; verticeActual < todosLosVertices.cantidad(); verticeActual++){
            ListaInfoDinamica recorridoActual = recorridoProfundidad(todosLosVertices.obtener(verticeActual));
            if (recorridoActual.cantidad() != todosLosVertices.cantidad()) {
                conexo = false;
            }
        }
        return conexo;
    }

    public boolean hayCaminoCerrado(Object origen){
        ListaInfoDinamica visitados = recorridoProfundidad(origen);
        visitados.iniciarIterador();
        visitados.obtenDato(); //Me brinco el origen

        while (visitados.iteradorNulo() == false) {
            if (hayRuta(visitados.obtenDato(), origen)) { //Verifico si puedo llegar al origen a partir de cualquier vertice que siga el recorrido
                return true; 
            }
        }
        return false;
    }

    public boolean esCaminoSimple(Object origen, Object destino){
        ListaInfoDinamica camino = recorridoProfundidad(origen); //Busca el camino que sigue el origen
        ListaInfoDinamica visitados = new ListaInfoDinamica(); //Lista para guardar los vertices que ya revise

        camino.iniciarIterador();
        while (camino.iteradorNulo() == false) {
            Object verticeActual = camino.obtenDato();
            if (visitados.encontrar(verticeActual) != null) { //Si me encuentro un vertice repetido, no es camino simple
                return true;
            }
            visitados.insertar(verticeActual);
        }
        return true;
    }

    //Por la naturaleza de la ED todas las aristas van dirigidas, por lo cual siempre será un grafo dirigido
    public boolean esDirigido(){
        return true;
    }

    public boolean esArbol(){
        ListaInfoDinamica subListaOrigen = (ListaInfoDinamica)listaAdyacencia.verInicial();
        Object origen = subListaOrigen.verInicial();
        if (esConexo() && hayCaminoCerrado(origen) == false && esPseudografo() == false) {
            return true;
        } else {
            return false;
        }
    }

    public void listarAristas(){
        listaAdyacencia.iniciarIterador();
        while (listaAdyacencia.iteradorNulo() == false) {
            ListaInfoDinamica sublistaVerticeActual = (ListaInfoDinamica)listaAdyacencia.obtenDato();

            sublistaVerticeActual.iniciarIterador();
            Vertice verticeInicio = (Vertice) sublistaVerticeActual.obtenDato();
            while (sublistaVerticeActual.iteradorNulo() == false) {
                Vertice verticeActual = (Vertice) sublistaVerticeActual.obtenDato();
                consola("(" + verticeInicio.getDato() + "," + verticeActual.getDato() + ",1) ");

            }
        }
    }

    public void listarAristas(Object vertice){
        ListaInfoDinamica sublistaVertice = encontrarSubListaVertice(vertice);

        sublistaVertice.iniciarIterador();
            Vertice verticeInicio = (Vertice) sublistaVertice.obtenDato();
            while (sublistaVertice.iteradorNulo() == false) {
                Vertice verticeActual = (Vertice) sublistaVertice.obtenDato();
                consola("(" + verticeInicio.getDato() + "," + verticeActual.getDato() + ",1) ");

            }
    }

    public void listarVertices(){
        listaAdyacencia.iniciarIterador();

        while (listaAdyacencia.iteradorNulo() == false) {
            ListaInfoDinamica sublistaVerticeActual = (ListaInfoDinamica)listaAdyacencia.obtenDato();
            Vertice verticeActual = (Vertice)sublistaVerticeActual.verInicial();
            consola(verticeActual.getDato() + " ");
        }
    }
}
