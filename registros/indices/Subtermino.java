package registros.indices;

import entradasalida.SalidaPorDefecto;

public class Subtermino {
    protected int paginaInicio;
    protected int paginaFin;
    protected String nombre;


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

    public void mostrarSubtermino(){ //Metodo que nos sirve para mostrar el subtermino de la forma que querramos
        if (paginaInicio == paginaFin) {
            SalidaPorDefecto.consola(nombre + ", " + paginaInicio + "\n");
        }else{
            SalidaPorDefecto.consola(nombre + ", " + paginaInicio + "-" + paginaFin + "\n");
        }
    }

}
