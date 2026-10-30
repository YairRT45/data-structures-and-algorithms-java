package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.EntradaPorDefecto;
import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.ListaInfoDinamicaClave;
import estructuraslineales.PilaInfoEstatica;
import estructuraslineales.auxiliares.NodoDoble;
import utils.matematicas.ExpresionesAritmeticas;

public class ArbolExpresionesA extends ArbolBinario{
    protected ListaInfoDinamica operadores;
    protected ListaInfoDinamicaClave operandos;

    public void obtenerListas(){
        operadores = new ListaInfoDinamica();
        operandos = new ListaInfoDinamicaClave();
        obtenerListas(raiz, operadores, operandos);
        mostrarListas();
    }

    private ArbolExpresionesA generarArbolSinVariables(){
        ArbolExpresionesA nuevoArbol = new ArbolExpresionesA();
        nuevoArbol.raiz = clonarRaiz(raiz);
        reemplazarVariables(nuevoArbol.raiz, operandos);
        return nuevoArbol;
        // ArbolExpresionesA otroArbol = this;
        // reemplazarVariables(otroArbol.raiz, operandos);
        // return otroArbol;
    }

    public void mostrarArboles(){
        ArbolExpresionesA otroArbol = generarArbolSinVariables();
        consola("Arbol original:\n");
        preorden();
        consola("\n");
        innorden();
        consola("\n");
        postorden();
        consola("\n");
        consola("Arbol sin variables: \n");
        
        otroArbol.preorden();
        consola("\n");
        otroArbol.innorden();
        consola("\n");
        otroArbol.postorden();
        consola("\n");
    }

    private void reemplazarVariables(NodoDoble subraiz, ListaInfoDinamicaClave operandos){
        if (subraiz != null) {
            Object valor = subraiz.getDato();
            if (operandos.encontrar(valor) != null) {
                subraiz.setDato(operandos.encontrar(valor));
            }
            reemplazarVariables(subraiz.getEnlaceIzq(), operandos);
            reemplazarVariables(subraiz.getEnlaceDer(), operandos);
        }
    }

    private NodoDoble clonarRaiz(NodoDoble subraiz){
        if (subraiz != null) {
            NodoDoble nuevoNodo = new NodoDoble(subraiz.getDato());
            
            nuevoNodo.setEnlaceIzq(subraiz.getEnlaceIzq());
            nuevoNodo.setEnlaceDer(subraiz.getEnlaceDer());

            return nuevoNodo;
        } else {
            return null;
        }
    }

    private void obtenerListas(NodoDoble subraiz, ListaInfoDinamica operadores, ListaInfoDinamicaClave operandos){
        if (subraiz != null) {
            String valor = subraiz.getDato().toString();
            if (ExpresionesAritmeticas.esOperando(valor)) {
                if (ExpresionesAritmeticas.esVariable(valor)) {
                    consola("Ingresa el valor de: " + valor + ": ");
                    double respuesta = EntradaPorDefecto.consolaDouble();
                    operandos.insertar(valor, respuesta);
                } else {
                    double valorNumero = Double.parseDouble(valor);
                    operandos.insertar(valorNumero, valorNumero);
                }
            } else {
                operadores.insertar(valor);
            }
            obtenerListas(subraiz.getEnlaceIzq(), operadores, operandos);
            obtenerListas(subraiz.getEnlaceDer(), operadores, operandos);
        }
    }

    private void mostrarListas(){
        operandos.mostrar();
        operadores.mostrar();
        consola("\n");
    }
    
    public boolean nuevoArbolPostfija(String postfija){
        PilaInfoEstatica pila = new PilaInfoEstatica(postfija.length());

        for (int posToken = 0; posToken < postfija.length(); posToken++) {
            //2. tokenizar de izquierda a derecha ->
            char token = postfija.charAt(posToken);

            //3. Si es operando crear nodo con el valor del token, y meterlo en la pila
            if (utils.matematicas.ExpresionesAritmeticas.esOperando(token + "")) { //Verificar que pedo con el metodo esOperando ya que realmente son operadores en mi metodo
                NodoDoble nodoOperando = new NodoDoble(token + "");
                pila.poner(nodoOperando);
            } else {
                //4. Si es operador crear nodo con el valor del token y ligarlo con los 2 hijos
                // (sacando dos elementos de la pila donde el primero es op2); despues meterlo en la pila
                NodoDoble nodoOperador = new NodoDoble(token + "");
                NodoDoble op2 = (NodoDoble) pila.quitar();
                NodoDoble op1 = (NodoDoble) pila.quitar();

                if (op2 != null && op1 != null) {
                    nodoOperador.setEnlaceIzq(op1);
                    nodoOperador.setEnlaceDer(op2);
                } else {
                    return false;
                }
                pila.poner(nodoOperador);
            }
        }
        //5. La raíz esta en la pila
        raiz = (NodoDoble) pila.quitar();
        return true;
    }

    public boolean nuevoArbolInfija(String infija){
        //1. primero tendría que convertir a postfija o prefija
        // y luego llamar a los otros metodos de abajo o de arriba
        return true;
    }

    public boolean nuevoArbolPrefija(String prefija){
        return true;
    }


}
