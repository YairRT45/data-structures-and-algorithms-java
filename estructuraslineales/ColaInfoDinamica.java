package estructuraslineales;


public class ColaInfoDinamica implements LoteInfo{
    protected int posInicial;
    protected int posFinal;
    protected ListaInfoDinamica datos;
    protected int CAPACIDAD;

    public ColaInfoDinamica(){
        datos = new ListaInfoDinamica();
    }

    public boolean poner(Object valor){
        int retorno = datos.insertar(valor);
        if (retorno == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Object quitar(){
        return datos.borrarInicio();
    }

    public boolean vacio(){
        return datos.estaVacia();
    }

    //Metodo que no se utilizara por ser dinamico
    public boolean lleno(){
        return false;
    }

    //Metodo que
    public Object verFinal(){
        return datos.verFinal();
    } 

    public void mostrar(){
        datos.mostrar();
    }
}
