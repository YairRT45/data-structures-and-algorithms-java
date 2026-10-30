package estructuraslineales.auxiliares;

public class NodoBuscadoClave {
    protected NodoClaveValor buscado;
    protected NodoClaveValor anterior;
    
    public NodoClaveValor getBuscado() {
        return buscado;
    }
    public void setBuscado(NodoClaveValor buscado) {
        this.buscado = buscado;
    }
    public NodoClaveValor getAnterior() {
        return anterior;
    }
    public void setAnterior(NodoClaveValor anterior) {
        this.anterior = anterior;
    }

    
}
