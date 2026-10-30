// package pruebas;

import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstatica;
import registros.cosechas.Campesino;
import registros.cosechas.GestorCosechas;

public class PruebaCosechas {
    public static void main(String[] args) {
        GestorCosechas gestor = new GestorCosechas(4, 4, 5);
        Campesino campesino1 = new Campesino("JESA908976G94", "Jelipe", 20, 'H');
        Campesino campesino2 = new Campesino("REDS908976G93", "Rito", 39, 'H');
        Campesino campesino3 = new Campesino("ASDR208976G21", "Juan", 38, 'H');
        Campesino campesino4 = new Campesino("HGTF108976G27", "Antonio", 33, 'H');
        
        gestor.agregarCampesino(campesino1);
        gestor.agregarCampesino(campesino2);
        gestor.agregarCampesino(campesino3);
        gestor.agregarCampesino(campesino4);

        gestor.agregarProducto("frijol");
        gestor.agregarProducto("maíz");
        gestor.agregarProducto("ajo");
        gestor.agregarProducto("chile");

        gestor.agregarAnio(2010);
        gestor.agregarAnio(2003);
        gestor.agregarAnio(2020);
        gestor.agregarAnio(2023);
        gestor.agregarAnio(2000);

        gestor.agregarToneladas("JESA908976G94", "frijol", 2010, 40.0);
        gestor.agregarToneladas("JESA908976G94", "maíz", 2003, 80.0);
        gestor.agregarToneladas("ASDR208976G21", "maíz", 2003, 90.6);
        gestor.agregarToneladas("ASDR208976G21", "maíz", 2000, 100.6);
        gestor.agregarToneladas("HGTF108976G27", "ajo", 2010, 50.5);

        gestor.mostrarCosechas();

        SalidaPorDefecto.consola("\nDatos consultados:\n");
        ListaInfoEstatica campesinosPedidos = new ListaInfoEstatica(3);
        campesinosPedidos.insertar(campesino1);
        campesinosPedidos.insertar(campesino2);
        campesinosPedidos.insertar(campesino4);

        ListaInfoEstatica productosPedidos = new ListaInfoEstatica(3);
        productosPedidos.insertar("frijol");
        productosPedidos.insertar("maíz");
        productosPedidos.insertar("ajo");

        ListaInfoEstatica aniosPedidos = new ListaInfoEstatica(3);
        aniosPedidos.insertar(2000);
        aniosPedidos.insertar(2023);
        aniosPedidos.insertar(2003);

        campesinosPedidos.mostrar();
        SalidaPorDefecto.consola("\n");
        productosPedidos.mostrar();
        SalidaPorDefecto.consola("\n");
        aniosPedidos.mostrar();
        SalidaPorDefecto.consola("\n");
        ListaInfoEstatica toneladasTotales = gestor.toneladasCampesinos(campesinosPedidos, productosPedidos, aniosPedidos);
        toneladasTotales.mostrar();
        SalidaPorDefecto.consola("\n");
    }
}
