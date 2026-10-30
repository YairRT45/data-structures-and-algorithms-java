package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;
import utils.comunes.TipoColumna;
import utils.comunes.TipoRenglon;

public class MatrizInfo2 {
    protected int renglones;
    protected int columnas;
    protected Object arreglo[][];

    public MatrizInfo2(int reng, int col){
        renglones = reng;
        columnas = col;
        arreglo = new Object[renglones][columnas];
    }

    public MatrizInfo2(int reng, int col, Object valor){
        renglones = reng;
        columnas = col;
        arreglo = new Object[renglones][columnas];
        // Rellenar la matriz con el valor dado
        for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            for(int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                arreglo[cadaRenglon][cadaColumna] = valor;
            }
        }
    }

    // Método para obtener el valor en la posición dada de la matriz
    public Object obtener(int renglon, int columna){
        return arreglo[renglon][columna];
    }

    // Método para cambiar el valor en la posición dada de la matriz
    public boolean cambiar(int renglon, int columna, Object valor){
        if (validarIndices(renglon, renglones) && validarIndices(columna, columnas)) {
            arreglo[renglon][columna] = valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean validarIndices(int indice, int dimensionMax){
        if(indice >= 0 && indice < dimensionMax){
            return true;
        } else {
            return false;
        }
    }

    public int obtenerRenglones() {
        return renglones;
    }

    public int obtenerColumnas() {
        return columnas;
    }

    public void mostrarXRenglon(){
        for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++) {
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
                SalidaPorDefecto.consola(arreglo[cadaRenglon][cadaColumna] + " ");
            }
            SalidaPorDefecto.consola("\n");
        }
    }

    public void mostrarXColumna(){
        for (int cadaRenglon = 0; cadaRenglon < columnas; cadaRenglon++) {
            for (int cadaColumna = 0; cadaColumna < renglones; cadaColumna++) {
                SalidaPorDefecto.consola(arreglo[cadaColumna][cadaRenglon] + " ");
            }
            SalidaPorDefecto.consola("\n");
        }
    }

    public void transpuesta(){
        // Crear una nueva matriz para almacenar la transpuesta
        Object[][] transpuesta = new Object[columnas][renglones];
        // Calcular la transpuesta intercambiando filas por columnas
        for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
            for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++) {
                transpuesta[cadaColumna][cadaRenglon] = arreglo[cadaRenglon][cadaColumna]; 
            }
        }

        // Actualizar la matriz original con la transpuest
        arreglo = transpuesta;
        columnas = renglones;
        renglones = arreglo.length;
    }

    public void rellenar(Object valor){
        for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            //Para mi solo existe un renglón
            for(int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                //Para mi solo existe una columna
                arreglo[cadaRenglon][cadaColumna] = valor;
            }
        }
    }
    
    public MatrizInfo2 clonar(){
        return this;
    }

    public boolean esIgual(MatrizInfo2 matriz2){
        //Verificar si las matrices tienen las mismas dimensiones
        if (obtenerColumnas() != matriz2.obtenerColumnas() || obtenerRenglones() != obtenerRenglones()) {
            return false;
        }
        //Recorremos ambas matrices y comparamos cada elemento
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (obtener(renglon, columna) != matriz2.obtener(renglon, columna)) {
                    return false; //Si se encuentra un elemento diferente nod dira que las matrices no son iguales
                }
            }
        }
        return true; //Si llega hasta aqui es que todo es igual
    }

    public boolean vectorColumna(int filas, Object info){
        arreglo = new Object[filas][1];
        columnas = 1;
        renglones = filas;
        rellenar(info);
        return true;
    }

    public boolean vectorRenglon(int columnas, Object info){
        arreglo = new Object[1][columnas];
        this.columnas = columnas;
        renglones = 1; 
        rellenar(info);
        return true;
    }

    public boolean redefinir(MatrizInfo2 matriz2){
        //Redefinimos el tamaño del arreglo
        arreglo = new Object[matriz2.obtenerRenglones()][matriz2.obtenerColumnas()];
        renglones = matriz2.obtenerRenglones();
        columnas = matriz2.obtenerColumnas(); 

        arreglo = matriz2.arreglo;
        if (esIgual(matriz2)) { //Si las matrices son iguales despues de redefinir se regresa verdadero
            return true;
        } else { //Si no son iguales, hubo error
            return false;
        }
    }

    public boolean agregarRenglon(ListaInfoEstatica arreglo){
        if (arreglo.cantidad() == columnas) {
            // Crear una matriz temporal con una fila adicional
            MatrizInfo2 matrizTemporal = new MatrizInfo2(renglones + 1, columnas);
            // Copiar los valores de la matriz original a la matriz temporal
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    matrizTemporal.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
            // Agregar los valores del nuevo renglón
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
                matrizTemporal.cambiar(renglones, cadaColumna, arreglo.obtener(cadaColumna));
            }
            // Redefinir la matriz original con la matriz temporal
            redefinir(matrizTemporal);
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarColumna(ListaInfoEstatica arreglo){
        if (arreglo.cantidad() == renglones) {
            // Crear una matriz temporal con una columna adicional
            MatrizInfo2 matrizTemporal = new MatrizInfo2(renglones, columnas + 1);
            // Copiar los valores de la matriz original a la matriz temporal
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    matrizTemporal.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
            // Agregar los valores de la nueva columna
            for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++) {
                matrizTemporal.cambiar(cadaRenglon, columnas, arreglo.obtener(cadaRenglon));
            }
            // Redefinir la matriz original con la matriz temporal
            redefinir(matrizTemporal);
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarMatrizXColumna(MatrizInfo2 matriz){
        if (renglones == matriz.renglones) {
            // Crear una matriz temporal con el tamaño adecuado para contener las columnas de ambas matrices
            MatrizInfo2 matrizTemporal = new MatrizInfo2(renglones, columnas + matriz.columnas);
            // Copiar los valores de la matriz original a la matriz temporal
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    matrizTemporal.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
            // Agregar las columnas de la matriz dada a la matriz temporal
            for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++) {
                for (int cadaColumna = columnas; cadaColumna < matrizTemporal.columnas; cadaColumna++) {
                    matrizTemporal.cambiar(cadaRenglon, cadaColumna, matriz.obtener(cadaRenglon, cadaColumna - columnas));
                }
            }
            redefinir(matrizTemporal);
            return true;
        } else {
            return false;
        }
    }

    public boolean agregarMatrizXRenglon(MatrizInfo2 matriz){
        if (columnas == matriz.columnas) {
            // Crear una matriz temporal con el tamaño adecuado para contener los renglones de ambas matrices
            MatrizInfo2 matrizTemporal = new MatrizInfo2(renglones + matriz.renglones, columnas);
            // Copiar los valores de la matriz original a la matriz temporal
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    matrizTemporal.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
             // Agregar los renglones de la matriz dada a la matriz temporal
            for (int cadaRenglon = renglones; cadaRenglon < matrizTemporal.renglones; cadaRenglon++) {
                for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
                    matrizTemporal.cambiar(cadaRenglon, cadaColumna, matriz.obtener(cadaRenglon - renglones, cadaColumna));
                }
            }
            redefinir(matrizTemporal);
            return true;
        } else {
            return false;
        }
    }

    public MatrizInfo3 aMatriz3(ListaInfoEstatica matrices) {
        // Verificar si todas las matrices en la lista tienen las mismas dimensiones que la matriz actual
        for (int ciontador = 0; ciontador < matrices.cantidad(); ciontador++) { //dimensiones
            MatrizInfo2 matrizTemporal = (MatrizInfo2) matrices.obtener(ciontador);
            if (matrizTemporal.obtenerColumnas() != columnas || matrizTemporal.obtenerRenglones() != renglones) {
                return null; // Si alguna matriz no tiene las mismas dimensiones, retornar null
            }
        }
        // Crear una nueva MatrizInfo3D con una capa adicional para cad matriz en la lista mas la matriz actual
        MatrizInfo3 nuevaMatriz3D = new MatrizInfo3(renglones, columnas, matrices.cantidad() + 1);
        // Copiar los valores de la matriz actual como la primera capa de la nueva MatrizInfo3D
        for (int numRenglon = 0; numRenglon < renglones; numRenglon++) {//primeracapa
            for (int numeroColumna = 0; numeroColumna < columnas; numeroColumna++) {
                nuevaMatriz3D.cambiar(numRenglon, numeroColumna, 0, arreglo[numRenglon][numeroColumna]);
            }
        }
        
        // Agregar las capas adicionales a partir de las matrices en la lista
        for (int numProfundo = 0; numProfundo < matrices.cantidad(); numProfundo++) {// agregar
            MatrizInfo2 matrizTemporal = (MatrizInfo2) matrices.obtener(numProfundo);
            for (int numRenglon = 0; numRenglon < renglones; numRenglon++) {
                for (int numeroColumna = 0; numeroColumna < columnas; numeroColumna++) {
                    nuevaMatriz3D.cambiar(numRenglon, numeroColumna, numProfundo + 1, matrizTemporal.obtener(numRenglon, numeroColumna));
                }
            }
        }
        return nuevaMatriz3D;
    }

    public MatrizInfo2 aVectorColumna(){
        // Crear un vector columna con la cantidad de elementos igual al producto de filas y columnas de la matriz
        MatrizInfo2 vector = new MatrizInfo2(renglones * columnas, 1);
        ListaInfoEstatica listaTemporal = new ListaInfoEstatica(renglones * columnas);
        // Llenar la lista temporal con los elementos de la matriz en orden de columna
        for (int columna = 0; columna < columnas; columna++) {
            for (int renglon = 0; renglon < renglones; renglon++) {
                listaTemporal.insertar(obtener(renglon, columna));    
            }
        }
        // Llenar el vector con los elementos de la lista temporal
        for (int iterador = 0; iterador < listaTemporal.capacidad(); iterador++) {
            vector.cambiar(iterador, 0, listaTemporal.obtener(iterador));
        }
        return vector;
    }

    public MatrizInfo2 aVectorRenglon(){ 
        // Crear un vector rengol con la cantidad de elementos igual al producto de filas y columnas de la matriz
        MatrizInfo2 vector = new MatrizInfo2(1, renglones * columnas);
        ListaInfoEstatica listaTemporal = new ListaInfoEstatica(renglones * columnas);
        // Llenar la lista temporal con los elementos de la matriz en orden de renglon
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                listaTemporal.insertar(obtener(renglon, columna));    
            }
        }
        // Llenar el vector con los elementos de la lista temporal
        for (int iterador = 0; iterador < listaTemporal.capacidad(); iterador++) {
            vector.cambiar(0, iterador, listaTemporal.obtener(iterador));
        }
        return vector;
    }

    public boolean quitarColumna(TipoColumna tipoCol){
        //Creamos una matriz temporal con una columna menos
        MatrizInfo2 tempo = new MatrizInfo2(renglones, columnas - 1);
        columnas = columnas -1;
        if (tipoCol == TipoColumna.IZQ) {
            //Agregamos todas las columnas excepto la primera
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    tempo.cambiar(renglon, columna, obtener(renglon, columna + 1));
                }
            }
            redefinir(tempo); //Redefinimos la original con la temporal
            return true;
        } else if (tipoCol == TipoColumna.DER) {
            //Agregamos todas las columnas excepto la ultima
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    tempo.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
            redefinir(tempo); //Redefinimos la original con la temporal
            return true;
        } else {
            return false;
        }
    }

    public boolean quitarRenglon(TipoRenglon tipoRenglon){
        //Creamos una matriz temporal con un renglon menos
        MatrizInfo2 tempo = new MatrizInfo2(renglones - 1, columnas);
        renglones = renglones -1;
        if (tipoRenglon == TipoRenglon.SUP) {
            //Agregamos todos los renglones excepto el primero
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    tempo.cambiar(renglon, columna, obtener(renglon + 1, columna));
                }
            }
            redefinir(tempo); //Redefinimos la original con la temporal
            return true;
        } else if (tipoRenglon == TipoRenglon.INF) {
            //Agregamos todos los renglones excepto el ultimo
            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    tempo.cambiar(renglon, columna, obtener(renglon, columna));
                }
            }
            redefinir(tempo);//Redefinimos la original con la temporal
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarColumna(int columna){
        if (validarIndices(columna, columnas)) {
            for (int rengAct = 0; rengAct < renglones; rengAct++) {
                for (int colAct = columna; colAct < columnas - 1; colAct++) {
                    arreglo[rengAct][colAct] = arreglo[rengAct][colAct + 1];
                }
            }
            columnas = columnas - 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarRenglon(int renglon){
        if (validarIndices(renglon, renglones)) {
            for (int rengAct = renglon; rengAct < renglones - 1; rengAct++) {
                for (int colAct = 0; colAct < columnas; colAct++) {
                    arreglo[rengAct][colAct] = arreglo[rengAct + 1][colAct];
                }
            }
            renglones = renglones - 1;
            return true;
        } else {
            return false;
        }
    }

    public void matrizDiagonal(Object valor){
        if (obtenerColumnas() == obtenerRenglones()) {
            for (int indice = 0; indice < obtenerRenglones(); indice++){
                arreglo[indice][indice] = valor;
            }
        } else { //Lo que corresponda a matrices no cuadradas

        }
    }
}
