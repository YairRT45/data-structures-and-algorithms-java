import static entradasalida.SalidaPorDefecto.consola;

import estructurasnolineales.ArbolBBD;

public class PruebaABBD {
    
    public static void main(String[] args) {
        ArbolBBD arbol = new ArbolBBD("src\\tablasDB\\order_items.csv");
        try {
            arbol.agregarTabla();
        } catch (Exception e) {
            // TODO: handle exception
        }
        arbol.innorden();
        arbol.mostrarRegistro(2460);
        consola("\n");
        arbol.borrarRegistro(2460);
        arbol.innorden();
        consola("\n");
        arbol.mostrarRegistro(2393);
        // try {
        //     arbol.agregarRegistro();
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        // arbol.innorden();
        // consola("\n");
        // arbol.mostrarRegistro((int)arbol.encontrarUltimo());
    }
}
