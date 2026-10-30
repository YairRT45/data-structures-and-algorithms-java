package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructuraslineales.ColaInfoDinamica;
import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.PilaInfoDinamica;
import estructuraslineales.auxiliares.NodoDoble;

// Esta clase crea un TDA ArbolBinario manualmente por el usuario

public class ArbolBinario {
    protected NodoDoble raiz; //Por defecto ya es nulo
    
    public ArbolBinario (){
        raiz = null;
    }

    public void nuevoArbol(){
        consola("Dame la raíz: ");
        String valorRaiz = EntradaPorDefecto.consolaCadenas();
        NodoDoble nodoRaiz = new NodoDoble(valorRaiz);
        raiz = nodoRaiz;
        if (nodoRaiz != null) { //no hubo error
            nuevoArbol(raiz);
        }
    }

    //Metodo recursivo
    private void nuevoArbol(NodoDoble subRaiz){
        //Preguntar si hay hijo izquierdo
        consola("¿El nodo " + subRaiz.getDato() + " tiene hijo izquierdo? [S/N]: ");
        String respuestaHI = EntradaPorDefecto.consolaCadenas();
        if (respuestaHI.equalsIgnoreCase("s")) {
            //Creamos un hijo izquierdo
            consola("Dame el valor del hijo izquierdo de " + subRaiz.getDato() + ": ");
            String valorHI = EntradaPorDefecto.consolaCadenas();
            NodoDoble hijoIzquierdo = new NodoDoble(valorHI);
            if (hijoIzquierdo != null) {
                subRaiz.setEnlaceIzq(hijoIzquierdo);
                nuevoArbol(subRaiz.getEnlaceIzq()); //Crearemos un arbol con esta subraiz como base
            }
        }

        //Preguntar si hay hijo derecho
        consola("¿El nodo " + subRaiz.getDato() + " tiene hijo derecho? [S/N]: ");
        String respuestaHD = EntradaPorDefecto.consolaCadenas();
        if (respuestaHD.equalsIgnoreCase("s")) {
            //Creamos un hijo derecho
            consola("Dame el valor del hijo derecho de " + subRaiz.getDato() + ": ");
            String valorHD = EntradaPorDefecto.consolaCadenas();
            NodoDoble hijoDerecho = new NodoDoble(valorHD);
            if (hijoDerecho != null) {
                subRaiz.setEnlaceDer(hijoDerecho);
                nuevoArbol(subRaiz.getEnlaceDer()); //Crearemos un arbol con esta subraiz como base
            }
        }
    }

    public void preorden(){
        //RID
        preorden(raiz);
    }

    private void preorden(NodoDoble subRaiz){
        //RID
        if (subRaiz != null) {
            consola(subRaiz.getDato() + " ");
            preorden(subRaiz.getEnlaceIzq());
            preorden(subRaiz.getEnlaceDer());    
        } //El caso base es cuando subRaiz es null
    }

    public void postorden(){
        //IDR
        postorden(raiz);
    }

    private void postorden(NodoDoble subRaiz){
        //IDR
        if (subRaiz != null) {
            postorden(subRaiz.getEnlaceIzq());
            postorden(subRaiz.getEnlaceDer());
            consola(subRaiz.getDato() + " ");
        } //El caso base es cuando subRaiz es null
    }

    public void innorden(){
        //IRD
        innorden(raiz);
    }

    private void innorden(NodoDoble subRaiz){
        //IRD
        if (subRaiz != null) {
            innorden(subRaiz.getEnlaceIzq());
            consola(subRaiz.getDato() + " ");
            innorden(subRaiz.getEnlaceDer());
        } //El caso base es cuando subRaiz es null
    }

    public int obtenerAltura(){
        return obtenerAltura(raiz);
    }

    private int obtenerAltura(NodoDoble subraiz){
        if(subraiz != null){
            int alturaIzq = obtenerAltura(subraiz.getEnlaceIzq());
            int alturaDer = obtenerAltura(subraiz.getEnlaceDer());

            if(alturaIzq > alturaDer){
                return alturaIzq + 1;
            }else{
                return alturaDer + 1;
            }
        }else{
            return 0;
        }
    }

    public int encontrarNivel(Object valor){
        return encontrarNivel(raiz, valor, 0);
    }
    
    private int encontrarNivel(NodoDoble subraiz, Object valor, int nivelActual){
        if (subraiz != null) {
            if (subraiz.getDato().equals(valor)) {
                return nivelActual;
            } else {
                int nivelIzq = encontrarNivel(subraiz.getEnlaceIzq(), valor, nivelActual + 1);
                int nivelDer = encontrarNivel(subraiz.getEnlaceDer(), valor, nivelActual + 1);

                if (nivelIzq > nivelDer) {
                    return nivelIzq;
                } else {
                    return nivelDer;
                }
            }
        } else {
            return 0;
        }
    }

    public ListaInfoEstatica contarElementosPorNivel(){
        ListaInfoEstatica contador = new ListaInfoEstatica(obtenerAltura());
        contador.rellenar(0, obtenerAltura());
        contarElementosPorNivel(raiz, 0, contador);
        return contador;
    }

    private void contarElementosPorNivel(NodoDoble subraiz, int nivel, ListaInfoEstatica contador){
        if (subraiz != null) {
            int cantidadNivel = (int)contador.obtener(nivel);
            contador.cambiar(nivel, cantidadNivel + 1);
            contarElementosPorNivel(subraiz.getEnlaceIzq(), nivel + 1, contador);
            contarElementosPorNivel(subraiz.getEnlaceDer(), nivel + 1, contador);
        }
    }

    public void tipoNodo(Object valor){
        consola(tipoNodo(raiz, null, valor));
    }

    private String tipoNodo(NodoDoble subraiz, NodoDoble padre, Object valor){
        if (subraiz != null) {
            if (valor.equals(subraiz.getDato())) {
                if (padre == null) {
                    return "El nodo " + subraiz.getDato() + " es raíz y no tiene padre";
                } else if (subraiz.getEnlaceIzq() == null && subraiz.getEnlaceDer() == null) {
                    return "El nodo " + subraiz.getDato() + " es hoja y su padre es " + padre.getDato();
                } else {
                    return "El nodo " + subraiz.getDato() + " es intermedio y su padre es: " + padre.getDato();
                }
            }
            String tipoIzq = tipoNodo(subraiz.getEnlaceIzq(), subraiz, valor);
            String tipoDer = tipoNodo(subraiz.getEnlaceDer(), subraiz, valor);

            return tipoIzq.equals("No encontrado") ? tipoDer:tipoIzq;
        } else {
            return "No encontrado";
        }
    }

    public boolean tieneHermano(Object valor){
        return tieneHermano(raiz,valor,null);
    }

    private boolean tieneHermano(NodoDoble subRaiz, Object valor, NodoDoble padre){
        if(subRaiz != null){
            if(subRaiz.getDato().toString().equalsIgnoreCase(valor.toString())){
                if(padre.getEnlaceDer() != null && padre.getEnlaceIzq() != null){
                    return true;
                }else{
                    return false;
                }
            }

            boolean nodoIzq = tieneHermano(subRaiz.getEnlaceIzq(), valor, subRaiz);
            boolean nodoDer = tieneHermano(subRaiz.getEnlaceDer(), valor, subRaiz);
            if(nodoIzq || nodoDer){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void postordenNoRecursivo(){
        if (raiz != null) {
            PilaInfoDinamica pila = new PilaInfoDinamica(); //Usada para recorrer los nodos
            PilaInfoDinamica pila2 = new PilaInfoDinamica(); //Usada para guardar los nodos en postorden pero al reves

            pila.poner(raiz); //Se agrega la raíz porque en postorden siempre va al final

            while (pila.vacio() == false) { //Ciclo para recorrer los demas nodos
                NodoDoble actual = (NodoDoble)pila.quitar(); //Se obtiene el nodo y se agrega a la pila en postorden
                pila2.poner(actual); 

                //Si hay hijo izquierdo se agrega a la pila que se usa para recorrer
                if (actual.getEnlaceIzq() != null) {
                    pila.poner(actual.getEnlaceIzq());
                }
                //Si hay hijo derecho se agrega a la pila para recorrer
                if (actual.getEnlaceDer() != null) {
                    pila.poner(actual.getEnlaceDer());
                }
            }
            
            //Ciclo para mostrar el arbol en postorden
            while (pila2.vacio() == false) {
                NodoDoble nodo = (NodoDoble) pila2.quitar();
                consola(nodo.getDato() + " ");
            }
        }
    }

    public void mostrarPorAnchura(){
        ColaInfoDinamica cola = new ColaInfoDinamica();
         
        //1) agregar la raíz a la cola 
        cola.poner(raiz);

        //2) Mientras la cola no este vacia, sacar el primer elemento
        while (cola.vacio() == false) {
            NodoDoble nodo = (NodoDoble)cola.quitar();

            //3) Imprimir el nodo acual y guardar sus hijos en la cola
            consola(nodo.getDato() + " ");

            if (nodo.getEnlaceIzq() != null) {
                cola.poner(nodo.getEnlaceIzq());
            }
            if (nodo.getEnlaceDer() != null) {
                cola.poner(nodo.getEnlaceDer());
            }
            //4) regresar al paso 2
        }
        //5) terminamos
    }

    public void anchuraConPila(){
        PilaInfoDinamica pila = new PilaInfoDinamica();
         
        //1) agregar la raíz a la pila 
        pila.poner(raiz);

        //2) Mientras la pila no este vacia, sacar el primer elemento
        while (pila.vacio() == false) {
            // NodoDoble finall = (NodoDoble)pila.verFinal();
            // consola(finall.getDato() + " ");
            NodoDoble nodo = (NodoDoble)pila.quitar();

            
            //3) Imprimir el nodo acual y guardar sus hijos en la pila
            consola(nodo.getDato() + " ");
            
            if (nodo.getEnlaceIzq() != null) {
                pila.poner(nodo.getEnlaceIzq());
            }
            if (nodo.getEnlaceDer() != null) {
                pila.poner(nodo.getEnlaceDer());
            }
            //4) regresar al paso 2
        }
        //5) terminamos
    }
}
