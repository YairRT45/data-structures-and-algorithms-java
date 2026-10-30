package entradasalida.archivos;

import entradasalida.*;
import estructuraslineales.*;
import java.io.*;

public class ArchivoTexto {

    public static ListaInfoEstatica leer(String archivo){
        FileReader input = null;
        int registro = 0;
        ListaInfoEstatica datos=null;
        BufferedReader buffer = null;
        try {
            String cadena=null;
            input = new FileReader(archivo);
            buffer = new BufferedReader(input);
            datos = new ListaInfoEstatica ((int)buffer.lines().count());
            buffer.close();
            input.close();
            input = new FileReader(archivo);
            buffer = new BufferedReader(input);
            while((cadena = buffer.readLine()) != null) {
                datos.insertar(cadena);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                input.close();
                buffer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return datos;
    }

    public static void escribir(ListaInfoEstatica arreglo, String archivo){
        FileWriter output = null;
        try {
            output = new FileWriter(archivo);
            for(int posicion = 0; posicion < arreglo.cantidad() - 1 ; posicion++) {
                output.write((String)arreglo.obtener(posicion) + "\n");
            }
            output.write((String)arreglo.obtener(arreglo.cantidad() - 1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                output.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
