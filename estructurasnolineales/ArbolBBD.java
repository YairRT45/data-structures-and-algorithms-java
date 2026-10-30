package estructurasnolineales;

import static entradasalida.SalidaPorDefecto.consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructuraslineales.PilaInfoDinamica;
import estructuraslineales.auxiliares.NodoDoble;
import estructurasnolineales.auxiliares.NodoArbolBusqueda;
import utils.ComparadorObjetos;

public class ArbolBBD {
    protected NodoArbolBusqueda raiz;
    protected String ruta;

    public ArbolBBD(String ruta){
        raiz = null;
        this.ruta = ruta;
    }
    
    public boolean insertar(int indice, long direccion){
        if (raiz == null) { //El arbol esta vacio
            NodoArbolBusqueda nodoRaiz = new NodoArbolBusqueda(indice, direccion);
            if (nodoRaiz == null) {
                return false;        
            }
            raiz = nodoRaiz;
            return true;
        } else { //Ya hay elementos
            insertar(raiz, indice, direccion);
            return true;
        }
    }

    private boolean insertar(NodoArbolBusqueda subRaiz, int indice, long direccion){
        if (ComparadorObjetos.compararObjetos(indice, subRaiz.getIndice()) > 0) {
            //Valor es mas grande que subraiz
            if (subRaiz.getEnlaceDer() == null) { //Encontre su lugar
                NodoArbolBusqueda nodoHijo = new NodoArbolBusqueda(indice, direccion);
                if (nodoHijo == null) {
                    return false;
                }
                subRaiz.setEnlaceDer(nodoHijo);
                return true;
            } else { //Sigo buscando
                return insertar(subRaiz.getEnlaceDer(), indice, direccion);
            }
        } else if (ComparadorObjetos.compararObjetos(indice, subRaiz.getIndice()) < 0) {
            //Valor es mas chico que subraiz
            if (subRaiz.getEnlaceIzq() == null) { //Encontre su lugar
                NodoArbolBusqueda nodoHijo = new NodoArbolBusqueda(indice, direccion);
                if (nodoHijo == null) {
                    return false;
                }
                subRaiz.setEnlaceIzq(nodoHijo);
                return true;
            } else { //Sigo buscando
                return insertar(subRaiz.getEnlaceIzq(), indice, direccion);
            }
        } else { //Son iguales y no se inserta
            return false;
        }
    }

    public long buscar(int indice){
        return buscar(raiz, indice);
    }

    private long buscar(NodoArbolBusqueda subRaiz, int indice){
        if (subRaiz == null) {
            return 0;
        } else {
            if (ComparadorObjetos.compararObjetos(indice, subRaiz.getIndice()) > 0) {
                //Valor es mas grande que subraiz
                return buscar(subRaiz.getEnlaceDer(), indice);
            } else if (ComparadorObjetos.compararObjetos(indice, subRaiz.getIndice()) < 0) {
                //Valor es mas grande que subraiz
                return buscar(subRaiz.getEnlaceIzq(), indice);
            } else { //Si es igual lo encontre
                return subRaiz.getDireccion();
            }
        }
    }

    public Object encontrarUltimo(){
        return encontrarUltimo(raiz, new PilaInfoDinamica());
    }

    private Object encontrarUltimo(NodoArbolBusqueda subRaiz, PilaInfoDinamica pila) {
        if (subRaiz != null) {
            encontrarUltimo(subRaiz.getEnlaceIzq(), pila);
            pila.poner(subRaiz.getIndice());
            encontrarUltimo(subRaiz.getEnlaceDer(), pila);
        } 
        if (!pila.vacio()) {
            return pila.verFinal(); // Devuelve el último elemento sin quitarlo
        } else {
            return null; // O devuelve un valor nulo en caso de una pila vacía
        }
        
    }

    public void vaciar(){
        raiz = null;
    }

    public void agregarTabla() throws IOException {
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile(ruta, "r");
            String cad = "";
            boolean primeraLinea = true; // Agrega un marcador para controlar la primera línea
            while (true) {
                long direccion = archivo.getFilePointer();
                cad = archivo.readLine();
                if (cad == null) {
                    break;
                }
                if (primeraLinea) {
                    primeraLinea = false; // Ignora la primera línea
                    continue;
                }
                int indice = obtenerId(cad);
                insertar(indice, direccion);

            }
        } catch (FileNotFoundException fe) {
            consola("No se encontró el archivo");
        }
        archivo.close();
    }

    public static int obtenerId(String cadena) {
        String id = "";
    
        for (int posToken = 0; posToken < 4; posToken++) {
            id = id + cadena.charAt(posToken);
        }
    
        return Integer.parseInt(id);
    }

    public void mostrarRegistro(int indice){
        try {
            long direccion = buscar(indice);
            if (direccion != 0) {
                mostrarRegistro(ruta, direccion);
            } else {
                consola("No se encontro el registro");
            }
        } catch (Exception e) {
            consola(e.getMessage());
        }
    }

    private void mostrarRegistro(String ruta, long direccion) throws IOException{
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile(ruta, "r");
           
        } catch (FileNotFoundException fe) {
            consola("No se encontró el archivo");
        }
        consola("\n");
        archivo.seek(direccion);
        consola(archivo.readLine());
        consola("\n");
        archivo.close();
    }

    public void innorden(){
        //IRD
        innorden(raiz);
    }

    private void innorden(NodoArbolBusqueda subRaiz){
        //IRD
        if (subRaiz != null) {
            innorden(subRaiz.getEnlaceIzq());
            consola(subRaiz.getIndice() + " ");
            innorden(subRaiz.getEnlaceDer());
        } //El caso base es cuando subRaiz es null
    }

    public void agregarRegistro() throws IOException {
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length()); // Situar el puntero al final del archivo
            long direccion = archivo.getFilePointer(); // Obtener la dirección del nuevo registro
            int indice = (int)encontrarUltimo() + 1; // Obtener el índice del nuevo registro
            String nuevoRegistro = indice + "," + pedirRegistro(); //Definimos el nuevo registro
            archivo.writeBytes(nuevoRegistro + "\n"); // Escribir el nuevo registro en el archivo
            insertar(indice, direccion); // Insertar el índice y la dirección en el árbol
        } catch (FileNotFoundException fe) {
            consola("No se encontró el archivo");
        }
        archivo.close();
    }

    private String pedirRegistro(){
        consola("Inserta el registro, separado por comas: ");
        String registro = EntradaPorDefecto.consolaCadenas();
        return registro;
    }

    public void borrarRegistro (int indice) {
        try {
            long direccion = buscar(indice);
            if (direccion != 0) {
                borrarRegistro(direccion);
            } else {
                consola("No se encontro el registro");
            }
        } catch (Exception e) {
            consola(e.getMessage());
        }
    }

    private void borrarRegistro(long direccion) throws IOException {
        RandomAccessFile archivo = null;
        vaciar();
        try {
            archivo = new RandomAccessFile(ruta, "r");
            
            String cad = "";
            boolean primeraLinea = true; // Agrega un marcador para controlar la primera línea
            while (true) {
                long direccionActual = archivo.getFilePointer();
                if (direccionActual == direccion) {
                    archivo.readLine(); // Ignora la línea de dirección de memoria
                    continue;
                }
                cad = archivo.readLine();
                if (cad == null) {
                    break;
                }
                if (primeraLinea) {
                    primeraLinea = false; // Ignora la primera línea
                    continue;
                }
    
                int indice = obtenerId(cad);
                insertar(indice, direccionActual);
            }
        }catch (FileNotFoundException fe) {
            SalidaPorDefecto.consola("No se encontró el archivo");
        }
        archivo.close();
    }

}
