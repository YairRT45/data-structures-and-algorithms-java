package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import estructuraslineales.auxiliares.NodoDoble;
import utils.ComparadorObjetos;

public class ArbolBinarioBusqueda extends ArbolBinario{
    
    public boolean insertar(Object valor){
        if (raiz == null) { //El arbol esta vacio
            NodoDoble nodoRaiz = new NodoDoble(valor);
            if (nodoRaiz == null) {
                return false;        
            }
            raiz = nodoRaiz;
            return true;
        } else { //Ya hay elementos
            insertar(raiz, valor);
            return true;
        }
    }

    private boolean insertar(NodoDoble subRaiz, Object valor){
        if (ComparadorObjetos.compararObjetos(valor, subRaiz.getDato()) > 0) {
            //Valor es mas grande que subraiz
            if (subRaiz.getEnlaceDer() == null) { //Encontre su lugar
                NodoDoble nodoHijo = new NodoDoble(valor);
                if (nodoHijo == null) {
                    return false;
                }
                subRaiz.setEnlaceDer(nodoHijo);
                return true;
            } else { //Sigo buscando
                return insertar(subRaiz.getEnlaceDer(), valor);
            }
        } else if (ComparadorObjetos.compararObjetos(valor, subRaiz.getDato()) < 0) {
            //Valor es mas chico que subraiz
            if (subRaiz.getEnlaceIzq() == null) { //Encontre su lugar
                NodoDoble nodoHijo = new NodoDoble(valor);
                if (nodoHijo == null) {
                    return false;
                }
                subRaiz.setEnlaceIzq(nodoHijo);
                return true;
            } else { //Sigo buscando
                return insertar(subRaiz.getEnlaceIzq(), valor);
            }
        } else { //Son iguales y no se inserta
            return false;
        }
    }

    public Object buscar(Object valor){
        return buscar(raiz, valor);
    }

    private Object buscar(NodoDoble subRaiz, Object valor){
        if (subRaiz == null) {
            return null;
        } else {
            if (ComparadorObjetos.compararObjetos(valor, subRaiz.getDato()) > 0) {
                //Valor es mas grande que subraiz
                return buscar(subRaiz.getEnlaceDer(), valor);
            } else if (ComparadorObjetos.compararObjetos(valor, subRaiz.getDato()) < 0) {
                //Valor es mas grande que subraiz
                return buscar(subRaiz.getEnlaceIzq(), valor);
            } else { //Si es igual lo encontre
                return subRaiz.getDato();
            }
        }
    }

    public void eliminar(Object valor){
        eliminar(raiz, null, valor);
    }

    private void eliminar(NodoDoble subraiz, NodoDoble anterior, Object valor){
        if (subraiz != null) {
            if (ComparadorObjetos.compararObjetos(valor, subraiz.getDato()) < 0) {
                eliminar(subraiz.getEnlaceIzq(), subraiz, valor);
            } else {
                if (ComparadorObjetos.compararObjetos(valor, subraiz.getDato()) > 0) {
                    eliminar(subraiz.getEnlaceDer(), subraiz, valor);
                } else { //Es el nodo que deseo eliminar
                    if (subraiz.getEnlaceIzq() != null && subraiz.getEnlaceDer() != null) { 
                        //Tiene 2 hijos, caso 3, saco el mas a la derecha del subarbol izquierdo
                        NodoDoble aux = subraiz.getEnlaceIzq();
                        NodoDoble aux1 = null;
                        boolean bool = false;

                        while (aux.getEnlaceDer() != null) {
                            aux1 = aux;
                            aux = aux.getEnlaceDer();
                            bool = true;
                        }

                        subraiz.setDato(aux.getDato());

                        if (bool == true) {
                            aux1.setEnlaceDer(aux.getEnlaceIzq());
                        } else {
                            subraiz.setEnlaceIzq(aux.getEnlaceIzq());
                        }
                    } else {//El derecho, izquierdo o ambos son nulos
                        NodoDoble otro = subraiz;
                        if (otro.getEnlaceDer() == null) {
                            if (otro.getEnlaceIzq() != null) { //Solo el hijo derecho es nulo
                                otro = subraiz.getEnlaceIzq();
                                if (anterior != null) { //No es la raíz
                                    if (ComparadorObjetos.compararObjetos(valor, anterior.getDato()) < 0) {
                                        anterior.setEnlaceIzq(otro);
                                    } else {
                                        anterior.setEnlaceDer(otro);
                                    }
                                } else {
                                    raiz = otro;
                                }
                            } else { //ambos son nulos
                                if (anterior == null) { //Solo la raíz
                                    raiz = null;
                                } else {
                                    if (ComparadorObjetos.compararObjetos(valor, anterior.getDato()) < 0) {
                                        anterior.setEnlaceIzq(null);
                                    } else {
                                        anterior.setEnlaceDer(null);
                                    }
                                }
                            } 
                        } else { //La derecha no es nula
                            if (otro.getEnlaceIzq() == null) { //Solo el izquierdo es nulo
                                otro = subraiz.getEnlaceDer();
                                if (anterior != null) {
                                    if (ComparadorObjetos.compararObjetos(valor, anterior.getDato()) < 0) {
                                        anterior.setEnlaceIzq(otro);
                                    } else {
                                        anterior.setEnlaceDer(otro);
                                    }
                                } else {
                                    raiz = otro;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            consola("El dato no está en el árbol\n");
        }
    }

}
