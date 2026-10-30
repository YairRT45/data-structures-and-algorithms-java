package utils;

public class ComparadorObjetos {
    
    public static int compararObjetos(Object valor1, Object valor2){
        if (valor1 instanceof Number && valor2 instanceof Number) { //Para verificar que ambos sean numeros
            double num1 = Double.parseDouble(valor1.toString());
            double num2 = Double.parseDouble(valor2.toString());
            if (num1 > num2) {
                return 1;
            }else if(num1 < num2){
                return -1;
            }else{
                return 0;
            }
        }else{//Si no es numero
            return valor1.toString().compareToIgnoreCase(valor2.toString());
        }
    }
}
