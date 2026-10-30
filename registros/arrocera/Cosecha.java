package registros.arrocera;

import java.util.Random;
import estructuraslineales.ListaInfoEstatica;

public class Cosecha {
    protected ListaInfoEstatica cosechaAnual;

    public ListaInfoEstatica getCosechaAnual() {
        return cosechaAnual;
    }

    public Cosecha() {
        cosechaAnual = new ListaInfoEstatica(12);
    }

    public void llenarListaCosecha(){ //Asigna a todos los campos de la lista un valor random
        Random random = new Random();
        for (int indice = 0; indice < cosechaAnual.capacidad(); indice++) {
            int tonelada = random.nextInt(15);
            cosechaAnual.insertar(tonelada);
        }
    }

    public double promedioAnual(){ //Calcula el promedio de todos los elementos que estan en la lista
        int sumaTotal = 0;
        for (int mes = 0; mes < cosechaAnual.capacidad(); mes++){
            int toneladas = (int)cosechaAnual.obtener(mes);
            sumaTotal = sumaTotal + toneladas;
        }
        double promedio = (double)sumaTotal / 12;
        return promedio;
    }

    public int peorMes(){ //Obtiene el indice del mes con la menor cantidad de toneladas
        int menor = 0;
        for (int mes = 1; mes < cosechaAnual.capacidad(); mes++){
            if ((int)cosechaAnual.obtener(mes) < (int)cosechaAnual.obtener(menor) == true){
                menor = mes; //Si las toneladas del mes es menor al que ya estaba pensado se reemplazara el indice con el que tenga menor
            } 
        }
        return menor;
    }

    public int cantidadPorTrimestre(){ //Regresa la cantidad de toneladas obtenidas en el intervalo de abril a junio
        ListaInfoEstatica trimestre = cosechaAnual.subLista(3, 5);
        int totalTrimestre = 0;
        for (int mes = 0; mes < trimestre.capacidad(); mes++){
            int toneladas = (int)trimestre.obtener(mes);
            totalTrimestre = totalTrimestre + toneladas;
        }
        return totalTrimestre;
    }

    public int toneladasUltimoMes(){ //Regresa el valor de la ultima posicion es decir del mes de diciembre
        return (int)cosechaAnual.obtener(11);
    }

    public int mejorMes(){//Regresa el indice del mes con mayor cantidad de toneladas
        int mejor = 0;
        for (int mes = 1; mes < cosechaAnual.capacidad(); mes++){
            if ((int)cosechaAnual.obtener(mes) > (int)cosechaAnual.obtener(mejor) == true){
                mejor = mes; //Si las toneladas del mes es menor al que ya estaba pensado se reemplazara el indice con el que tenga menor
            } 
        }
        return mejor;
    }

    public int mejorMesEpoca(ListaInfoEstatica epoca){//Regresa el indice del mes con mejor aprovechamiento en los intervalos de epocas
        int mejor = 0;
        for (int mes = 1; mes < epoca.capacidad(); mes++){
            if ((double)epoca.obtener(mes) > (double)epoca.obtener(mejor) == true){
                mejor = mes; //Si las toneladas del mes es menor al que ya estaba pensado se reemplazara el indice con el que tenga menor
            } 
        }
        return mejor;
    }

    public ListaInfoEstatica mejorEpoca(){//Retornara una lista con las 4 epocas para así poder encontrar la de mejor rendimiento
        ListaInfoEstatica primavera = cosechaAnual.subLista(2, 4);
        ListaInfoEstatica verano = cosechaAnual.subLista(5, 7);
        ListaInfoEstatica otono = cosechaAnual.subLista(8, 10);
        ListaInfoEstatica invierno = new ListaInfoEstatica(3);
        invierno.insertar((int)cosechaAnual.obtener(11));
        invierno.insertar((int)cosechaAnual.obtener(0));
        invierno.insertar((int)cosechaAnual.obtener(1));
        double promedioPrimavera = promedioEpocas(primavera);
        double promedioVerano = promedioEpocas(verano);
        double promedioOtono = promedioEpocas(otono);
        double promedioInvierno = promedioEpocas(invierno);

        ListaInfoEstatica epocas = new ListaInfoEstatica(4);
        epocas.insertar(promedioPrimavera);
        epocas.insertar(promedioVerano);
        epocas.insertar(promedioOtono);
        epocas.insertar(promedioInvierno);
        return epocas;
    }

    public double promedioEpocas(ListaInfoEstatica epoca){//Obtiene el promedio de cada intervalo de epocas
        int sumaTotal = 0;
        for (int mes = 0; mes < epoca.capacidad(); mes++){
            int toneladas = (int)epoca.obtener(mes);
            sumaTotal = sumaTotal + toneladas;
        }
        double promedio = (double)sumaTotal / 12;
        return promedio;
    }
}

