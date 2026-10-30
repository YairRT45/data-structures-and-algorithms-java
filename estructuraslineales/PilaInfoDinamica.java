package estructuraslineales;

public class PilaInfoDinamica implements LoteInfo{
    protected ListaInfoDinamica arregloPila;

    public PilaInfoDinamica(){
        arregloPila = new ListaInfoDinamica();
    }

    public boolean poner(Object valor) {
        int retorno = arregloPila.insertar(valor);
        if (retorno == 0) {
            return true; //Si pudo agregar el elemento
        } else {
            return false; //No pudo
        }
    }

    public Object quitar() {
        return arregloPila.borrar();    
    }

    public boolean vacio() {
        return arregloPila.estaVacia();
    }

    //Este metodo no se utiliza ya que es memoria dinamica
    public boolean lleno() {
        return false;    
    }

    public Object verFinal() {
        return arregloPila.verFinal();
    }

    public void mostrar() {
        arregloPila.mostrar();
    }
    
}
