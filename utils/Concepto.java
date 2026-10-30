package utils;

public class Concepto {
    String definicion;
    String tipo;

    public Concepto(String definicion, String tipo){
        this.definicion = definicion;
        this.tipo = tipo;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return definicion;
    }

    
}
