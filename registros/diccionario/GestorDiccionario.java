package registros.diccionario;

import static entradasalida.EntradaPorDefecto.consolaCadenas;
import static entradasalida.SalidaPorDefecto.consola;

import entradasalida.EntradaPorDefecto;

public class GestorDiccionario {
    protected Diccionario diccionario = new Diccionario();

    public void correr(){
        boolean programa = true;
        while (programa) {
            menuPrincipal();
        }
    }

    private void menuPrincipal(){
        consola("""
            \n1. Agregar concepto
            2. Consultar concepto
            3. Buscar por palabra clave
            4. Mostrar todos
            Selecciona una opcion: \n""");
        int seleccion = EntradaPorDefecto.consolaInt();
        consola("\n");
        switch (seleccion) {
            case 1:
                agregarConcepto();
                break;
            case 2:
                buscarConcepto();
                break;
            case 3:
                buscarPalabraClave();;
                break;
            case 4:
                mostrarTodos();
                break;
            default:
                break;
        }
    }

    private boolean agregarConcepto(){
        return diccionario.agregarConcepto();
    }

    private void buscarConcepto(){
        consola("Concepto a buscar: ");
        String concepto = consolaCadenas();
        diccionario.buscarConcepto(concepto);
    }

    private void buscarPalabraClave(){
        consola("Ingrese la palabra clave: ");
        String palabra = consolaCadenas();
        diccionario.mostrarConceptos(palabra);
    }

    private void mostrarTodos(){
        diccionario.mostrarTodos();
    }
}
