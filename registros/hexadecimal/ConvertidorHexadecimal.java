package registros.hexadecimal;

public class ConvertidorHexadecimal {
    
    public String aHexadecimal(int numero){
        if(numero > 16){
            int nuevoNumero = numero / 16;
            int residuo = numero % 16;
            return aHexadecimal(nuevoNumero) + aValorHexa(residuo);
        }else{
            return aValorHexa(numero);
        }
    }

    private String aValorHexa(int numero){
        if(numero < 10){
            return numero + "";
        }else{
            switch (numero) {
                case 10:
                    return "A";
                case 11:
                    return "B"; 
                case 12:
                    return "C";
                case 13:
                    return "D";
                case 14:
                    return "E"; 
                case 15:
                    return "F";
                default:
                    break;
            }
            return "";
        }
    }
}
