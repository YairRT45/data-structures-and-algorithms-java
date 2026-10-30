package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ColaInfoDinamica;
import estructuraslineales.PilaInfoDinamica;
import estructuraslineales.PilaInfoEstatica;
import estructuraslineales.auxiliares.NodoDoble;
import utils.ComparadorObjetos;
import utils.comunes.TipoOrdenamiento;

public class ArbolBinarioMonticulo extends ArbolBinario{
    protected TipoOrdenamiento orden;

    public ArbolBinarioMonticulo(TipoOrdenamiento orden){
        raiz = null;
        this.orden = orden;
    }

    public void insertar(Object valor){
        if (raiz == null) {
            NodoDoble nodoRaiz = new NodoDoble(valor);
            raiz = nodoRaiz;
        } else {
            ColaInfoDinamica cola = new ColaInfoDinamica();
            ColaInfoDinamica recorrido = new ColaInfoDinamica(); //La cola almacenara los nodos que se recorren para llegar al recien insertado
            //1) agregar la raíz a la cola 
            cola.poner(raiz);
            //2) Mientras la cola no este vacia, sacar el primer elemento
            while (cola.vacio() == false) {
                NodoDoble nodo = (NodoDoble)cola.quitar();
                //3) Si los hijos no son nulos guardarlos en la cola, si alguno es nulo guardar ahí el elemento
                NodoDoble nuevoNodo = new NodoDoble(valor);  
                if (nodo.getEnlaceIzq() != null) {
                    cola.poner(nodo.getEnlaceIzq());
                } else {
                    nodo.setEnlaceIzq(nuevoNodo);
                    encontrarRecorrido(raiz, valor, recorrido);
                    // recorrido.mostrar();
                    recorrido.quitar(); //Quito el primer elemento que sería mi nodo actual para que no interfiera con el metodo cambiar
                    cambiar(recorrido, nuevoNodo);
                    return;
                }
                if (nodo.getEnlaceDer() != null) {
                    cola.poner(nodo.getEnlaceDer());
                } else {
                    nodo.setEnlaceDer(nuevoNodo);
                    encontrarRecorrido(raiz, valor, recorrido);
                    // recorrido.mostrar();
                    recorrido.quitar(); //Quito el primer elemento que sería mi nodo actual para que no interfiera con el metodo cambiar
                    cambiar(recorrido, nuevoNodo);
                    return;
                }
                //4) regresar al paso 2
            }
        }
    }

    private boolean encontrarRecorrido(NodoDoble subraiz, Object objetivo, ColaInfoDinamica recorrido){
        if (subraiz == null) {
            return false;
        } else {
            // consola(subraiz.toString() + " ");
            if (subraiz.getDato() == objetivo) {
                recorrido.poner(subraiz);
                return true;
            } else if (encontrarRecorrido(subraiz.getEnlaceIzq(), objetivo, recorrido) || encontrarRecorrido(subraiz.getEnlaceDer(), objetivo, recorrido)) {
                recorrido.poner(subraiz);
                return true;
            } else {
                return false;
            }
        }
    }

    private void cambiar(ColaInfoDinamica recorrido, NodoDoble actual){
        NodoDoble padre = (NodoDoble) recorrido.quitar();
        if (padre != null) {
            if (orden == TipoOrdenamiento.INC) {
                if (ComparadorObjetos.compararObjetos(actual.getDato(), padre.getDato()) > 0) { //Si el nodo insertado es mayor a su padre
                    Object temporal = padre.getDato();
                    padre.setDato(actual.getDato());
                    actual.setDato(temporal);
                    cambiar(recorrido, padre);
                } else {
                    return;
                }
            } else {
                if (ComparadorObjetos.compararObjetos(actual.getDato(), padre.getDato()) < 0) { //Si el nodo insertado es mayor a su padre
                    Object temporal = padre.getDato();
                    padre.setDato(actual.getDato());
                    actual.setDato(temporal);
                    cambiar(recorrido, padre);
                } else {
                    return;
                }
            }
        }
    }

    private void eliminarAuxiliar(){
        ColaInfoDinamica camino = new ColaInfoDinamica();
        encontrarRecorrido(raiz, encontrarUltimo().getDato(), camino);
        NodoDoble actual = (NodoDoble) camino.quitar();
        NodoDoble padre = (NodoDoble) camino.quitar();
        if(padre.getEnlaceDer() != null && ComparadorObjetos.compararObjetos(actual.getDato(), padre.getEnlaceDer().getDato()) == 0){
            padre.setEnlaceDer(null);
        }else if(ComparadorObjetos.compararObjetos(actual.getDato(), padre.getEnlaceIzq().getDato()) == 0){
            padre.setEnlaceIzq(null);
        }
        raiz.setDato(actual.getDato());

        if (orden == TipoOrdenamiento.INC) {
            NodoDoble hijoMayor = hijoValorMayor(raiz);
            if(ComparadorObjetos.compararObjetos(raiz.getDato(), hijoMayor.getDato()) < 0){
                Object temp = raiz.getDato();
                raiz.setDato(hijoMayor.getDato());
                hijoMayor.setDato(temp);
            }
        } else {
            NodoDoble hijoMenor = hijoValorMenor(raiz);
            if(ComparadorObjetos.compararObjetos(raiz.getDato(), hijoMenor.getDato()) > 0){
                Object temp = raiz.getDato();
                raiz.setDato(hijoMenor.getDato());
                hijoMenor.setDato(temp);
            }
        }

        
    } 

    private void eliminar(NodoDoble subraiz){
        if(subraiz != null){
            if (orden == TipoOrdenamiento.INC) {
                if(subraiz.getEnlaceDer() != null && ComparadorObjetos.compararObjetos(subraiz.getDato(), subraiz.getEnlaceDer().getDato()) < 0){
                    eliminarAuxiliar();
                }else if(subraiz.getEnlaceIzq() != null && ComparadorObjetos.compararObjetos(subraiz.getDato(), subraiz.getEnlaceIzq().getDato()) < 0){
                    eliminarAuxiliar();
                }else{
                    if(subraiz.getEnlaceIzq() != null){
                        eliminar(subraiz.getEnlaceIzq());
                    }
                    if(subraiz.getEnlaceDer() != null){
                        eliminar(subraiz.getEnlaceDer());
                    }
                }
            } else {
                if(subraiz.getEnlaceDer() != null && ComparadorObjetos.compararObjetos(subraiz.getDato(), subraiz.getEnlaceDer().getDato()) > 0){
                    eliminarAuxiliar();
                }else if(subraiz.getEnlaceIzq() != null && ComparadorObjetos.compararObjetos(subraiz.getDato(), subraiz.getEnlaceIzq().getDato()) > 0){
                    eliminarAuxiliar();
                }else{
                    if(subraiz.getEnlaceIzq() != null){
                        eliminar(subraiz.getEnlaceIzq());
                    }
                    if(subraiz.getEnlaceDer() != null){
                        eliminar(subraiz.getEnlaceDer());
                    }
                }
            }
        }
    }

    public void eliminar(){
        eliminarAuxiliar();
        eliminar(raiz);
    }

    private NodoDoble hijoValorMayor(NodoDoble subraiz){
        if(ComparadorObjetos.compararObjetos(subraiz.getEnlaceIzq().getDato(), subraiz.getEnlaceDer().getDato()) >= 0){
            return subraiz.getEnlaceIzq();
        }else{
            return subraiz.getEnlaceDer();
        }
    }

    private NodoDoble hijoValorMenor(NodoDoble subraiz){
        if(ComparadorObjetos.compararObjetos(subraiz.getEnlaceIzq().getDato(), subraiz.getEnlaceDer().getDato()) <= 0){
            return subraiz.getEnlaceIzq();
        }else{
            return subraiz.getEnlaceDer();
        }
    }

    private NodoDoble encontrarUltimo(){
        ColaInfoDinamica nodosPorVisitar =  new ColaInfoDinamica();
        PilaInfoDinamica pila = new PilaInfoDinamica();
        nodosPorVisitar.poner(raiz);
        while(nodosPorVisitar.vacio() == false){
            NodoDoble elemento = (NodoDoble) nodosPorVisitar.quitar();
            if(elemento != null){
                pila.poner(elemento);
                if(elemento.getEnlaceIzq() != null){
                    nodosPorVisitar.poner(elemento.getEnlaceIzq());
                }
                if(elemento.getEnlaceDer() != null){
                    nodosPorVisitar.poner(elemento.getEnlaceDer());
                }
            }
        }
        return (NodoDoble) pila.quitar();
    }
}
