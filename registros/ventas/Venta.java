package registros.ventas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;

public class Venta {
    protected String folio;
    protected String fecha;
    protected Vendedor cajero;
    protected Cliente cliente;

    protected ListaInfoEstatica articulos; //arreglo de eproductos
    protected ListaInfoEstatica cantidad; //cuantos de cada articulo

    protected double total;

    public Venta(String folio, String fecha, Vendedor cajero, Cliente cliente, int cantidadMaximaArticulos) {
        this.folio = folio;
        this.fecha = fecha;
        this.cajero = cajero;
        this.cliente = cliente;
        articulos = new ListaInfoEstatica(cantidadMaximaArticulos);
        cantidad = new ListaInfoEstatica(cantidadMaximaArticulos);
    }

    public boolean agregarProducto(Producto articulo, int cant){
        // int resultadoB = (Integer)articulos.encontrar(articulo);
        // if (resultadoB >= 0) { //ya existe
            
        // }else{ //No existe

        // }
        int retornoArt = articulos.insertar(articulo);
        if (retornoArt < 0) { //hubo error
            return false;   
        }
        int retornoCant = cantidad.insertar(cant);
        if (retornoCant < 0) { //hubo error
            return false;
        }
        return true; //Si llega hasta acá, quiere decir que no hubo error
    }

    public void calcularTotal(){
        double totalTemp = 0.0;
        for(int consecutivoArticulo = 0; consecutivoArticulo < articulos.cantidad(); consecutivoArticulo++ ){//Recorrer todos los productos
            Producto articuloTemp = (Producto) articulos.obtener(consecutivoArticulo);
            int cantidadTemp = (int)cantidad.obtener(consecutivoArticulo);
            double subTotal = articuloTemp.getPrecio() * cantidadTemp;
            totalTemp = totalTemp + subTotal;
        }
        total = totalTemp;
    }

    public void mostrarNota(){
        SalidaPorDefecto.consola("Nota de venta\n");
        //Encabezado
        SalidaPorDefecto.consola("\tFolio:" + folio +"\n");
        SalidaPorDefecto.consola("Vendedor: " + cajero.nombre + "\n");
        SalidaPorDefecto.consola("Cliente: " + cliente.nombre + "\n");
        SalidaPorDefecto.consola("\tFecha: " + fecha + "\n");
        SalidaPorDefecto.consola("\n");

        //Detalle
        SalidaPorDefecto.consola("Cons.\t Producto \t Precio \t Cantidad \t Subtotal \n");
        for (int consecutivoArticulo = 0; consecutivoArticulo < articulos.cantidad(); consecutivoArticulo++){
            Producto articuloTemp = (Producto) articulos.obtener(consecutivoArticulo);
            int cantidadTemp = (int) cantidad.obtener(consecutivoArticulo);

            SalidaPorDefecto.consola("" + (consecutivoArticulo+1) +"\t");
            SalidaPorDefecto.consola(articuloTemp.getDescripcion() + "\t");
            SalidaPorDefecto.consola(articuloTemp.getPrecio() + "\t");
            SalidaPorDefecto.consola(cantidadTemp + "\t");
            SalidaPorDefecto.consola(articuloTemp.getPrecio() * cantidadTemp + "\t");
            SalidaPorDefecto.consola("\n");
        }
        calcularTotal();
        SalidaPorDefecto.consola("Total: " + total);
    }
}
