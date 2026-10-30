package Entidades;

//Esta clase solamente define el modelo de lo que contienen los vuelos y sus metodos para acceder a sus atributos
public class Vuelo {
    public Aeropuerto origen;
    public Aeropuerto destino;    
    public String fecha;
    public Piloto piloto;

    
    public Aeropuerto getOrigen() {
        return origen;
    }
    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }
    public Aeropuerto getDestino() {
        return destino;
    }
    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }
    public Piloto getPiloto() {
        return piloto;
    }
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
