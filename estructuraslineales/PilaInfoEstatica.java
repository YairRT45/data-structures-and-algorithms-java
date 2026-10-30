package estructuraslineales;

public class PilaInfoEstatica implements LoteInfo{
    protected ListaInfoEstatica arregloPila;

    public PilaInfoEstatica(int cuantos){
        arregloPila = new ListaInfoEstatica(cuantos);
    }
    
    public boolean poner(Object valor){
        int retorno = arregloPila.insertar(valor);
        if (retorno >= 0 ) { //Si pudo, con la posición de inserción
            return true;
        } else { //No pudo
            return false;
        }
    }

    public Object quitar(){
        return arregloPila.borrar();
    }

    public boolean vacio(){
        return arregloPila.estaVacia();
    }

    public boolean lleno(){
        return arregloPila.estaLleno();
    }

    public Object verFinal(){
        return arregloPila.verFinal();
    }

    public void mostrar(){
        arregloPila.mostrar();
    }
}
