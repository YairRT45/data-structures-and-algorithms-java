package registros.cosechas;

public class Campesino {
    protected String rfc;
    protected String nombre;
    protected int edad;
    protected char sexo;
    
    public Campesino(String rfc, String nombre, int edad, char sexo) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return rfc;
    }
}
