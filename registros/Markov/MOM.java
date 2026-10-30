package registros.Markov;

import estructuraslineales.ColaInfoDinamica;
import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.ListaInfoEstaticaNumerica;
import estructurasnolineales.GrafoEstatico;
import estructurasnolineales.MatrizInfo2;

public class MOM {


    protected MatrizInfo2 emision;
    protected ListaInfoEstatica estados;
    protected ListaInfoEstatica observaciones;
    protected ListaInfoEstaticaNumerica probabilidad_Inicial;
    protected GrafoEstatico modelo;


    public MOM(int numEstados, int numObservaciones){

        emision = new MatrizInfo2(numEstados, numObservaciones);
        estados = new ListaInfoEstatica(numEstados);
        observaciones = new ListaInfoEstatica(numObservaciones);
        probabilidad_Inicial = new ListaInfoEstaticaNumerica(numEstados);
        modelo = new GrafoEstatico(numEstados);
        incializarMOM();
        
    }

    private void incializarMOM (){
        // estados
        estados.insertar("Feliz");
        estados.insertar("Triste");
        estados.insertar("Enojado");
        estados.insertar("Neutral");
    
        // observaciones
        observaciones.insertar("Jugar");
        observaciones.insertar("Llorar");
        observaciones.insertar("Reflexionar");

        // probabilidades iniciales
        probabilidad_Inicial.insertar(0.3);
        probabilidad_Inicial.insertar(0.3);
        probabilidad_Inicial.insertar(0.2);
        probabilidad_Inicial.insertar(0.2);

        // transición
        modelo.agregarVertice("Feliz");
        modelo.agregarVertice("Triste");
        modelo.agregarVertice("Enojado");
        modelo.agregarVertice("Neutral");

        modelo.agregarArista("Feliz", "Feliz", 0.5);
        modelo.agregarArista("Feliz", "Triste", 0.1);
        modelo.agregarArista("Feliz", "Enojado", 0.1);
        modelo.agregarArista("Feliz", "Neutral", 0.3);

        modelo.agregarArista("Triste", "Feliz", 0.1);
        modelo.agregarArista("Triste", "Triste", 0.5);
        modelo.agregarArista("Triste", "Enojado", 0.2);
        modelo.agregarArista("Triste", "Neutral", 0.2);

        modelo.agregarArista("Enojado", "Feliz", 0.1);
        modelo.agregarArista("Enojado", "Triste", 0.3);
        modelo.agregarArista("Enojado", "Enojado", 0.2);
        modelo.agregarArista("Enojado", "Neutral", 0.4);

        modelo.agregarArista("Neutral", "Feliz", 0.3);
        modelo.agregarArista("Neutral", "Triste", 0.2);
        modelo.agregarArista("Neutral", "Enojado", 0.3);
        modelo.agregarArista("Neutral", "Neutral", 0.2);


        // emision
        emision.cambiar(0, 0, 0.7);
        emision.cambiar(0, 1, 0.1);
        emision.cambiar(0, 2, 0.2);

        emision.cambiar(1, 0, 0.1);
        emision.cambiar(1, 1, 0.6);
        emision.cambiar(1, 2, 0.3);

        emision.cambiar(2, 0, 0.1);
        emision.cambiar(2, 1, 0.3);
        emision.cambiar(2, 2, 0.6);

        emision.cambiar(3, 0, 0.4);
        emision.cambiar(3, 1, 0.3);
        emision.cambiar(3, 2, 0.3);
    }

    public double probabilidadInicio(String estado){
        int indice = (int)estados.encontrar(estado);
        return (double)probabilidad_Inicial.obtener(indice) * 100;
    }

    public double probabilidadActividad(String actividad){
        double probabilidadSuma = 0.0;
        int indiceObservacion = (int) observaciones.encontrar(actividad);
        for (int cadaEstado = 0; cadaEstado < estados.cantidad(); cadaEstado++){
            probabilidadSuma = probabilidadSuma + (double) emision.obtener(cadaEstado, indiceObservacion);
        }
        return Math.round(probabilidadSuma / estados.cantidad() * 100);
    }

    public double probabilidadSiguienteEstado(String estado){
        double probabilidadSuma = 0.0;
        int indiceEstadoSig = (int) estados.encontrar(estado);
        for (int cadaEstado = 0; cadaEstado < estados.cantidad(); cadaEstado++){
            probabilidadSuma = probabilidadSuma + modelo.adyacencia(cadaEstado, indiceEstadoSig);
        }
        return Math.round(probabilidadSuma / estados.cantidad() * 100) ;
    }

    public double probabilidadSecuencia(ColaInfoDinamica secuencia){ 
        if (secuencia.vacio()) {
            return 0.0;
        }

        String estadoActual = (String)secuencia.quitar();
        double probabilidad = probabilidadInicio(estadoActual) / 100;
        if (secuencia.vacio()) {
            return probabilidad * 100;
        }

        String estadoSiguiente = (String)secuencia.quitar();
        int indiceActual = (int)estados.encontrar(estadoActual);
        int indiceSiguiente = (int)estados.encontrar(estadoSiguiente);
        
        probabilidad = probabilidad * modelo.adyacencia(indiceActual, indiceSiguiente);
        while (secuencia.vacio() == false) {
            estadoActual = estadoSiguiente;
            estadoSiguiente = (String)secuencia.quitar();   
            indiceActual = indiceSiguiente;
            indiceSiguiente = (int)estados.encontrar(estadoSiguiente);
            probabilidad = probabilidad * modelo.adyacencia(indiceActual, indiceSiguiente);
        }
        return probabilidad * 100;
    }

}