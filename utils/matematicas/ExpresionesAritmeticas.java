package utils.matematicas;

// import static entradasalida.SalidaPorDefecto.consola;

import estructuraslineales.ListaInfoEstatica;
import estructuraslineales.ListaInfoEstaticaNumerica;
import estructuraslineales.PilaInfoEstatica;

public class ExpresionesAritmeticas {


    public static Double evaluarInfija(String infija){
        //Este metodo llamara primero a uno que convierta a infija a posfija o prefija
        //Posteriormente llamaría al metodo evaluarPosfija o evaluarPrefija
        return null;
    }

    public static Double evaluarPosfija(String posfija){
        PilaInfoEstatica pila = new PilaInfoEstatica(posfija.length());
        String numActual = "";
        for (int posToken = 0; posToken < posfija.length(); posToken++){
            //1. Tokenizar la expPos de izq -> der
            char token = posfija.charAt(posToken);
            
            //2. Si el token es operando, meterlo en la pila (agregarlo a la variable numActual)
            if (esVariable(token +"")) {
                if (numActual.length() > 0) {
                    if (pila.poner(numActual) == false) {
                        return null;
                    } 
                    numActual = "";
                }
                if (pila.poner(token + "") == false) {
                    return null;
                }
            } else if (esOperando(token + "") == true) {
                numActual = numActual + token;
                // if (pila.poner(token + "") == false) {
                //     return null;
                // }
            } else { //es operador primero agregar el numero actual si es que existe
                if (numActual.length() > 0) {
                    if (pila.poner(numActual) == false) {
                        return null;
                    } 
                    // if (pila.poner(token + "") == false) {
                    //     return null;
                    // }
                    numActual = "";
                }
            //     //3. Si el token es operador, sacar 2 operandos de loa pila(el primero extraido es op2) aplicar la operacion, finalmente meter el resultado parcial en la pila
            //     String operando2 = (String) pila.quitar();
            //     String operando1 = (String) pila.quitar();

            //     if (operando1 != null && operando2 != null) {
            //         Double resultadoParcial = operacion(Double.parseDouble(operando1), Double.parseDouble(operando2), token);
            //         if (resultadoParcial != null) {
            //             if (pila.poner(resultadoParcial + "") == false) {
            //                 return null;
            //             }    
            //         } else {
            //             return null; 
            //         }
            //     } else { //error
            //         return null;
            //     }
            }
        }
        //4. El resultado final está en la pila
        //String resultado = (String)pila.quitar();
        pila.mostrar();
        // if (resultado != null) {
        //     return Double.parseDouble(resultado);
        // } else {
        //     return null;
        // }
        return null; //Eliminar esta linea despues de la prueba
    }

    public static Double evaluarPosfija(String posfija, ListaInfoEstatica variables, ListaInfoEstaticaNumerica valores){
        PilaInfoEstatica pila = new PilaInfoEstatica(posfija.length());
        String numActual = "";
        for (int posToken = 0; posToken < posfija.length(); posToken++){
            //1. Tokenizar la expPos de izq -> der
            char token = posfija.charAt(posToken);
            //2. Si el token es operando, meterlo en la pila (agregarlo a la variable numActual)
            if (esVariable(token + "")) {
                if (numActual.length() > 0) {
                    if (pila.poner(numActual) == false) {
                        return null;
                    } 
                    numActual = "";
                }
                if (pila.poner(token + "") == false) {
                    return null;
                }
            } else if (esOperando(token + "") == true) {
                numActual = numActual + token;
            } else {
                if (numActual.length() > 0) {
                    if (pila.poner(numActual) == false) {
                        return null;
                    } 
                    numActual = "";
                }
                //3. Si el token es operador, sacar 2 operandos de loa pila(el primero extraido es op2) aplicar la operacion, finalmente meter el resultado parcial en la pila
                String operando2 = (String) pila.quitar();
                String operando1 = (String) pila.quitar();
                if (esVariable(operando2)) {
                    int posValor2 = (int)variables.encontrar(operando2); 
                    operando2 = valores.obtener(posValor2).toString();
                }
                if (esVariable(operando1)) {
                    int posValor1 = (int)variables.encontrar(operando1);
                    operando1 = valores.obtener(posValor1).toString();
                }
                if (operando1 != null && operando2 != null) {
                    Double resultadoParcial = operacion(Double.parseDouble(operando1), Double.parseDouble(operando2), token);
                    if (resultadoParcial != null) {
                        if (pila.poner(resultadoParcial + "") == false) {
                            return null;
                        }    
                    } else {
                        return null; 
                    }
                } else { //error
                    return null;
                }
            }
        }   
        String resultado = (String)pila.quitar();
        if (resultado != null) {
            return Double.parseDouble(resultado);
        } else {
            return null;
        }  
    }
        

    public static boolean esOperando(String token){
        if (token.equals("+") == true) {
            return false;
        } else if (token.equals("-") == true) {
            return false;
        } else if (token.equals("*") == true) {
            return false;
        } else if (token.equals("/") == true) {
            return false;
        } else if (token.equals("^") == true) {
            return false;
        } else if (token.equals("%") == true) {
            return false;
        } else if (token.equals("(") == true) {
            return false;
        } else if (token.equals(")") == true) {
            return false;
        } else {
            return true;
        }
    }

    public static Double operacion(double operando1, double operando2, char operador){
        if (operador == '+') {
            return operando1 + operando2;
        } else if (operador == '-') {
            return operando1 - operando2;
        } else if (operador == '*') {
            return operando1 * operando2;
        } else if (operador == '/') {
            if (operando2 == 0) {
                return null;
            } else {
                return operando1 / operando2;
            }
        } else if (operador == '^') {
            return Math.pow(operando1, operando2);  
        } else if (operador == '%') {
            return operando1 % operando2;
        } else {
            return null;
        }
    }

    public static boolean esVariable(String token){
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";

        for (int letra = 0; letra < alfabeto.length(); letra++) {
            if (token.equalsIgnoreCase(alfabeto.charAt(letra) + "")) {
                return true; //Si se encuentra, si es una variable
            } 
        }
        return false;//No es variable
    }
    
    public static Double evaluarPrefija(String prefija){
        PilaInfoEstatica pila = new PilaInfoEstatica(prefija.length());
        for (int posToken = prefija.length()-1; posToken >= 0; posToken--){
            //1. Tokenizar la expPre de der -> izq
            char token = prefija.charAt(posToken);
            //2. Si el token es operando, meterlo en la pila
            if (esOperando(token + "") == true) {
                if (pila.poner(token + "") == false) {
                    return null;
                }
            } else { //es operador
                //3. Si el token es operador, sacar 2 operandos de loa pila(el primero extraido es op2) aplicar la operacion, finalmente meter el resultado parcial en la pila
                String operando1 = (String) pila.quitar();
                String operando2 = (String) pila.quitar();
                if (operando1 != null && operando2 != null) {
                    Double resultadoParcial = operacion(Double.parseDouble(operando1), Double.parseDouble(operando2), token);
                    if (resultadoParcial != null) {
                        if (pila.poner(resultadoParcial + "") == false) {
                            return null;
                        }    
                    } else {
                        return null; 
                    }
                } else { //error
                    return null;
                }
            }
        }
        //4. El resultado final está en la pila
        String resultado = (String)pila.quitar();
        if (resultado != null) {
            return Double.parseDouble(resultado);
        } else {
            return null;
        }
    }

    public static String infijaPosfija(String infija){
        PilaInfoEstatica pila = new PilaInfoEstatica(infija.length());
        String posfija = "";
        String numActual = "";
        for (int posToken = 0; posToken < infija.length(); posToken++) {//1. Tokenizar de izq a der
            char token = infija.charAt(posToken);
            if (esOperando(token + "") == true) { //2. Es numero, punto o variable(Operando)
                numActual = numActual + token;
            } else { //Es operador
                if (numActual.length() > 0) {//Si el numero existe que lo agregue a la expresion 2.Se agrega a exPos
                    posfija = posfija + numActual;
                    numActual = "";
                }   
                if (token == '(') { //4. Si el token es "(" se mete en la pila
                    pila.poner(token);
                } else if (token == ')') { //5. Si el token es ")" se sacan los operadores hasta llegar a "(" y se meten a exPos
                    if (pila.vacio() == false) {
                        while (pila.verFinal().toString().charAt(0) != '(') {
                            posfija = posfija + pila.quitar(); //Agregar los operadores a la expresion en caso de cerrar parentesis
                        }
                        pila.quitar(); //Quitar el "(" de la pila
                    }
                } else { //3. Si es operador se mete en la pila
                    pila.poner(token + "");
                }

            }
        }
        while (pila.vacio() == false) {//6. Sacar todos los operadores de la pila cuadno ya no hay tokens
            posfija = posfija + pila.quitar();
        }
        return posfija;
    }

    public static String infijaPrefija(String infija){
        return "";
    }
}
