package registros.arrocera;

import entradasalida.SalidaPorDefecto;

public class GestorCosecha {
    
    private String comparar(double promedio1,double promedio2){//Compara el rendimiento de los campesinos y regresa una cadena indicando cual fue mejor

        if (promedio1 > promedio2){
            return "Campesino 1";
        } else if( promedio1 < promedio2){
            return "Campesino 2";
        } else{
            return "Los dos obtuvieron la misma cantidad este año";
        }
    }

    public void mostrarPeorPorAnio(Campesino campesino1, Campesino campesino2){ //Es un metodo para solo mostrar de forma ordenada cual campesino fue mejor en cada año
        SalidaPorDefecto.consola("¿Quién trabajó más por año? \n" );
        SalidaPorDefecto.consola("Numero de Año\t  \n");
        SalidaPorDefecto.consola("1\t" + comparar(campesino1.getCosecha1().promedioAnual(), campesino2.getCosecha1().promedioAnual()) + "\n");
        SalidaPorDefecto.consola("2\t" + comparar(campesino1.getCosecha2().promedioAnual(), campesino2.getCosecha2().promedioAnual()) + "\n");
        SalidaPorDefecto.consola("3\t" + comparar(campesino1.getCosecha3().promedioAnual(), campesino2.getCosecha3().promedioAnual()) + "\n");
        SalidaPorDefecto.consola("4\t" + comparar(campesino1.getCosecha4().promedioAnual(), campesino2.getCosecha4().promedioAnual()) + "\n");
    }

    public void mostrarMejorEpoca(Campesino campesino){//Muestra cual fue la epoca mas productiva de un año
        int epocaAnio1 = campesino.getCosecha1().mejorMesEpoca(campesino.getCosecha1().mejorEpoca());
        int epocaAnio2 = campesino.getCosecha2().mejorMesEpoca(campesino.getCosecha2().mejorEpoca());
        int epocaAnio3 = campesino.getCosecha3().mejorMesEpoca(campesino.getCosecha3().mejorEpoca());
        int epocaAnio4 = campesino.getCosecha4().mejorMesEpoca(campesino.getCosecha4().mejorEpoca());
        SalidaPorDefecto.consola("Mejor epoca por año\n");
        SalidaPorDefecto.consola("Numero de año \n");
        SalidaPorDefecto.consola("1\t" + obtenerNombreEpoca(epocaAnio1) + "\n");
        SalidaPorDefecto.consola("2\t" + obtenerNombreEpoca(epocaAnio2) + "\n");
        SalidaPorDefecto.consola("3\t" + obtenerNombreEpoca(epocaAnio3) + "\n");
        SalidaPorDefecto.consola("4\t" + obtenerNombreEpoca(epocaAnio4) + "\n");
    }

    public String obtenerNombreEpoca(int mejorMes){//Regresa la cadena indicando el nombre de la epoca segun el indice ingresado
        switch (mejorMes) {
            case 0:
                return "Primavera";
            case 1:
                return "Verano";
            case 2:
                return "Otoño";
            case 3:
                return "Invierno";
            default:
                return "";
        }
    }

    public String obtenerNombreMes(int mes){//Regresa la cadena indicando el nombre del mes segun el indice ingresado
        switch (mes) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "";
        }
    }
}
