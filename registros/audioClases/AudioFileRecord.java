package registros.audioClases;

import java.io.*;
import estructuraslineales.ListaInfoEstaticaNumerica;

public class AudioFileRecord {
    private long numFrames;  //numero de tramas, número de muestras totales del archivo por canal
    private long sampleRate; //numero de muestras por segundo a la que se discretiza la señal
    private int numChannels; //número de canales
    private double[] buffer; //audio original
    //private double[] buffer2; //audio modificado
    private ListaInfoEstaticaNumerica buffer2; 
    private String archivo;   //nombre de archivo dado por el usuario
    private WavFile wavFileR; //apuntador de archivo leido
    private WavFile wavFileW;  //apuntador de archivo a escribir
    private String nomArchivo; //nombre archivo (uno.wav)
    private String nomRuta;    //ruta donde esta guardado el archivo (/home)
    private int validBits;     //bits usados para la discretización

    public AudioFileRecord(String archivo) {
        this.archivo = archivo;
        // Abre el archivo wav y asigna parámetros a las variables
        try {
            File file = new File(this.archivo);
            wavFileR = WavFile.openWavFile(file);
            nomArchivo = file.getName();
            nomRuta = file.getParent();
        } catch (Exception e) {

        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits=wavFileR.getValidBits();
    }

    public void leerAudio() {
        try {
            // Muestra datos del archivo
            wavFileR.display();

            // Crea buffer de origen y de temporal
            buffer = new double[(int) numFrames * numChannels];
            //buffer2 = new double[buffer.length];
            buffer2 = new ListaInfoEstaticaNumerica(buffer.length);

            //tramas leidas
            int framesRead;

            // Lee tramas totales
            framesRead = wavFileR.readFrames(buffer, (int) numFrames);

            // Recorrer todas las tramas del archivo y guardarlas en el arreglo.
            Object[] respaldoBuffer = new Object[buffer.length];
            for (int s = 0; s < framesRead * numChannels; s++) {
                respaldoBuffer[s] = buffer[s];
            }

            buffer2.convertirArray(respaldoBuffer);
            //buffer2.mostrar();
            // Cierra archivo
            wavFileR.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void EscribirAudio() {
        try {

            //Crear el apuntador al archivo para guardar datos del temporal
            File file = new File(nomRuta + "/2_" + nomArchivo);

            // Creamos un nuevo archivo de audio con los mismos datos que el original
            wavFileW = WavFile.newWavFile(file, numChannels, buffer2.cantidad(), validBits, sampleRate);


            double buffer3[] = new double[buffer2.cantidad()];
            for(int iterador = 0; iterador < buffer2.cantidad(); iterador++){
                buffer3[iterador] = (double)buffer2.obtener(iterador);
            }
            // Escribimos los frames o muestras totales del temporal
            wavFileW.writeFrames(buffer3, buffer2.cantidad());
            // Cerramos el archivo|
            wavFileW.close();
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void preEnfasis(){
        leerAudio();
        for(int indice = 0; indice < buffer2.cantidad(); indice++){
            buffer2.cambiar(indice, (double)buffer2.obtener(indice) + ((0.95) * ((double) buffer2.obtener(indice)) - 1) );
        }
        EscribirAudio();
    }

    public void subirVolumen(int intesidad){
        leerAudio();
        buffer2.porEscalar(intesidad);
        EscribirAudio();
    }

    public void bajarVolumen(int intensidad){
        leerAudio();
        buffer2.entreEscalar(intensidad);
        EscribirAudio();
    }

    public void acelerarPista(int incremento){
        leerAudio();
        buffer2.promediarConsecutivos(incremento);
        EscribirAudio();
    }

    public void retrasarPista(int decremento){
        leerAudio();
        buffer2.expandirLista(decremento);
        EscribirAudio();
    }

    public void invertirX(){
        leerAudio();
        buffer2.invertir();
        EscribirAudio();
    }

    public void invertirY(){
        leerAudio();
        buffer2.porEscalar(-1);
        EscribirAudio();
    }

    public void eliminarSilencio(){
        
    }

    public int obtenerEnergiaVocal(){
        leerAudio();
        buffer2.aplicarPotencia(2);
        int energia = (int)buffer2.sumarTodo(buffer2);
        return energia;
    }
}
