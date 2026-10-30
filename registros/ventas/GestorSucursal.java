package registros.ventas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;

public class GestorSucursal {
    protected ListaInfoEstatica ventas;
    protected String nombre;

    public GestorSucursal(String nombre, int maximoVentas) {
        this.nombre = nombre;
        ventas = new ListaInfoEstatica(maximoVentas);
    }

    public boolean agregarVenta(Venta ventaParam){
        int retornoVenta = ventas.insertar(ventaParam);
        if (retornoVenta >= 0) {
            return true;
        }else{
            return false;
        }
    }

    public void mostrarVentas(){
        //Recorrer todas las ventas
        for(int cadaVenta = 0; cadaVenta < ventas.cantidad(); cadaVenta++){
            Venta ventaTemp = (Venta) ventas.obtener(cadaVenta);
            ventaTemp.mostrarNota();
            SalidaPorDefecto.consola("\n");
        }
    }
}
