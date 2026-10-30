package estructuraslineales.auxiliares;

public class NodoDoble {
    protected Object dato;
    protected NodoDoble enlaceIzq;
    protected NodoDoble enlaceDer;

    
    public NodoDoble(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoDoble getEnlaceIzq() {
        return enlaceIzq;
    }

    public void setEnlaceIzq(NodoDoble enlaceIzq) {
        this.enlaceIzq = enlaceIzq;
    }

    public NodoDoble getEnlaceDer() {
        return enlaceDer;
    }

    public void setEnlaceDer(NodoDoble enlaceDer) {
        this.enlaceDer = enlaceDer;
    }
    
}
