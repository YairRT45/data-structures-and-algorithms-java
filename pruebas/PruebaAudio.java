// package pruebas;

import entradasalida.SalidaPorDefecto;
import registros.audioClases.AudioFileRecord;

public class PruebaAudio {
    public static void main(String[] args) {
        //AudioFileRecord audioFile = new AudioFileRecord("..\\registros\\audioClases\\archivo.wav");
        AudioFileRecord audioFile = new AudioFileRecord("C:\\Users\\ryrt3\\OneDrive\\Documentos\\Ing. Software\\4to semestre\\Lab. Estructura de Datos\\Proyecto\\src\\registros\\audioClases\\A1.wav");
        //audioFile.leerAudio();

        //audioFile.preEnfasis();

        //audioFile.subirVolumen(10);

        //audioFile.bajarVolumen(20);

        //audioFile.acelerarPista(3);

        //audioFile.retrasarPista(3);

        //audioFile.invertirX();

        //audioFile.invertirY();
        SalidaPorDefecto.consola("" + audioFile.obtenerEnergiaVocal());

    };
    
}
