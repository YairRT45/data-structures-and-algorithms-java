package registros.indices;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstaticaOrdenada;
import utils.comunes.TipoOrdenamiento;

public class Termino {
    protected int paginaInicio;
    protected int paginaFin;
    protected String nombre;
    protected ListaInfoEstaticaOrdenada subterminos;
    protected TipoOrdenamiento orden = TipoOrdenamiento.INC;


    public Termino(int subterminosMaximos) {
        subterminos = new ListaInfoEstaticaOrdenada(subterminosMaximos, orden);
    }

    public boolean agregarSubtermino(Subtermino subterminoParam){ //Insertar un subtermino en la lista
        int retornoSubter = subterminos.insertar(subterminoParam);
        if (retornoSubter < 0) { //hubo error
            return false;   
        }
        return true; //Si llega hasta acá, quiere decir que no hubo error
    }

    public int getPaginaInicio() {
        return paginaInicio;
    }

    public void setPaginaInicio(int paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public int getPaginaFin() {
        return paginaFin;
    }

    public void setPaginaFin(int paginaFin) {
        this.paginaFin = paginaFin;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }

    public void buscarSubtermino(String nombre){ //Metodo para buscar un subtermino que esta dentro de un termino
        int indiceSubtermino = (int) subterminos.encontrar(nombre);
        if (indiceSubtermino > 0) {
            Subtermino subterminoEncontrado = (Subtermino) subterminos.obtener(indiceSubtermino - 1);
            subterminoEncontrado.mostrarSubtermino();
        }else{
            SalidaPorDefecto.consola("No se encontro ningún termino con ese nombre");
        }
    }

    public void mostrarTermino(){ //Metodo que nos sirve para mostrar el termino de la forma que querramos
        if (paginaInicio == paginaFin){
            SalidaPorDefecto.consola(nombre + ", " + paginaInicio + "\n");
            for(int contador = 0; contador < subterminos.cantidad(); contador++){
                Subtermino unSubtermino = (Subtermino) subterminos.obtener(contador);
                SalidaPorDefecto.consola("\t");
                unSubtermino.mostrarSubtermino();
            }
        }else{
            SalidaPorDefecto.consola(nombre + ", " + paginaInicio + "-" + paginaFin + "\n");
            for(int contador = 0; contador < subterminos.cantidad(); contador++){
                Subtermino unSubtermino = (Subtermino) subterminos.obtener(contador);
                SalidaPorDefecto.consola("\t");
                unSubtermino.mostrarSubtermino();
            }
        }
    }
}
