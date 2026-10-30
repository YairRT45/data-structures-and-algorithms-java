package estructuraslineales.auxiliares;

public class Nodo {
    protected Object dato;
    protected Nodo enlaceDer;


    public Nodo(Object valor) {
        dato = valor;
        enlaceDer = null;
    }


    public Object getDato() {
        return dato;
    }


    public void setDato(Object dato) {
        this.dato = dato;
    }


    public Nodo getEnlaceDer() {
        return enlaceDer;
    }


    public void setEnlaceDer(Nodo enlaceDer) {
        this.enlaceDer = enlaceDer;
    }
 
    @Override
    public String toString(){
        return dato.toString();
    }
}
