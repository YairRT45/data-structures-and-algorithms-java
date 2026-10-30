package registros.ventas;

public class Vendedor {
    protected int numVendedor;
    protected String nombre;
    protected String fechaNac;
    protected char sexo;
    
    public Vendedor(int numVendedor, String nombre, String fechaNac, char sexo) {
        this.numVendedor = numVendedor;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }

    public int getNumVendedor() {
        return numVendedor;
    }

    public void setNumVendedor(int numVendedor) {
        this.numVendedor = numVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    } 

    @Override
    public String toString(){
        return "" + numVendedor;
    }
}
