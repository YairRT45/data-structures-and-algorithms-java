package Entidades;

//Esta clase solamente define el modelo de lo que contienen los aviones y sus metodos para acceder a sus atributos
public class Avion {
    public String tipo;
    public String matricula;
    public String fabricante;
    public String modelo;
    public int capacidad;
    public int autonomia;

    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelos(String modelo) {
        this.modelo = modelo;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getAutonomia() {
        return autonomia;
    }
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
}
