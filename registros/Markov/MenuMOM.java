package registros.Markov;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructuraslineales.ColaInfoDinamica;

import javax.swing.*;

public class MenuMOM {

    private MOM mom;

    public MenuMOM(){
        mom = new MOM(4, 3);
    }
    
    public void correrMenu (){
        boolean stop = false;
        while (stop == false) {
            SalidaPorDefecto.consola("----------------------------\n MODELO OCULTO DE MARKOV\n----------------------------\n");
            mostrarMenuOpciones();
            switch (elegirOpcion()) {
                case 1:
                    mostrarImagen(); 
                    break;
                case 2: 
                    mostrarPreguntas();
                    break;
                default:
                    break;
            }
        }
    }

    private void mostrarMenuOpciones (){
        SalidaPorDefecto.consola("1. Mostrar grafo\n" + "2. Responder Preguntas\n");
    }

    private int elegirOpcion (){
        SalidaPorDefecto.consola("Elige la opción: ");
        int opcionElegida = EntradaPorDefecto.consolaDouble().intValue();
        return opcionElegida;
    }

    private void mostrarPreguntas (){

        SalidaPorDefecto.consola("\n1.Probabilidad de que su amigo empiece el día con un estado de ánimo específico: \n" 
                                +"2.Probabilidad de que su amigo haga cierta actividad dependiendo de su estado de ánimo:\n" 
                                +"3.Probabilidad de que su amigo esté en cierto estado:  \n"
                                +"4.Probabilidad de que se de cierta secuencia de estados de ánimo: \n");
        responderPreguntas(elegirOpcion());
    }

    private void responderPreguntas (int tipo){
        switch (tipo) {
            case 1:
                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo empiece el día neutral?  ");
                SalidaPorDefecto.consola(mom.probabilidadInicio("Neutral")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo empiece el día feliz?  ");
                SalidaPorDefecto.consola(mom.probabilidadInicio("Feliz")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo empiece el día enojado?  ");
                SalidaPorDefecto.consola(mom.probabilidadInicio("Enojado")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo empiece el día triste?  ");
                SalidaPorDefecto.consola(mom.probabilidadInicio("Triste")+"%\n " + "\n \n");
                break;
            case 2:
                SalidaPorDefecto.consola("¿ Cuál es la probabilidad de  su amigo juegue?");
                SalidaPorDefecto.consola(mom.probabilidadActividad("Jugar") +"%\n");

                SalidaPorDefecto.consola("¿ Cuál es la probabilidad de su amigo llore?");
                SalidaPorDefecto.consola(mom.probabilidadActividad("Llorar") +"%\n");
                
                SalidaPorDefecto.consola("¿ Cual es la probabilidad de su amigo reflexione?");
                SalidaPorDefecto.consola(mom.probabilidadActividad("Reflexionar") +"%\n"+"\n \n");
                
                break;
            case 3:
                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo este triste?");
                SalidaPorDefecto.consola(mom.probabilidadSiguienteEstado("Triste")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo este feliz?");
                SalidaPorDefecto.consola(mom.probabilidadSiguienteEstado("Feliz")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo este neutral?");
                SalidaPorDefecto.consola(mom.probabilidadSiguienteEstado("Neutral")+"%\n");

                SalidaPorDefecto.consola("¿Cuál es la probabilidad de que su amigo este enojado?");
                SalidaPorDefecto.consola(mom.probabilidadSiguienteEstado("Enojado")+"%\n"+"\n \n");

                break;
            case 4:
                ColaInfoDinamica secuencia = crearSecuencia();
                secuencia.mostrar();
                SalidaPorDefecto.consola("\nProbabilidad de la secuencia: ");
                
                SalidaPorDefecto.consola(mom.probabilidadSecuencia(secuencia)+"%\n");
                break;
            default:
                break;
        }
    }

    private ColaInfoDinamica crearSecuencia (){
        ColaInfoDinamica cola = new ColaInfoDinamica();
        String estado = subMenuEstados();
        while (estado !=null) {
            cola.poner(estado);
            estado = subMenuEstados();
            
        }
        return cola;
    }

    private String subMenuEstados (){
        SalidaPorDefecto.consola("1.Feliz \n2.Triste \n3.Enojado \n4.Neutral \n5.Fin\n");
        String estado = "";
        switch (elegirOpcion()) {
            case 1:
                estado =  "Feliz";
                break;
            case 2:
                estado = "Triste";
                break;
            case 3:
                estado =  "Enojado";
                break;
            case 4:
                estado = "Neutral";  
                break;
            case 5:
                estado = null;
            default:
                SalidaPorDefecto.consola("Ingresa una opción válida");
        }
        return estado;
    }

    private void mostrarImagen (){
        SwingUtilities.invokeLater(() -> {
            Imagen grafoMOM = new Imagen();
            grafoMOM.setVisible(true);
        });
    }
}