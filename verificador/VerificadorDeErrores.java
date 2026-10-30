package verificador;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.archivos.ArchivoTexto;
import estructuraslineales.ColaInfoDinamica;
import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.PilaInfoDinamica;
import estructuraslineales.PilaInfoEstatica;
import estructuraslineales.auxiliares.Nodo;

public class VerificadorDeErrores {
    
    protected ListaInfoEstatica lista;
    protected ListaInfoEstatica listaTokenizada;
    protected ListaInfoDinamica listaPalabras;
    protected PilaInfoDinamica pilaSimbolos;
    protected ColaInfoDinamica colaErrores;
    protected ListaInfoDinamica variablesDeclaradas;
    protected ListaInfoDinamica variablesNoDeclaradas;

    public VerificadorDeErrores(String archivo){
        lista = ArchivoTexto.leer(archivo);
        pilaSimbolos = new PilaInfoDinamica();
        colaErrores = new ColaInfoDinamica();
        tokenizarRenglones();
        separarPorPalabras();
    }

    public VerificadorDeErrores(String cadena, int cuantos){
        lista = new ListaInfoEstatica(cuantos);
        lista.insertar(cadena);
        pilaSimbolos = new PilaInfoDinamica();
        colaErrores = new ColaInfoDinamica();
        tokenizarRenglones();
    }

    public boolean tokenizarRenglones(){
        listaTokenizada = new ListaInfoEstatica(lista.cantidad());
        for (int indice = 0; indice < lista.cantidad(); indice++) {
            String renglonActual = (String) lista.obtener(indice);
            ListaInfoEstatica listaRenglon = new ListaInfoEstatica(renglonActual.length());
            for (int posToken = 0; posToken < renglonActual.length(); posToken++) {
                String token = renglonActual.charAt(posToken) + "";
                listaRenglon.insertar(token);
            }
            listaTokenizada.insertar(listaRenglon);
        }
        return true;
    }

    public boolean separarPorPalabras() {
        // Obtener la lista de caracteres del archivo
        tokenizarRenglones();
        // Crear una lista dinámica para almacenar las palabras
        listaPalabras = new ListaInfoDinamica();

        // Iterar sobre la lista de caracteres
        for (int lineaTemp = 0; lineaTemp < listaTokenizada.capacidad(); lineaTemp++) {
            // Obtener la lista de caracteres de la línea actual
            ListaInfoEstatica linea = (ListaInfoEstatica) listaTokenizada.obtener(lineaTemp);
            // Inicializar una cadena para almacenar la palabra actual
            String palabraActual = "";

            // Iterar sobre los caracteres de la línea
            for (int caracterTemp = 0; caracterTemp < linea.capacidad(); caracterTemp++) {
                // Obtener el carácter actual
                String caracter = (String)linea.obtener(caracterTemp);

                // Verificar si el carácter es un espacio en blanco
                if (caracter.equals(" ")) {
                    // Si es un espacio en blanco, agregar la palabra actual a la lista de palabras y reiniciar la palabra actual
                    if (palabraActual.length() > 0) {
                        listaPalabras.insertar(palabraActual);
                        palabraActual = "";
                    }
                } else {
                    // Si no es un espacio en blanco, añadirlo a la palabra actual
                    palabraActual = palabraActual + caracter;
                }
            }
            // Agregar la última palabra si no está vacía
            if (palabraActual.length() > 0) {
                listaPalabras.insertar(palabraActual);
            }
        }
        // Devolver la lista de palabras
        return true;
    }

    public boolean verificarVariables() {
        variablesDeclaradas = new ListaInfoDinamica();
        variablesNoDeclaradas = new ListaInfoDinamica();
        int contador = 0;
        Nodo temporal = listaPalabras.getApuntadorInicial();
        while (temporal != null) {
            String palabra = (String)temporal.getDato();
            // Verificar si la palabra es una declaración de variable
            if (palabra.equals("int") || palabra.equals("double") || palabra.equals("float")
            || palabra.equals("boolean") || palabra.equals("char") || palabra.equals("Object") 
            || palabra.equals("String")) {
                // Si es una declaración, obtener el nombre de la variable
                String variable = (String)listaPalabras.obtener(contador + 1);
                variable = variable.substring(0, variable.length()-1);
                variablesDeclaradas.insertar(variable);
            } else if (palabra.equals("=")) {
                // Si encuentra un signo de igual, verificar si la variable ya ha sido declarada
                String posibleVariable = (String)listaPalabras.obtener(contador - 1);
                if (variablesDeclaradas.encontrar(posibleVariable) == null) {
                    // Si no ha sido declarada, agregarla a la cola de variables no declaradas
                    variablesNoDeclaradas.insertar(posibleVariable);
                }
            }
            contador = contador + 1;
            temporal = temporal.getEnlaceDer();
        }
        return true;
    }
    
    public boolean verificarBalance(){
        for (int fila = 0; fila < lista.cantidad(); fila++) {
            ListaInfoEstatica renglon = (ListaInfoEstatica)listaTokenizada.obtener(fila);
            for (int posicion = 0; posicion < renglon.cantidad(); posicion++) {
                String caracter = (String)renglon.obtener(posicion); 

                if (caracter.equals("(") || caracter.equals("{") || caracter.equals("[")) {
                    //Si es un carácter de apertura, lo agregamos a la pila
                    SignoPosicion signo = new SignoPosicion(caracter, fila, posicion);
                    pilaSimbolos.poner(signo);
                    
                } else if (caracter.equals(")") || caracter.equals("}") || caracter.equals("]")) {
                    // Si es un carácter de cierre, verificamos si coincide con el último carácter de apertura en la pila
                    if ((pilaSimbolos.vacio() == false) && esParValido(caracter, pilaSimbolos)) {
                        pilaSimbolos.quitar();
                    } 
                    else 
                    if ((esParValido(caracter, pilaSimbolos) == false) && (pilaSimbolos.vacio() == false)) {
                        colaErrores.poner(pilaSimbolos.quitar());
                        posicion = posicion - 1;
                    } 
                    else {
                        // Si no coincide o la pila está vacia, hay un error
                        SignoPosicion signo = new SignoPosicion(caracter, fila, posicion);
                        colaErrores.poner(signo);
                    }
                }
            }
        }
        while (pilaSimbolos.vacio() == false) {
            colaErrores.poner(pilaSimbolos.quitar());
        }
        //Si la cola esta vacia entonces no hay errores
        return colaErrores.vacio();
    }

    // verificar si la pila esta vacia, sacar el simbolo de la pila, y verificar si es igual 
    private boolean esParValido(String apertura, PilaInfoDinamica pila) {
        if (pila.vacio() == false) {
            String cierre = ((SignoPosicion)pila.verFinal()).getSigno();
            if (apertura.equals(")") && cierre.equals("(")) {
                return true;
            } else if (apertura.equals("}") && cierre.equals("{")) {
                return true;
            } else if (apertura.equals("]") && cierre.equals("[")) {
                return true;
            } else if (apertura.equals("(") && cierre.equals(")")) {
                return true;
            } else if (apertura.equals("{") && cierre.equals("}")) {
                return true;
            } else if (apertura.equals("[") && cierre.equals("]")) {
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean verificarComentarios(){
        for (int fila = 0; fila < lista.cantidad(); fila++) {
            String comentario = "";
            ListaInfoEstatica renglon = (ListaInfoEstatica)listaTokenizada.obtener(fila);
            for (int posicion = 0; posicion < renglon.cantidad(); posicion++) {
                String caracter = (String)renglon.obtener(posicion); 
                String caracterAnterior = (String)renglon.obtener(posicion - 1);

                if (caracter.equals("/")) {
                    if (comentario.length() == 0) {
                        comentario = comentario + caracter;
                    } else if (comentario.equals("*") && caracterAnterior.equals("*")) {
                        comentario = comentario + caracter;
                    }
                } else if (caracter.equals("*")) {
                    if (comentario.length() == 0) {
                        comentario = comentario + caracter;
                    } else if (comentario.equals("/") && caracterAnterior.equals("/")) {
                        comentario = comentario + caracter;
                    }
                }

                if (comentario.equals("/*")) {
                    SignoPosicion signo = new SignoPosicion(comentario, fila, posicion - 1);
                    pilaSimbolos.poner(signo);
                    comentario = "";
                } 
                else if (comentario.equals("*/")) {
                    if (pilaSimbolos.vacio() == false) {
                        pilaSimbolos.quitar();
                    } else {
                        SignoPosicion signo = new SignoPosicion(comentario, fila, posicion - 1);
                        colaErrores.poner(signo);
                    }
                    comentario = "";
                }
            }
        }
        while (pilaSimbolos.vacio() == false) {
            colaErrores.poner(pilaSimbolos.quitar());
        }
        //Si la cola esta vacia entonces no hay errores
        return colaErrores.vacio();
    }
    
    public boolean mostrarRenglones(){
        for (int i = 0; i < listaTokenizada.cantidad(); i++) {
            ListaInfoEstatica listaTokens = (ListaInfoEstatica)listaTokenizada.obtener(i);
            listaTokens.mostrar();
            consola("\n");
        }
        return true;
    }

    public void mostrarPila(){
        pilaSimbolos.mostrar();
    }

    public void mostrarVariables(){
        Nodo temporal = variablesNoDeclaradas.getApuntadorInicial();
        while (temporal != null) {
            consola("Variable '" + (String)temporal.getDato() + "' no declarada \n");
            temporal = temporal.getEnlaceDer();
        }
    }

    public void mostrarErrores(){
        while (colaErrores.vacio() == false) {
            SignoPosicion error = (SignoPosicion)colaErrores.quitar();
            consola("línea " + error.getRenglon() + 1 + "\n");
            consola((String)lista.obtener(error.getRenglon()) + "\n");
            for (int iterador = 0; iterador < error.getPos(); iterador++) {
                consola(" ");
            }
            consola("^ ");
            if (error.getSigno().equals("(")) {
                consola("Faltó cerrar el paréntesis");
            } else if (error.getSigno().equals(")")) {
                consola("Faltó abrir el paréntesis");
            } else if (error.getSigno().equals("{")) {
                consola("Faltó cerrar la llave");
            } else if (error.getSigno().equals("}")) {
                consola("Faltó abrir la llave");
            } else if (error.getSigno().equals("[")) {
                consola("Faltó cerrar el corchete");
            } else if (error.getSigno().equals("]")) {
                consola("Faltó abrir el corchete");
            } else if (error.getSigno().equals("/*")) {
                consola("Faltó cerrar el comentario");
            } else if (error.getSigno().equals("*/")) {
                consola("Faltó abrir el comentario");
            }
        }
    }

}
