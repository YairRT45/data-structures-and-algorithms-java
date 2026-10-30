import estructurasnolineales.GrafoEstatico;

public class PruebaGrafoRP {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico(8);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("F");
        grafo.agregarVertice("H");
        grafo.agregarVertice("M");
        grafo.agregarVertice("R");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "H");
        grafo.agregarArista("B", "C");
        grafo.agregarArista("C", "D");
        grafo.agregarArista("C", "F");
        grafo.agregarArista("D", "B");
        grafo.agregarArista("D", "H");
        grafo.agregarArista("D", "F");
        grafo.agregarArista("F", "M");
        grafo.agregarArista("H", "M");
        grafo.agregarArista("M", "R");

        // grafo.recorridoProfundidad("A").mostra r();
        grafo.recorridoAnchura("A").mostrar();
    }
}
