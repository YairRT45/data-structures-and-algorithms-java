package utils.matematicas;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.EntradaPorDefecto;
import estructuraslineales.ListaInfoDinamica;
import estructuraslineales.auxiliares.Nodo;

public class Polinomio {
    protected ListaInfoDinamica listaMonomios;

    public Polinomio(){
        listaMonomios = new ListaInfoDinamica();
    }

    public void agregarMonomios(){
        consola("Cuantos monomios deseas agregar: ");
        int cantidad = EntradaPorDefecto.consolaInt();
        agregarMonomios(cantidad, 0);
    }

    private void agregarMonomios(int cantVeces, int indiceActual){
        if (indiceActual == cantVeces) {
            return;
        } else {
            consola("\nMonomio " + (indiceActual+1) + "\n");
            listaMonomios.insertar(pedirMonomio());
            agregarMonomios(cantVeces, indiceActual + 1);
        }
    }

    public void mostrar(){
        listaMonomios.mostrar();
    }

    private Monomio pedirMonomio(){
        consola("Ingresa el coeficiente: ");
        int coeficiente = EntradaPorDefecto.consolaInt();
        consola("Ingresa la potencia de X: ");
        int potX = EntradaPorDefecto.consolaInt();
        consola("Ingresa la potencia de Y: ");
        int potY = EntradaPorDefecto.consolaInt();
        Monomio monomio = new Monomio(coeficiente, potX, potY);
        return monomio;
    }

    public int evaluarPolinomio(){
        Nodo inicio = listaMonomios.getApuntadorInicial();
        consola("\nIngrese el valor de X: ");
        int x = EntradaPorDefecto.consolaInt();
        consola("Ingrese el valor de Y: ");
        int y = EntradaPorDefecto.consolaInt();
        return evaluarPolinomio(inicio, x, y);
    }

    private int evaluarPolinomio(Nodo nodo, int x, int y){
        if (nodo == null) {
            return 0;
        } else {
            Monomio monomio = (Monomio)nodo.getDato();
            return monomio.calcularResultado(x, y) + evaluarPolinomio(nodo.getEnlaceDer(), x, y);
        }
    }
}
