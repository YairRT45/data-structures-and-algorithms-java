package registros.calculadora;

import static entradasalida.SalidaPorDefecto.consola;
import static entradasalida.EntradaPorDefecto.consolaDouble;

import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.ListaInfoEstaticaNumerica;
import utils.matematicas.ExpresionesAritmeticas;

public class Calculadora {
    protected static ListaInfoEstatica listaVariables;
    protected static ListaInfoEstaticaNumerica listaValores;



    public static boolean encontrarVariables(String expresion){
        listaVariables = new ListaInfoEstatica(expresion.length());
        listaValores = new ListaInfoEstaticaNumerica(expresion.length());

        for (int posToken = 0; posToken < expresion.length(); posToken++) {
            char token = expresion.charAt(posToken);
            if (esVariable(token + "")) {
                listaVariables.insertar(token + "");
            }
        }
        return true;
    }

    public static boolean pedirValoresVariables(){
        for (int pos = 0; pos < listaVariables.cantidad(); pos++) {
            consola("Ingresa el valor de la variable '" + listaVariables.obtener(pos) + "': ");
            double valor = consolaDouble();
            listaValores.insertar(valor);
        }
        return true;
    }

    public static boolean esVariable(String token){
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";

        for (int letra = 0; letra < alfabeto.length(); letra++) {
            if (token.equals(alfabeto.charAt(letra) + "")) {
                return true; //Si se encuentra, si es una variable
            } 
        }
        return false;//No es variable
    }

    public static Double evaluar(String expresion){
        String expPosfija = ExpresionesAritmeticas.infijaPosfija(expresion);
        Double resultado = ExpresionesAritmeticas.evaluarPosfija(expPosfija, listaVariables, listaValores);
        return resultado;
    }
    
}
