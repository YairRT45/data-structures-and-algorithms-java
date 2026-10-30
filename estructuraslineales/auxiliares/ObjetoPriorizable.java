package estructuraslineales.auxiliares;

public class ObjetoPriorizable {
    protected Object valor;
    protected int Prioridad;

    public ObjetoPriorizable(Object valor, int prioridad) {
        this.valor = valor;
        Prioridad = prioridad;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(int prioridad) {
        Prioridad = prioridad;
    }

    @Override
    public String toString() {
        return ("" + Prioridad);
    }

    // public String toString(){
        // return (valor + "");
    // }

}
