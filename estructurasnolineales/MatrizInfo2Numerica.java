package estructurasnolineales;

import utils.comunes.TipoLogaritmo;
import estructuraslineales.ListaInfoEstaticaNumerica;

public class MatrizInfo2Numerica extends MatrizInfo2 {

    public MatrizInfo2Numerica(int filas, int columnas) {
        super(filas, columnas);
        inicializar(0.0);
    }

    public MatrizInfo2Numerica(int filas, int columnas, Object valor) {
        super(filas, columnas);
        inicializar(valor);
    }
    
    @Override
    public boolean cambiar(int fila, int col, Object valor) {
        if (validarIndices(fila, renglones) && validarIndices(col, columnas) && esNumero(valor)) {
            arreglo[fila][col] = ((Number) valor).doubleValue();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void rellenar(Object valor) {
        if (esNumero(valor)) {
            super.rellenar(((Number) valor).doubleValue());
        }
    }

    public boolean porEscalar(Object escalar) {
        if (esNumero(escalar)) {
            double valorEscalar = ((Number) escalar).doubleValue();

            for (int renglon = 0; renglon < renglones; renglon++) {
                for (int columna = 0; columna < columnas; columna++) {
                    arreglo[renglon][columna] = (valorEscalar*valorEscalar);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void inicializar(Object valor) {
        if (esNumero(valor)) {
            double valorInicial = ((Number) valor).doubleValue();
            for (int indReng = 0; indReng < renglones; indReng++) {
                for (int indColum = 0; indColum < columnas; indColum++) {
                    arreglo[indReng][indColum] = valorInicial;
                }
            }
        }
    }

    public boolean porEscalares(ListaInfoEstaticaNumerica escalares) {
        if (escalares.cantidad() != renglones * columnas) {
            return false; // La cantidad de escalares debe ser igual al tamaño de la matriz
        }

        int indiceEscalar = 0;
        for (int indReng = 0; indReng < renglones; indReng++) {
            for (int indColum = 0; indColum < columnas; indColum++) {
                double escalar = Double.parseDouble(escalares.obtener(indiceEscalar).toString());
                arreglo[indReng][indColum] = ((Number) arreglo[indReng][indColum]).doubleValue() * escalar;
                indiceEscalar++;
            }
        }
        return true;
    }

    public boolean sumarEscalar(Number escalar) { // Sumar un escalar a la matriz
        if (esNumero(escalar) == false) {
            return false; // Dimensiones no válidas
        }

        for (int indReng = 0; indReng < renglones; indReng++) {
            for (int indColum = 0; indColum < columnas; indColum++) {
                Number valorActual = (Number) arreglo[indReng][indColum];
                arreglo[indReng][indColum] = (Number) (valorActual.doubleValue() + escalar.doubleValue());
            }
        }

        return true;
    }

    public boolean sumarEscalares(ListaInfoEstaticaNumerica escalares) { // Sumar un escalar para cada elemento de la matriz
        if (escalares.cantidad() != renglones * columnas) {
            return false; // debe ser igual al tamaño de la matriz
        }

        int indiceEscalar = 0;
        for (int indReng = 0; indReng < renglones; indReng++) {
            for (int indColum = 0; indColum < columnas; indColum++) {
                double escalar = Double.parseDouble(escalares.obtener(indiceEscalar).toString());
                Number valorActual = (Number) arreglo[indReng][indColum];
                arreglo[indReng][indColum] = (Number) (valorActual.doubleValue() + escalar);
                indiceEscalar++;
            }
        }
        return true;
    }

    public boolean multiplicar(MatrizInfo2Numerica matriz2) {
        if (columnas != matriz2.obtenerRenglones()) {
            return false; // No se pueden multiplicar matrices con estas dimensiones
        }

        MatrizInfo2Numerica resultado = new MatrizInfo2Numerica(renglones, matriz2.obtenerColumnas());

        for (int rengActual = 0; rengActual < renglones; rengActual++) {
            for (int ColActual = 0; ColActual < matriz2.obtenerColumnas(); ColActual++) {
                double suma = 0;
                for (int unaColumna = 0; unaColumna < columnas; unaColumna++) {
                    suma = suma + ((Number) arreglo[rengActual][unaColumna]).doubleValue() * ((Number) matriz2.obtener(unaColumna, ColActual)).doubleValue();
                }
                resultado.cambiar(rengActual, ColActual, suma);
            }
        }

        // Redefinir la matriz actual con el resultado
        return redefinir(resultado);
    }

    public boolean sumar(MatrizInfo2Numerica matriz2) { 
        if (renglones != matriz2.obtenerRenglones() || columnas != matriz2.obtenerColumnas()) {
            return false; // Las matrices deben tener las mismas dimensiones
        }
        
        for (int indReng = 0; indReng < renglones; indReng++) {
            for (int indColum = 0; indColum < columnas; indColum++) {
                arreglo[indReng][indColum] = ((Number) arreglo[indReng][indColum]).doubleValue() + ((Number)matriz2.obtener(indReng, indColum)).doubleValue();

            }
        }

        return true;
    }
    
    public boolean aplicarPotencia(Number escalar) {
        for (int indRenglones = 0; indRenglones < renglones; indRenglones++) {
            for (int indColum = 0; indColum < columnas; indColum++) {
                arreglo[indRenglones][indColum] = Math.pow(((Number) arreglo[indRenglones][indColum]).doubleValue(), escalar.doubleValue());
            }
        }

        return true;
    }

    public boolean aplicarLogaritmo(TipoLogaritmo tipoLogaritmo) {
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                double valor = ((Number) arreglo[renglon][columna]).doubleValue();
                if (valor <= 0) {
                    return false; // No se puede aplicar logaritmo si hay valores no positivos
                }
    
                switch (tipoLogaritmo) {
                    case NATURAL:
                        arreglo[renglon][columna] = Math.log(valor);
                        break;
                    case BASE10:
                        arreglo[renglon][columna] = Math.log10(valor);
                        break;
                    case BASE2:
                        arreglo[renglon][columna] = Math.log(valor) / Math.log(2);
                        break;
                }
            }
        }
        return true;
    }
    
    public boolean matrizDiagonal(Number contenido) {
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (renglon == columna) {
                    arreglo[renglon][columna] = contenido.doubleValue();
                } else {
                    arreglo[renglon][columna] = 0.0;
                }
            }
        }
        return true;
    }
    
    public boolean esDiagonalSuperior() {
        for (int renglon = 1; renglon < renglones; renglon++) {
            for (int columna = 0; columna < renglon; columna++) {
                if (((Number) arreglo[renglon][columna]).doubleValue() != 0) {
                    return false; // Hay un elemento no nulo por debajo de la diagonal
                }
            }
        }
        return true;
    }
    
    public boolean esDiagonalInferior() {
        for (int renglon = 0; renglon < renglones - 1; renglon++) {
            for (int columna = renglon + 1; columna < columnas; columna++) {
                if (((Number) arreglo[renglon][columna]).doubleValue() != 0) {
                    return false; // Hay un elemento no nulo por encima de la diagonal
                }
            }
        }
        return true;
    }

    private boolean esNumero(Object valor) {
        return valor instanceof Number;
    }

    public boolean potencia(int exponente) {
        if (exponente < 0) {    
            return false; // Exponente debe ser no negativo
        }

        MatrizInfo2Numerica resultado = new MatrizInfo2Numerica(renglones, columnas);

        // Inicializar la matriz resultado con la matriz actual
        resultado.redefinir(this);
    
        for (int iterador = 1; iterador < exponente; iterador++) {
            resultado.multiplicar(this);
        }

        return redefinir(resultado);
    }

    public boolean doblarRenglones() {
        if (columnas % 2 != 0) {
            return false; // La matriz debe tener un número par de columnas
        }

        int nuevasColumnas = columnas / 2;
    
        MatrizInfo2Numerica resultado = new MatrizInfo2Numerica(renglones * 2, nuevasColumnas);
    
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < nuevasColumnas; columna++) {
                double valor = (double) arreglo[renglon][columna];
                resultado.cambiar(renglon, columna, valor);
                resultado.cambiar(renglon + renglones, columna, valor);
            }
        }
    
        // Redefinir la matriz actual con el resultado
        return redefinir(resultado);
    }

    public boolean doblarColumnas() {
        if (renglones % 2 != 0) {
            return false; // La matriz debe tener un número par de renglones
        }
    
        int nuevasRenglones = renglones / 2;
    
        MatrizInfo2Numerica resultado = new MatrizInfo2Numerica(nuevasRenglones, columnas * 2);
    
        for (int unRenglon = 0; unRenglon < nuevasRenglones; unRenglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                double valor = (double) arreglo[unRenglon][columna];
                resultado.cambiar(unRenglon, columna, valor);
                resultado.cambiar(unRenglon, columna + columnas, valor);
            }
        }
    
        // Redefinir la matriz actual con el resultado
        return redefinir(resultado);
    }
    
    public void invertirVertical(){
        Object[][] temporal = new Object[renglones][columnas];
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                temporal[(renglones - 1) - renglon][columna] = (double)arreglo[renglon][columna];
            }
        }
        arreglo = temporal;
    }

    public void invertirHorizontal(){
        Object[][] temporal = new Object[renglones][columnas];
        for (int renglon = 0; renglon < renglones; renglon++) {
            for (int columna = 0; columna < columnas; columna++) {
                temporal[renglon][(columnas - 1) - columna] = (double)arreglo[renglon][columna];
            }
        }
        arreglo = temporal;
    }
}
