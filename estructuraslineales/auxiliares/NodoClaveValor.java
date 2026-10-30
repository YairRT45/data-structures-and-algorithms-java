package estructuraslineales.auxiliares;

public class NodoClaveValor {
    protected Object dato;
    protected NodoClaveValor enlaceDer;
    protected Object clave;

    public NodoClaveValor(Object clave, Object valor){
        this.clave = clave;
        this.dato = valor;
        this.enlaceDer = null; 
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoClaveValor getEnlaceDer() {
        return enlaceDer;
    }

    public void setEnlaceDer(NodoClaveValor enlaceDer) {
        this.enlaceDer = enlaceDer;
    }
    
    public Object getClave() {
        return clave;
    }

    public void setClave(Object clave) {
        this.clave = clave;
    }

    @Override
    public String toString(){
        return clave.toString() + " (" + dato.toString() + ")";
    }
}
