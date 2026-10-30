package estructuraslineales;

import utils.ComparadorObjetos;
import utils.comunes.TipoOrdenamiento;

public class ListaInfoEstaticaOrdenada extends ListaInfoEstatica{

    protected TipoOrdenamiento orden;

    public ListaInfoEstaticaOrdenada(int cuantos, TipoOrdenamiento orden) {
        super(cuantos);
        this.orden = orden;
    }

    @Override
    public Object encontrar(Object valor){
        int posBusqueda = 0;
        ComparadorObjetos comparador = new ComparadorObjetos();
       
        if (orden == TipoOrdenamiento.INC) {
            while (posBusqueda <= posFinal && comparador.compararObjetos(valor, arreglo[posBusqueda]) > 0) {
                posBusqueda = posBusqueda + 1;
            }
            if (posBusqueda > posFinal || comparador.compararObjetos(valor, arreglo[posBusqueda]) < 0) {
                return (posBusqueda + 1) * -1;
            } else {
                return posBusqueda + 1;
            }
        } else {
            while (posBusqueda <= posFinal && comparador.compararObjetos(valor, arreglo[posBusqueda]) < 0) {
                posBusqueda = posBusqueda + 1;
            }
            if (posBusqueda > posFinal || comparador.compararObjetos(valor, arreglo[posBusqueda]) > 0) {
                return (posBusqueda + 1) * -1;
            } else {
                return posBusqueda + 1;
            }
        }
    }

    @Override
    public Integer insertar(Object valor){
        int posicion = (int) encontrar(valor);
        if (posicion < 0) {
            posFinal = posFinal + 1;
            posicion = posicion * (-1);
            posicion = posicion - 1;
            for(int indiceMov = posFinal; indiceMov >= posicion+1; indiceMov--){
                arreglo[indiceMov] = arreglo[indiceMov-1];
            }
            arreglo[posicion] = valor;
            return posicion;
        }else if(estaLleno()){
            return -1;
        }else{
            return -1;
        }
        
        
    }

    @Override
    public Object borrar(Object valor){
        int posicion = (int)encontrar(valor);
        if (posicion > 0) {
            posicion = posicion - 1;
            posFinal = posFinal - 1;
            Object respaldo = arreglo[posicion];
            for (int indiceMod = posicion; indiceMod <= posFinal; indiceMod++){
                arreglo[indiceMod] = arreglo[indiceMod + 1];
            }
            return respaldo;
        }else{
            return null;
        }
    }

    @Override
    public boolean cambiar(int indice, Object valor){
        if (validarIndice(indice) == true){
            Object valorReemplazar = obtener(indice);
            borrar(valorReemplazar);
            insertar(valor);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean agregarLista(Object lista2){
        if(lista2 instanceof ListaInfoEstaticaOrdenada == true){
            ListaInfoEstaticaOrdenada lista2Estatica = (ListaInfoEstaticaOrdenada) lista2;
            for (int indice = 0; indice < lista2Estatica.cantidad(); indice++){
                insertar(lista2Estatica.obtener(indice));   
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void invertir(){
        int posicionInicio = 0;
        int posicionFinal = posFinal;

        while (posicionInicio < posicionFinal) { //Ejecutaremos hasta que se llegue al centro
            Object temporal = obtener(posicionInicio);
            arreglo[posicionInicio] = arreglo[posicionFinal];
            arreglo[posicionFinal] = temporal;

            posicionFinal = posicionFinal - 1;
            posicionInicio = posicionInicio + 1;
        }

        if (orden == TipoOrdenamiento.INC) {
            orden = TipoOrdenamiento.DEC;
        }else{
            orden = TipoOrdenamiento.INC;
        }
    }

    @Override
    public void rellenar(Object valor){
        vaciar();
        int rangoDescarte;
        int rango;
        if (valor instanceof Character) {
            char charValor = (char) valor;
            char charMayuscula = Character.toUpperCase(charValor);
            valor = charMayuscula;
            rangoDescarte = (int)charMayuscula;
            rango = rangoDescarte - 65;

            if(rango < capacidad() && orden == TipoOrdenamiento.DEC) {
                for(int contador = 0; contador <= rango; contador++) {
                    char caracter = (char) (charMayuscula - contador); 
                    insertar(caracter);
                }
            } else if(rango < capacidad() && orden == TipoOrdenamiento.INC) {
                for(int contador = 0; contador <= rango; contador++) {
                    char caracter = (char) (charMayuscula - contador);
                    insertar(caracter);
                }
                for (int contador = 0; contador < (cantidad() / 2); contador++) {
                    Object temporal = arreglo[contador];
                    arreglo[contador] = arreglo[cantidad() - (contador - 1)];
                    arreglo[cantidad() - (contador - 1)] = temporal;
                }
            }
        }else if(valor instanceof Integer){
            if((int) valor < 0){
                rango =((int) valor) * (-1);
                if(rango <= capacidad() && orden == TipoOrdenamiento.INC){
                    for(int contador = 0; contador < rango; contador++){
                        insertar(((int) valor) + contador);
                    }
                }else if(rango <= capacidad() && orden == TipoOrdenamiento.DEC){
                    for(int contador = 0; contador <= (rango - 1); contador++){
                        insertar(((int) valor) - (contador + ((int) valor) + 1));
                    }
                }
            }else{
                rango = (int) valor;
                if(rango <= capacidad() && orden == TipoOrdenamiento.INC){
                    for(int contador = 0; contador < rango; contador++){
                        insertar(((int) valor) + (contador + 1 - ((int) valor)));
                    }
                }else if(rango <= capacidad() && orden == TipoOrdenamiento.DEC){
                    for(int contador = 0; contador <= (rango - 1); contador++){
                        insertar(((int) valor) - contador);
                    }
                }
            }
        }else if(valor instanceof String){
            insertar(valor);
        }else{
            insertar(valor);
        }
    }

    @Override
    public ListaInfoEstatica arregloDesordenado(){
        ListaInfoEstatica listaDesordenada = clonar();
        for(int IndicesTotales = listaDesordenada.cantidad(); IndicesTotales > 0; IndicesTotales--){
            int indiceNuevo = (int) (Math.random() * IndicesTotales);
            Object temporal = listaDesordenada.arreglo[IndicesTotales - 1];
            listaDesordenada.arreglo[IndicesTotales - 1] = listaDesordenada.arreglo[indiceNuevo];
            listaDesordenada.arreglo[indiceNuevo] = temporal;
        }
        return listaDesordenada;
    }

    //
    @Override
    public boolean esSublista(ListaInfoEstaticaOrdenada lista2){
        int indLista1 = 0;
        int indLista2 = 0;
        ComparadorObjetos comparador = new ComparadorObjetos();

        while (indLista1 <= posFinal && indLista2 <= lista2.posFinal) {
            Object valorLista1 = arreglo[indLista1];
            Object valorLista2 = lista2.arreglo[indLista2];

            int comparacion = comparador.compararObjetos(valorLista1, valorLista2);

            if (comparacion == 0) {
                indLista1 = indLista1 +1;
                indLista2 = indLista2 +1;
            } else if (comparacion < 0) {
                indLista1 = indLista1 +1;
            } else {
                indLista2 = 0;
                indLista1 = indLista1 +1;
            }

            if (indLista2 > lista2.posFinal) {
                return true; // los datos de la lista 2 son los mismos.
            }
        } 
        return false;
    }

    //
    @Override
    public boolean cambiarLista(ListaInfoEstaticaOrdenada lista2, ListaInfoEstaticaOrdenada lista2Nuevos){
        int indlista2 = 0;
        int indLista2Nuevos = 0;
        if ((lista2.estaVacia() == false && estaVacia() == false)) {    
            while (indlista2 <= lista2.cantidad() && indLista2Nuevos <= lista2Nuevos.cantidad()) {
                Object valorLista2 = lista2.obtener(indlista2);
                Object valorLista2Nueva = lista2Nuevos.obtener(indLista2Nuevos);
                int IndiceEncontradoPositivo = (Integer) encontrar(valorLista2);

                if (IndiceEncontradoPositivo > 0) {
                    lista2.cambiar(indlista2, valorLista2Nueva);
                    indLista2Nuevos = indLista2Nuevos + 1;
                }     
            indlista2 = indlista2 +1;
            }
            return true;
        }else{
            return false; 
        }
    }

    @Override
    public boolean retenerLista(ListaInfoEstaticaOrdenada lista2){
        ListaInfoEstaticaOrdenada listaTemp = new ListaInfoEstaticaOrdenada(capacidad(), orden);
        ComparadorObjetos comparador = new ComparadorObjetos();
        int indLista1 = 0;
        int indLista2 = 0;

        // Itera sobre los elementos de ambas listas
        while (indLista1 <= posFinal && indLista2 <= lista2.posFinal) {
            Object elementoLista1 = arreglo[indLista1];
            Object elementoLista2 = lista2.arreglo[indLista2];
            int comparacion = comparador.compararObjetos(elementoLista1, elementoLista2);

            if (comparacion == 0) {
                // Elemento encontrado en ambas listas, agrégalo a la lista temporal
                listaTemp.insertar(elementoLista1);
                indLista1++;
                indLista2++;
            } else if (comparacion < 0) {
                // El elemento en lista1 es menor, avanza en lista1
                indLista1++;
            } else {
                // El elemento en lista2 es menor, avanza en lista2
                indLista2++;
            }
        }

        // Vacía la lista actual
        vaciar();

        // Agrega los elementos retenidos de la lista temporal a la lista actual
        agregarLista(listaTemp);

        return true;
    }

    @Override
    public boolean insertar(int indice, Object info){
        if (validarIndice(indice)) {
            int posicion = (Integer) encontrar(info);
            if (posicion < 0) {
                posicion = (posicion * -1) - 1;
                if(posicion == indice){
                    insertar(info);
                    return true;
                }else{
                    return false; // Indice no valido
                }
            }else{
                return false;
            }
        } else {
            return false; // Indice no valido
        }
    }

    @Override
    public boolean copiarLista(Object lista2){
        if (lista2 instanceof ListaInfoEstaticaOrdenada) {
            ListaInfoEstaticaOrdenada listaTemp = (ListaInfoEstaticaOrdenada) lista2;
    
            if (orden == listaTemp.orden) {
                if (capacidad() < listaTemp.capacidad()) {
                    return false; // El tamaño no es suficiente
                }
                vaciar();
                agregarLista(listaTemp);
    
                return true; // Copia exitosa
            } else {
                return false; // Los ordenes no coinciden
            }
        } else {
            return false; // Si la lista2 no es del mismo tipo
        }
    }

}
