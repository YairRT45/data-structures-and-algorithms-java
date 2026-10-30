package Entidades;

//Esta clase solamente define el modelo de lo que contienen los aeropuertos y sus metodos para acceder a sus atributos
public class Aeropuerto {
    public String ciudad;
    public String pais;

    
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
}
