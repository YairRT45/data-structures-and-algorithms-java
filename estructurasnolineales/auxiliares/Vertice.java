package estructurasnolineales.auxiliares;

public class Vertice {
    protected int indice;
    protected Object dato;

    public Vertice() {
        indice = -1;
        dato = null;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
