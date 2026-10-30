// package pruebas;

import registros.ventas.Cliente;
import registros.ventas.GestorSucursal;
import registros.ventas.Producto;
import registros.ventas.Vendedor;
import registros.ventas.Venta;

public class PruebaVentas {
    public static void main(String[] argumentosValidosHoy) {
        GestorSucursal sucursal1 = new GestorSucursal("EMP SA", 4);

        Vendedor vendedor1 = new Vendedor(1, "Jelipe", "01/01/00", 'M');
        Cliente cliente1 = new Cliente("BEDF010100F87", "Pedro", 34);

        Venta venta1 = new Venta("001", "06/02/24", vendedor1, cliente1, 10);

        Producto producto1 = new Producto("111", "Lapiz", 200, 35.0);
        Producto producto2 = new Producto("222", "Libreta", 300, 55.0);
        Producto producto3 = new Producto("333", "Goma", 100, 5.0);

        venta1.agregarProducto(producto1, 2);
        venta1.agregarProducto(producto2, 5);
        venta1.agregarProducto(producto3, 4);

        sucursal1.agregarVenta(venta1);
        sucursal1.mostrarVentas();
    }
}
