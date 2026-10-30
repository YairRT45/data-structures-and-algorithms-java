package registros.arrocera;

public class Campesino {
    protected String nombre;

    protected Cosecha cosecha1;
    protected Cosecha cosecha2;
    protected Cosecha cosecha3;
    protected Cosecha cosecha4;
    
    public Campesino(String nombre) {
        this.nombre = nombre;
        cosecha1 = new Cosecha();
        cosecha2 = new Cosecha();
        cosecha3 = new Cosecha();
        cosecha4 = new Cosecha();
    }

    public void rellenarCosechas(){ //Este metodo rellena todas las listas con datos random
        cosecha1.llenarListaCosecha();
        cosecha2.llenarListaCosecha();
        cosecha3.llenarListaCosecha();
        cosecha4.llenarListaCosecha();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cosecha getCosecha1() {
        return cosecha1;
    }

    public Cosecha getCosecha2() {
        return cosecha2;
    }

    public Cosecha getCosecha3() {
        return cosecha3;
    }

    public Cosecha getCosecha4() {
        return cosecha4;
    }

}
