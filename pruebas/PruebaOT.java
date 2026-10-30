import estructurasnolineales.GrafoEstatico;

public class PruebaOT {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico(9);

        grafo.agregarVertice("P1");
        grafo.agregarVertice("P2");
        grafo.agregarVertice("P3");
        grafo.agregarVertice("P4");
        grafo.agregarVertice("P5");
        grafo.agregarVertice("P6");
        grafo.agregarVertice("P7");
        grafo.agregarVertice("P8");
        grafo.agregarVertice("P9");

        grafo.agregarArista("P5", "P8");
        grafo.agregarArista("P3", "P8");
        grafo.agregarArista("P3", "P4");
        grafo.agregarArista("P3", "P7");
        grafo.agregarArista("P8", "P6");
        grafo.agregarArista("P8", "P4");
        grafo.agregarArista("P4", "P6");
        grafo.agregarArista("P4", "P1");
        grafo.agregarArista("P4", "P7");
        grafo.agregarArista("P7", "P1");
        grafo.agregarArista("P7", "P2");
        grafo.agregarArista("P6", "P1");
        grafo.agregarArista("P1", "P9");
        grafo.agregarArista("P2", "P9");

        grafo.ordenaciónTopologica().mostrar();
    }
}
