package estructurasnolineales.auxiliares;

public class NodoArbolBusqueda {
    protected int indice;
    protected long direccion;
    protected NodoArbolBusqueda enlaceIzq;
    protected NodoArbolBusqueda enlaceDer;
    
    public NodoArbolBusqueda(int indice, long direccion) {
        this.indice = indice;
        this.direccion = direccion;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public long getDireccion() {
        return direccion;
    }

    public void setDireccion(long direccion) {
        this.direccion = direccion;
    }

    public NodoArbolBusqueda getEnlaceIzq() {
        return enlaceIzq;
    }

    public void setEnlaceIzq(NodoArbolBusqueda enlaceIzq) {
        this.enlaceIzq = enlaceIzq;
    }

    public NodoArbolBusqueda getEnlaceDer() {
        return enlaceDer;
    }

    public void setEnlaceDer(NodoArbolBusqueda enlaceDer) {
        this.enlaceDer = enlaceDer;
    }
}
