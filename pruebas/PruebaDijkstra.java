import entradasalida.SalidaPorDefecto;
import estructurasnolineales.GrafoEstatico;
import utils.comunes.TipoOrdenamiento;

public class PruebaDijkstra {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico(9, TipoOrdenamiento.DEC);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        grafo.agregarVertice("H");
        grafo.agregarVertice("M");
        grafo.agregarVertice("R");
        grafo.agregarVertice("T");

        grafo.agregarArista("A", "E", 2);
        grafo.agregarArista("A", "D", 8);
        grafo.agregarArista("A", "C", 3);
        grafo.agregarArista("A", "B", 5);

        grafo.agregarArista("B", "A", 5);
        grafo.agregarArista("B", "C", 4);
        grafo.agregarArista("B", "H", 9);

        grafo.agregarArista("C", "A", 3);
        grafo.agregarArista("C", "B", 4);
        grafo.agregarArista("C", "H", 7);
        grafo.agregarArista("C", "D", 9);

        grafo.agregarArista("D", "A", 8);
        grafo.agregarArista("D", "C", 9);
        grafo.agregarArista("D", "E", 1);
        grafo.agregarArista("D", "H", 11);
        grafo.agregarArista("D", "M", 8);
        grafo.agregarArista("D", "T", 7);
        grafo.agregarArista("D", "R", 3);

        grafo.agregarArista("E", "A", 2);
        grafo.agregarArista("E", "D", 1);
        grafo.agregarArista("E", "R", 6);
        
        grafo.agregarArista("H", "B", 9);
        grafo.agregarArista("H", "C", 7);
        grafo.agregarArista("H", "D", 11);
        grafo.agregarArista("H", "M", 7);
        
        grafo.agregarArista("M", "H", 7);
        grafo.agregarArista("M", "D", 8);
        grafo.agregarArista("M", "T", 11);

        grafo.agregarArista("R", "E", 6);
        grafo.agregarArista("R", "D", 3);
        grafo.agregarArista("R", "T", 2);

        grafo.agregarArista("T", "R", 2);
        grafo.agregarArista("T", "D", 7);
        grafo.agregarArista("T", "M", 11);

        // grafo.mostrarGrafo();

        SalidaPorDefecto.consola("La distancia mas optima de A hacia T es: " + grafo.metricaOptima("A", "T"));
        SalidaPorDefecto.consola("\n");

        SalidaPorDefecto.consola("La ruta mas optima desde A hacia T es: ");
        grafo.rutaOptima("A", "T").mostrar();


    }
}
