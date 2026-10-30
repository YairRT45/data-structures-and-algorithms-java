package estructuraslineales;

import entradasalida.SalidaPorDefecto;

public class ColaInfoEstatica implements LoteInfo{
    protected int posInicial;
    protected int posFinal;
    protected Object datos[];
    protected int CAPACIDAD;

    public ColaInfoEstatica(int tamanio){
        CAPACIDAD = tamanio;
        datos = new Object[CAPACIDAD];
        posFinal = -1;
        posInicial = -1;
    }

    public boolean poner(Object valor){
        if (lleno() == false) { //d)
            if (vacio() == true) { //c)
                posFinal = 0;
                posInicial = 0;
            } else if (posFinal == CAPACIDAD-1) { //b)
                posFinal = 0;
            } else { //a) e)
                posFinal = posFinal + 1;
                
            }
            datos[posFinal] = valor;
            return true;
        } else {
            return false;
        }
    }

    public Object quitar(){
        if (vacio() == false) {
            Object respaldo = datos[posInicial];
            if (posInicial == posFinal) { //e) unico elemento
                posInicial = -1;
                posFinal = -1;
            } else if(posInicial == (CAPACIDAD-1)){ //c)
                posInicial = 0;
            } else { //a) d)
                posInicial = posInicial + 1;
            }
            return respaldo;
        } else { //b)
            return null;
        }
    }

    public boolean vacio(){
        if (posInicial == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lleno(){
        if ((posInicial == 0 && posFinal == (CAPACIDAD-1)) || (posInicial == posFinal+1)) {
            return true;
        } else {
            return false;
        }
    }

    public Object verFinal(){
        if (vacio() == false) {
            return datos[posFinal];
        } else {
            return null;
        }
    } 

    public void mostrar(){
        if (vacio() == false) {
            if (posInicial <= posFinal) {//Comportamiento ordinario, a, e
                for (int pos = posInicial; pos <= posFinal; pos++) {
                    SalidaPorDefecto.consola(datos[pos] + " ");
                }
            } else { //Comportamiento circular , c,d
                for (int pos = posInicial; pos <= CAPACIDAD-1; pos++) {
                    SalidaPorDefecto.consola(datos[pos] + " ");
                }
                for (int pos = 0; pos <= posFinal; pos++) {
                    SalidaPorDefecto.consola(datos[pos] + " ");
                }
            }
        }
    }
}
