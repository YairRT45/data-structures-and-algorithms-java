import static entradasalida.SalidaPorDefecto.consola;

import estructurasnolineales.GrafoDinamico;

public class PruebaGrafoDinamico {
    public static void main(String[] args) {
        GrafoDinamico grafo = new GrafoDinamico();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        // grafo.agregarVertice("Z");

        grafo.agregarArista("A", "B");
        // grafo.agregarArista("A", "E");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "C");
        grafo.agregarArista("B", "E");
        grafo.agregarArista("C", "E");
        grafo.agregarArista("C", "D");
        grafo.agregarArista("D", "C");
        grafo.agregarArista("D", "E");
        grafo.agregarArista("E", "C");

        // grafo.agregarArista("E", "B");

        grafo.mostrar();
        consola("\n");

        // if (grafo.esArbol()) {
        //     consola("El grafo es un árbol");
        // } else {
        //     consola("El grafo no es un árbol");
        // }

        // if (grafo.esCaminoSimple("A", "D")) {
        //     consola("El camino de A hasta D es simple");
        // } else {
        //     consola("El camino de A hasta D no es simple");
        // }
        // if (grafo.hayCaminoCerrado("E")) {
        //     consola("E si tiene camino cerrado");
        // } else {
        //     consola("E no tiene camino cerrado");
        // }
        // grafo.recorridoProfundidad("C").mostrar();
        // consola("\n");
        // if (grafo.esConexo()) {
        //     consola("El grafo es conexo");
        // } else {
        //     consola("El grafo no es conexo");
        // }

        // if (grafo.hayRuta("A", "A")) {
        //     consola("Hay ruta para llegar desde A hasta D");
        // } else {
        //     consola("No hay ruta para llegar desde A hasta D");
        // }
        // if (grafo.esMultigrafo()) {
        //     consola("Sí es multigrafo");
        // } else {
        //     consola("No es multigrafo");
        // }
        // grafo.listarAristas();
        // grafo.listarVertices();
        // consola("Grado del vertice B: " + grafo.gradoVertice("B"));

        // if (grafo.esPseudografo()) {
        //     consola("Sí es pseudografo");
        // } else {
        //     consola("No es pseudografo");
        // }
        // consola("Buscando B : " + grafo.buscarVertice("B"));

        // if (grafo.esAdyacente("E", "E")) {
        //     consola("D es adyacente con E");
        // } else {
        //     consola("No son adyacentes entre si");
        // }
        // consola("\n");
        // if (grafo.esAdyacente("D", "B")) {
        //     consola("D es adyacente con B");
        // } else {
        //     consola("D no es adyacente de B");
        // }
        // grafo.eliminarVertice("c");
        // // grafo.eliminarArista("C", "E");
        // consola("Eliminando vertice C\n");
        // grafo.mostrar();

        // grafo.recorridoProfundidad("A").mostrar();
    }
}
