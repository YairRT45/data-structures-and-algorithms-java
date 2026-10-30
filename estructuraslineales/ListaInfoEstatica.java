package estructuraslineales;

import entradasalida.SalidaPorDefecto;

//Define el TDA-ED Arreglo 
public class ListaInfoEstatica implements VectorListaInfo{
    protected Object arreglo[];
    protected int CAPACIDAD;
    protected int posFinal;

    public ListaInfoEstatica(int cuantos){
        CAPACIDAD = cuantos;
        arreglo = new Object[cuantos];
        posFinal = -1;
    }
    
    //Determina si un TDA-ED Arreglo no contiene nada
    public boolean estaVacia(){
        if (posFinal == -1) {
            return true;
        }else{
            return false;
        }
    }

    //Determina si un TDA-ED Arreglo tiene todos sus campos ocupados
    public boolean estaLleno(){
        if (posFinal == (CAPACIDAD - 1)) {
            return true;
        } else {
            return false;
        }
    }

    //Agrega un elemento en el ultimo espacio disponiblo del TDA-ED Arreglo
    public Integer insertar(Object valor){
        if (estaLleno() == false) { //Si hay espacio
            posFinal = posFinal + 1;
            arreglo[posFinal] = valor;
            return posFinal;
        } else { //No hay espacio
            return -1;
        }
    }

    public Integer insertarInicio(Object valor){
        return 0;
    }
    
    //Encuentra la posicion en el arreglo si es que se encuentra dentro
    public Object encontrar(Object valor){
        int posicionBusqueda = 0;
        while (posicionBusqueda <= posFinal && valor.toString().equalsIgnoreCase(arreglo[posicionBusqueda].toString()) == false) { //Verifico que mientras siga dentro del rango o no encuentre el objeto siga buscando
            posicionBusqueda = posicionBusqueda + 1; //Actualizamos el espacio donde se esta buscando
        }
        if (posicionBusqueda > posFinal) { //Esto es cuando ya estamos fuera del rango de busqueda
            return -1;
        } else { //Se encontro el objeto y se retorna la posicion
            return (Object)posicionBusqueda;
        }
    }

    //Encuentra primero el elemento ingresado y despues lo elimina del rango de busqueda del arreglo
    public Object borrar(Object valor){
        int posicion = (Integer)encontrar(valor);
        if (posicion >= 0) { //Lo encontramos
            Object respaldo = arreglo[posicion]; //Almacenamos aqui el dato que borramos
            posFinal = posFinal - 1;
            for(int posMod = posicion; posMod <= posFinal; posMod++){
                arreglo[posMod] = arreglo[posMod + 1];
            }
            return respaldo;
        } else { //No lo encontramos
            return null;
        }
    }

    public Object borrarInicio(){
        return borrar(0);
    }

    //Mostrara en consola todos los elementos del arreglo desde el ultimo hasta el primero
    public void mostrar(){
        for(int posIndice = posFinal; posIndice >= 0; posIndice--){
            SalidaPorDefecto.consola(arreglo[posIndice] + "\n");
        }
    }

    //Regresara el tamaño del arreglo
    public int capacidad(){
        return CAPACIDAD;
    }

    //Regresara la cantidad de elementos guardados en el arreglo
    public int cantidad(){
        int cantidad = posFinal + 1;
        return cantidad;
    }

    //Nos indicara si una lista es igual a otra
    public boolean esIgual(Object lista2){
        if (lista2 instanceof ListaInfoEstatica == true) {
            ListaInfoEstatica lista2Estatica = (ListaInfoEstatica) lista2;
            if (cantidad() == lista2Estatica.cantidad() == true) { //Verifica que las 2 listas tengan la misma cantidad de elementos
                for (int posComparar = 0; posComparar < posFinal; posComparar++) {
                    if (arreglo[posComparar].toString().equalsIgnoreCase(lista2Estatica.arreglo[posComparar].toString()) == false) {
                        return false; //Comparo elemento por elemento y si alguno es diferente inmediatamente regresa falso
                    }
                }
                return true; //Cuando termina el for y no regresa falso indica que todos los elementos comparados son iguales
            } else {
                return false; //Indica que las listas no son del mismo tamaño
            }
        } else {
            return false; //En caso de no ser una ListaInfoEstatica nos indicara
        }
    }

    //Nos regresara el objeto que se encuentre en la posicion del indice que ingresemos
    public Object obtener(int indice){
        if (validarIndice(indice)) {
            return arreglo[indice];
        } else {
            return null;
        }
    }

    public boolean validarIndice(int indice){
        if (indice >= 0 && indice <= posFinal) {
            return true;
        }else{
            return false;
        }
    }

    //Cambiara el valor que ingresemos por otro que nosotros elijamos y lo realizara hasta n veces queramos.
    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces){
        if (numVeces > cantidad() == false) { //Esto indica que si la cantidad de veces es mayor al numero de elementos no se podrá realizar
            for (int contador = 0; contador < numVeces; contador++) {
                int posAReemplazar = (Integer)encontrar(valorViejo);
                if ((posAReemplazar == -1) == true){
                    return false; //No encontro mas elementos para reemplazar
                } else {
                    arreglo[posAReemplazar] = valorNuevo.toString();
                }
            }
            return true; //Se logro cambiar los elementos
        } else {
            return false; //No se cambio ningun elemento
        }
    }

    //Cambiara el valor en la posicion que ingresemos con el valor que querramos
    public boolean cambiar(int indice, Object valor){
        if (validarIndice(indice) == true) {
            arreglo[indice] = valor;
            return true; //Se logro cambiar
        } else {
            return false; //No se cambio el elemento
        }
    }

    //Cambia los valores de un TDA-ED Arreglo por los de otra
    public boolean cambiarLista(ListaInfoEstatica indicesBusqueda, ListaInfoEstatica valoresNuevos){
        if ((indicesBusqueda.cantidad() == valoresNuevos.cantidad()) == true){ //Verifica que la lista de indices y de nuevos valores tengan el mismo tamaño
            for (int contador = 0; contador < indicesBusqueda.cantidad(); contador++) {
                cambiar((Integer)indicesBusqueda.obtener(contador), valoresNuevos.obtener(contador));
            } //Cuando un indice esta fuera de rango solamente ese no es modificado
            return true; //Se cambiaron todos
        } else {
            return false; //No se cambio ninguno
        }
    }

    //Guardara en un TDA-ED Arreglo todos los valores que sean iguales al que ingresamos
    public ListaInfoEstatica encontrarValores(Object valor){
        ListaInfoEstatica valoresEncontrados = new ListaInfoEstatica(CAPACIDAD);
        for (Object object : arreglo) {//R
            if (object == valor){
                valoresEncontrados.insertar(object);//Añade los elementos al compararlos uno por uno
            }
        }
        return valoresEncontrados;
    }

    //Eliminamos el elemento ubicado en la posicion que ingresemos
    public Object borrar(int indice){
        if (indice < 0 || indice >= cantidad() == false){
            Object respaldo = arreglo[indice]; //Almacenamos aqui el dato que borramos
            posFinal = posFinal - 1;
            for(int posMod = indice; posMod <= posFinal; posMod++){
                arreglo[posMod] = arreglo[posMod + 1];
            }
            return respaldo;
        } else {
            return null;
        }
    }

    //Borramos el ultimo elemento de la lista
    public Object borrar(){
        Object respaldo = borrar(posFinal);
        return respaldo;
    }

    //Eliminamos todo lo que hay en una lista
    public void vaciar(){
        arreglo = new Object[capacidad()];
        posFinal = -1;
    }

    //Agregamos el contenido de una lista en otra
    public boolean agregarLista(Object lista2){
        if (lista2 instanceof ListaInfoEstatica == true){ //Verificamos que sea una ListaInfoEstatica
            ListaInfoEstatica lista2Estatica = (ListaInfoEstatica) lista2;
            for (int contador = 0; contador < lista2Estatica.cantidad(); contador++) {
                insertar(lista2Estatica.obtener(contador)); //Insertamos los valores de la lista2 uno por uno
            }
            return true;
        } else {
            return false;
        }
    }

    //Invertimos el orden de la lista
    public void invertir(){
        int posicionInicio = 0;
        int posicionFinal = posFinal;
        while (posicionInicio < posicionFinal) { //Ejecutaremos hasta que se llegue al centro
            Object temporal = obtener(posicionInicio);
            cambiar(posicionInicio, obtener(posicionFinal));
            cambiar(posicionFinal, temporal);
            posicionFinal = posicionFinal - 1;
            posicionInicio = posicionInicio + 1;
        }
    }

    //Contamos la cantidad de veces que se repite un valor ingresado
    public int contar(Object valor){
        ListaInfoEstatica listaValores = encontrarValores(valor);
        int cantidadRepetida = listaValores.cantidad();
        return cantidadRepetida;
    }

    //Borramos los elementos de la lista 2 que esten en la lista original
    public boolean borrarLista(Object lista2){
        if (lista2 instanceof ListaInfoEstatica == true){ //Verificamos que sea una ListaInfoEstatica
            ListaInfoEstatica lista2Estatica = (ListaInfoEstatica) lista2;
            for (int contador = 0; contador < lista2Estatica.cantidad(); contador++) {
                Object elementoBorrar = lista2Estatica.obtener(contador); //Obtiene el elemento de la lista 2 y lo borra en la linea siguiente
                borrar(elementoBorrar);
            }
            return true; //Borro los elementos encontrados
        } else {
            return false; //No es una ListaInfoEstatica
        }
    }

    //Rellena la cantidad de espacios vacios que ingresemos con el dato que queramos
    public void rellenar(Object valor, int cantidad){
        if (cantidad > capacidad() == true){
            cantidad = capacidad(); //Si la cantidad ingresada es mayor que el arreglo se modifica al tamaño del mismo
        }
        for(int contador = 0; contador < cantidad; contador++){
            insertar(valor);
        }
    }

    //Hace una copia de la lista original
    public ListaInfoEstatica clonar(){
        return this;
    }

    //Creamos una sublista de una lista a partir de 2 indices 
    public ListaInfoEstatica subLista(int indiceInicial, int indiceFinal){
        int tamañoSubLista = (indiceFinal - indiceInicial) + 1;
        ListaInfoEstatica sublista = new ListaInfoEstatica(tamañoSubLista);
        if (indiceInicial > -1 && indiceFinal < cantidad() == true) { //Verificamos que los indices esten dentro
            for (int contador = indiceInicial; contador <= indiceFinal; contador++){
                sublista.insertar(obtener(contador)); //Agregamos los eleementos a la sublista
            }
        } return sublista; 
    }

    //Modifica el tamaño del TDA-ED Arreglo
    public boolean redimensionar(int maximo){
        if (maximo < 0) {
            return false; // Valor negativo
        }
        if (maximo < capacidad() == true) {
            if (maximo <= cantidad() == true){
                posFinal = maximo - 1;
            }
        } else {
            Object[] arregloTemporal = new Object[maximo];
            for (int contador = 0; contador < cantidad(); contador++){
                arregloTemporal[contador] = obtener(contador);
            }
            arreglo = arregloTemporal;
        }
        CAPACIDAD = maximo;
        return true;
    }

    public void rellenar(Object valor){ 
        
    }

    public ListaInfoEstatica arregloDesordenado(){  
        return null;
    }

    public boolean esSublista(ListaInfoEstaticaOrdenada lista2){
        return true;
    }

    public boolean cambiarLista(ListaInfoEstaticaOrdenada lista2, ListaInfoEstaticaOrdenada lista2Nuevos){
        return true;
    }

    public boolean retenerLista(ListaInfoEstaticaOrdenada lista2){
        return true;
    }

    public boolean insertar(int indice, Object info){//Inserta el valor en la posicion que indica el indice
        if (validarIndice(indice)) {
            for (int i = posFinal; i >= indice; i--) {
                arreglo[i + 1] = arreglo[i];
            }
            arreglo[indice] = info;
            posFinal++;
            return true;
        } else {
            return false; // indice no valido
        }
    }

    public boolean copiarLista(Object lista2){//Implementa los valores de la lista2 en la lista actual borrando los valores que la lista acutual tenia
        if (lista2 instanceof ListaInfoEstatica) {
            ListaInfoEstatica listaTemp = (ListaInfoEstatica) lista2;
            if (capacidad() < listaTemp.capacidad()) {
                return false; // El tamaño no suficiente
            }
            vaciar();
            agregarLista(listaTemp);
            return true; // Copia exitosa
        }else{
            return false;
        }
    }

    public ListaInfoEstatica subLista(ListaInfoEstaticaNumerica listaIndices){
        ListaInfoEstatica sublista = new ListaInfoEstatica(listaIndices.cantidad());
        for (int contador = 0; contador < listaIndices.cantidad(); contador++){
            double indiceBusqueda = (double)listaIndices.obtener(contador);
            Object elementoEncontrado = obtener((int)indiceBusqueda);
            sublista.insertar(elementoEncontrado);
        }
        return sublista;
    }

    //Convertiremos los valores guardados en un arreglo para agregarlos en nuestra ListaInfoEstatica
    public void convertirArray(Object[] buffer){
        for (int contador = 0; contador < buffer.length; contador++){
            insertar(buffer[contador]); //Recorremos todo el arreglo y guardamos todos los datos en la lista
        }
    }

    public double[] leerArreglo(){
        double[] respaldo = new double[capacidad()];
        for (int contador = 0; contador < respaldo.length; contador++) {
            respaldo[contador] = (double)obtener(contador);
        }

        // Object[] respaldo = arreglo;
        return respaldo;
    }

    public Object verFinal(){
        if (estaVacia() == false) {
            return arreglo[posFinal];
        } else {
            return null;
        }
    }

    public void mostrarOI(){}
}