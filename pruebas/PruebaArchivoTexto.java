// package pruebas;

import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.archivos.ArchivoTexto;
import estructuraslineales.ListaInfoEstatica;
import verificador.VerificadorDeErrores;

public class PruebaArchivoTexto {
    static ArchivoTexto lectorArchivos;
    static VerificadorDeErrores verificador;

    public static void main(String[] args) {
        lectorArchivos = new ArchivoTexto();
        // verificador = new VerificadorDeErrores("C:\\Users\\ryrt3\\OneDrive\\Documentos\\Ing. Software\\4to semestre\\Hola.txt");
        verificador = new VerificadorDeErrores("C:\\Users\\ryrt3\\OneDrive\\Documentos\\Ing. Software\\4to semestre\\Lab. Estructura de Datos\\Proyecto\\Matematicas.txt");

        verificador.verificarVariables();
        verificador.mostrarVariables();
        // if (verificador.tokenizarRenglones()) {
        //     consola("Bien tokenizado\n");
        // } else {
        //     consola("Error");
        // }
        // verificador.separarPorPalabras();
        // verificador.mostrarPila();
        // verificador.verificarBalance();
        // verificador.verificarComentarios();
        // consola("Verifico todo\n");
        // verificador.mostrarPila();
        // verificador.mostrarErrores();
        // verificador.mostrarRenglones();
        // ListaInfoEstatica lista = lectorArchivos.leer("C:\\Users\\ryrt3\\OneDrive\\Documentos\\Ing. Software\\4to semestre\\Hola.txt");
        // lista.mostrar();
        // consola(lista.cantidad() + " " + lista.capacidad());
    }
}
