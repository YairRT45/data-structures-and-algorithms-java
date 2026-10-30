package registros.ventas;

public class Cliente {
    protected String rfc;
    protected String nombre;
    protected int edad;

    public Cliente(String rfc, String nombre, int edad){
        this.rfc = rfc;
        this.nombre = nombre;
        this.edad = edad;
    }

    //getters y setters
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString(){
        return rfc;
    }
}
