package registros.cosechas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;
import estructurasnolineales.MatrizInfo3;

public class GestorCosechas {
    protected MatrizInfo3 toneladas;
    protected ListaInfoEstatica campesinos;
    protected ListaInfoEstatica productos;
    protected ListaInfoEstatica anios;

    public GestorCosechas(int numCampesinos, int numProductos, int numAnios){
        toneladas = new MatrizInfo3(numCampesinos, numProductos, numAnios);
        campesinos = new ListaInfoEstatica(numCampesinos);
        productos = new ListaInfoEstatica(numProductos);
        anios = new ListaInfoEstatica(numAnios);
        toneladas.rellenar(0.0);
    }

    public boolean agregarCampesino(Campesino camp){
        int indiceCampesino = (int) campesinos.encontrar(camp);
        if (indiceCampesino >= 0) { //ya existe
            return false;
        }else{//No existe
            int retornoAgregado = campesinos.insertar(camp);
            if (retornoAgregado >= 0){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agregarProducto(String producto){
        int indiceProducto = (int) productos.encontrar(producto);
        if (indiceProducto >= 0) { //ya existe
            return false;
        }else{//No existe
            int retornoAgregado = productos.insertar(producto);
            if (retornoAgregado >= 0){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agregarAnio(int anio){
        int indiceAnio = (int) anios.encontrar(anio);
        if (indiceAnio >= 0) { //ya existe
            return false;
        }else{//No existe
            int retornoAgregado = anios.insertar(anio);
            if (retornoAgregado >= 0){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agregarToneladas(String rfc, String producto, int anio, double tonProd){
        ListaInfoEstatica indices = indicesCelda(rfc, producto, anio);
        if (indices != null) { //Son validos
            return toneladas.cambiar((int) indices.obtener(0), //Campesino
                                     (int) indices.obtener(1), //Producto
                                     (int) indices.obtener(2), //Anio
                                     tonProd);
        } else { //No son validos
            return false;
        }
    }

    private ListaInfoEstatica indicesCelda(String rfc, String producto, int anio){
        int indiceCampesino = (int)campesinos.encontrar(rfc);
        int indiceProducto = (int)productos.encontrar(producto);
        int indiceAnio = (int)anios.encontrar(anio);

        if (indiceCampesino >= 0 && indiceProducto >= 0 && indiceAnio >= 0) {
            ListaInfoEstatica indices = new ListaInfoEstatica(3);
            indices.insertar(indiceCampesino);
            indices.insertar(indiceProducto);
            indices.insertar(indiceAnio);
            return indices;
        }else{
            return null;
        }
    }

    public void mostrarCosechas(){
        SalidaPorDefecto.consola("Datos de cosechas \n\n");
        
        SalidaPorDefecto.consola("Rfcs de campesino: \n");
        campesinos.mostrar();

        SalidaPorDefecto.consola("\nProductos: \n");
        productos.mostrar();

        SalidaPorDefecto.consola("\nAños: \n");
        anios.mostrar();

        SalidaPorDefecto.consola("\nToneladas producidas: \n");
        toneladas.mostrarXColumna();
    } 

    public ListaInfoEstatica toneladasCampesinos(ListaInfoEstatica campesinosPedidos, ListaInfoEstatica productosPedidos, ListaInfoEstatica aniosPedidos){
        //Recorrer cada posición del arreglo de campesions pedidos
        // y calcular su producción de manera individual
        ListaInfoEstatica listaToneladasCampesinos = new ListaInfoEstatica(campesinosPedidos.cantidad());
        for(int cadaCampesino = 0; cadaCampesino < campesinosPedidos.cantidad(); cadaCampesino++){
            String rfcTemporal = campesinosPedidos.obtener(cadaCampesino).toString();
            Double toneladasCampesinoTemporal = toneladasXCampesino(rfcTemporal, productosPedidos, aniosPedidos);
            listaToneladasCampesinos.insertar(toneladasCampesinoTemporal);
        }
        return listaToneladasCampesinos;
    }

    public Double toneladasXCampesino(String rfc, ListaInfoEstatica productosPedidos, ListaInfoEstatica aniosPedidos){
        //Tengo que recorrer el arreglo de productos que fueron pedidos
        double toneladasAcumuladas = 0.0;
        for(int cadaProducto = 0; cadaProducto < productosPedidos.cantidad(); cadaProducto++){
            String productoTemporal = (String) productosPedidos.obtener(cadaProducto);
            //Voy a suponer que solo tengo que sacar los datos de toneladas de un solo producto por ejemplo frijol
            for (int cadaAnio = 0; cadaAnio < aniosPedidos.cantidad(); cadaAnio++){
                int anioTemporal = (int)aniosPedidos.obtener(cadaAnio);
                //Ahora tengo que obtener la cantidad de toneladas de un año nada más de un producto nada más del rfc que se me pidio
                
                //Primero obtenemos los indices del cubo para poder sacar del cubo las toneladas
                ListaInfoEstatica indicesCeldaPedida = indicesCelda(rfc, productoTemporal, anioTemporal);
                if (indicesCeldaPedida != null) {
                    Double toneladasCelda = (Double) toneladas.obtener((int) indicesCeldaPedida.obtener(0), //rfc
                                                                       (int) indicesCeldaPedida.obtener(1), //producto
                                                                       (int) indicesCeldaPedida.obtener(2));//anio
                    if (toneladasCelda != null) {
                        toneladasAcumuladas = toneladasAcumuladas + toneladasCelda;
                    }
                }
            }
        }
        return toneladasAcumuladas;
    }
}
