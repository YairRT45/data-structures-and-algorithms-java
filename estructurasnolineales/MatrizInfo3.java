package estructurasnolineales;

import entradasalida.SalidaPorDefecto;

public class MatrizInfo3 {
    protected Object arreglo[][][];
    protected int renglones;
    protected int columnas;
    protected int profundidad;

    public MatrizInfo3(int rengs, int cols, int profs){
        renglones = rengs;
        columnas = cols;
        profundidad = profs;
        arreglo = new Object[renglones][columnas][profundidad];
    }

    public int getRenglones() {
        return renglones;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public Object obtener(int reng, int col, int prof){
        if (validarIndices(reng, renglones) == true &&
            validarIndices(col, columnas) == true &&
            validarIndices(prof, profundidad)) {
            return arreglo[reng][col][prof];
        }else{ //No es un índice valido
            return null;
        }
    }

    public boolean cambiar(int reng, int col, int prof, Object valor){
        if (validarIndices(reng, renglones) == true &&
        validarIndices(col, columnas) == true &&
        validarIndices(prof, profundidad)) {
        arreglo[reng][col][prof] = valor;
        return true;
    }else{ //No es un índice valido
        return false;
    }
    }

    private boolean validarIndices(int indice, int dimensionMax){
        if(indice >= 0 && indice < dimensionMax){
            return true;
        } else {
            return false;
        }
    }

    public void rellenar(Object valor){
        for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            //Para mi solo existe un renglón
            for(int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                //Para mi solo existe una columna
                for(int cadaProf = 0; cadaProf < profundidad; cadaProf++){
                    arreglo[cadaRenglon][cadaColumna][cadaProf] = valor;
                }
            } 
        }
    }

    public void mostrarXColumna(){
        for (int cadaRebanada = 0; cadaRebanada < columnas; cadaRebanada++){
            //Voy a procesar una rebanada
            for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
                //Un renglón tiene varias rebanadas
                for(int cadaColumna = 0; cadaColumna < profundidad; cadaColumna++){ //cadaColumna hace referencia a las columnas que quedan de la matriz bidimensional 
                    SalidaPorDefecto.consola(arreglo[cadaRenglon][cadaRebanada][cadaColumna] + " ");
                } //Despues de recorrer todas las columnas de un renglon
                SalidaPorDefecto.consola("\n");
            }
            SalidaPorDefecto.consola("\n");
        }
    }
}