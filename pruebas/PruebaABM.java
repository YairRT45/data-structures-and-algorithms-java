import static entradasalida.SalidaPorDefecto.consola;

import estructurasnolineales.ArbolBinarioMonticulo;
import utils.comunes.TipoOrdenamiento;

public class PruebaABM {
    public static void main(String[] args) {
        ArbolBinarioMonticulo abm = new ArbolBinarioMonticulo(TipoOrdenamiento.INC);

        abm.insertar(20);
        abm.insertar(18);
        abm.insertar(9);
        abm.insertar(8);
        abm.insertar(10);

        abm.mostrarPorAnchura();
        consola("\n");

        abm.insertar(12);

        abm.mostrarPorAnchura();
        consola("\n");

        // abm.insertar(30);
        abm.eliminar();
        abm.mostrarPorAnchura();
        consola("\n");
        // abm.innorden();


    }
}
