package Entidades;

//Esta clase solamente define el modelo de lo que contienen los pilotos y sus metodos para acceder a sus atributos
public class Piloto {
    public String licencia;
    public String nombre;
    public Avion avionAsignado;

    
    public String getLicencia() {
        return licencia;
    }
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Avion getAvionAsignado() {
        return avionAsignado;
    }
    public void setAvionAsignado(Avion avionAsignado) {
        this.avionAsignado = avionAsignado;
    }
}
