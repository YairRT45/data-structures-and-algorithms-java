package registros.ventas;

public class Producto {
    protected String codigo;
    protected String descripcion;
    protected int existencia;     
    protected double precio;
    
    public Producto(String codigo, String descripcion, int existencia, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
