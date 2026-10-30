// package pruebas;

import entradasalida.SalidaPorDefecto;
import registros.arrocera.Campesino;
import registros.arrocera.GestorCosecha;

public class PruebaArrocera {
    

    public static void main(String[] args) {
        Campesino campesino1 = new Campesino("Joaquin");
        Campesino campesino2 = new Campesino("Alexis");
        GestorCosecha gestor = new GestorCosecha(); 

        campesino1.rellenarCosechas();
        campesino2.rellenarCosechas();
        campesino1.getCosecha1().getCosechaAnual().mostrar();
        
        SalidaPorDefecto.consola("El promedio anual es: " + campesino1.getCosecha1().promedioAnual() + "\n");
        SalidaPorDefecto.consola("El peor mes fue el: " + gestor.obtenerNombreMes(campesino1.getCosecha1().peorMes()) + "\n");
        SalidaPorDefecto.consola("La cantidad del segundo trimestre fue: " + campesino1.getCosecha1().cantidadPorTrimestre() + "\n");
        SalidaPorDefecto.consola("Las toneladas del ultimo mes son: " + campesino1.getCosecha1().toneladasUltimoMes() + "\n");
        SalidaPorDefecto.consola("El mejor mes fue: " + gestor.obtenerNombreMes(campesino1.getCosecha1().mejorMes()) + "\n");

        campesino2.getCosecha1().getCosechaAnual().mostrar();
        gestor.mostrarPeorPorAnio(campesino1, campesino2);
        gestor.mostrarMejorEpoca(campesino2);
    }
}
