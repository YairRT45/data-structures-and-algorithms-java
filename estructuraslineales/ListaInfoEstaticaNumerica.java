package estructuraslineales;


public class ListaInfoEstaticaNumerica extends ListaInfoEstatica{

    public ListaInfoEstaticaNumerica(int cuantos) {
        super(cuantos);
    }

    //Multiplica los elementos del arreglo por un escalar
    public boolean porEscalar(Number escalar){
        if (estaVacia() == false) {
            for (int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) * Double.parseDouble(escalar.toString());
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }

    //Suma los elementos del arreglo por un escalar
    public boolean sumarEscalar(Number escalar){
        if (estaVacia() == false) {
            for (int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) + Double.parseDouble(escalar.toString());
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }

    //Suma todos los elementos del arreglo por el escalar paralelo de otra lista
    public boolean sumar(ListaInfoEstaticaNumerica lista2){
        if (cantidad() == lista2.cantidad()) {
            for(int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) + (double)lista2.obtener(indice);
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }
    
    //Multiplica todos los elementos del arreglo por el escalar paralelo de otra lista
    public boolean multiplicar(ListaInfoEstaticaNumerica lista2){
        if (cantidad() == lista2.cantidad()) {
            for(int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) * (double) lista2.obtener(indice);
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }

    //Aplica potencia de 'escalar' a los elementos de un arreglo
    public boolean aplicarPotencia(Number escalar){
        if (estaVacia() == false) {
            for (int indice = 0; indice < cantidad(); indice++){
                double valorExponenciado = Math.pow((double)obtener(indice), Double.parseDouble(escalar.toString()));
                cambiar(indice, valorExponenciado);
            }
            return true;
        } else {
            return false;            
        }
    } 
    
    //Aplica potencia a todos los elementos del arreglo referente escalar paralelo de otra lista
    public boolean aplicarPotencia(ListaInfoEstaticaNumerica listaEscalares){
        if (cantidad() == listaEscalares.cantidad()){
            for(int indice = 0; indice < cantidad(); indice++){
                double valorExponenciado = Math.pow((double)obtener(indice), (double)listaEscalares.obtener(indice));
                cambiar(indice, valorExponenciado);
            }
            return true;
        } else {
            return false;
        }
    }
    
    //Multiplica todos los elementos del arreglo por el escalar paralelo de otra lista y suma todos los resultados
    public double productoEscalar(ListaInfoEstaticaNumerica lista2){
        double valorRetorno = 0.0;
        if (cantidad() == lista2.cantidad()) {
            for (int indice = 0; indice < cantidad(); indice++){
                double resultadoMulticacion = (double)obtener(indice) * (double)lista2.obtener(indice);
                valorRetorno = valorRetorno + resultadoMulticacion; 
            }
            return valorRetorno;
        } else {
            return 0.0;
        }
    }
    
    //Calcula la norma de un vector
    public double norma(){
        double normaResultado = 0.0;
        for (int indice = 0; indice < cantidad(); indice++){
            normaResultado = normaResultado + Math.pow((double)obtener(indice), 2.0);
        }
        normaResultado = Math.sqrt(normaResultado);
        return normaResultado;
    }
    
    //Obtenemos el valor de la norma Euclidiana de 2 vectores del mismo tamaño
    public double normaEuclidiana(ListaInfoEstaticaNumerica arreglo2){
        double normaResultado = 0.0;
        if (cantidad() == arreglo2.cantidad()) {
            for (int indice = 0; indice < cantidad(); indice++) {
                double resultadoPorSumar = Math.pow((double)arreglo2.obtener(indice) - (double)obtener(indice), 2);
                normaResultado = normaResultado + resultadoPorSumar;
            }
            return Math.sqrt(normaResultado);
        } else {
            return 0.0;
        }
    }

    public boolean sumarListaEstatica(ListaInfoEstatica listas){
        for (int contador = 0; contador < listas.cantidad(); contador++){
            if (cantidad() != ((ListaInfoEstatica)listas.obtener(contador)).cantidad()) {
                return false; //Esto si una lista es de diferente tamaño
            } else {
                ListaInfoEstaticaNumerica listaSumar = (ListaInfoEstaticaNumerica)listas.obtener(contador); //Obtenemos una lista almacenada
                sumar(listaSumar); //Usamos el metodo sumar dentro del for para ahorrarnos varias lineas de codigo
            }
        }
        return true;
    }

    //Suma todos los elementos del arreglo por el escalar paralelo de otra lista
    public boolean sumarEscalares(ListaInfoEstaticaNumerica escalares){
        if (cantidad() == escalares.cantidad()) {
            for(int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) + (double)escalares.obtener(indice);
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }
    
    //Suma los elementos contenidos en la posicion referente a los valores obtenidos de la lista indices
    public double sumarIndices(ListaInfoEstaticaNumerica indices){
        double resultado = 0;
        for (int contador = 0; contador < indices.cantidad(); contador++){
            double indiceDouble = (double)indices.obtener(contador); 
            int indice = (int)indiceDouble; //Buscamos el valor en tal posicion
            resultado = resultado + (double)obtener(indice);
        }
        return resultado;
    }
    
    //Calculamos la dependencia de 2 vectores si con los escalares dados es igual a 0
    public boolean sonLinealmenteDependientes(ListaInfoEstatica listaVectores){
        if (cantidad() == listaVectores.cantidad()) {
            double resultado = 0;
            for (int indice = 0; indice < cantidad(); indice++){
                double sumaVector = sumarTodo((ListaInfoEstaticaNumerica)listaVectores.obtener(indice));
                resultado = resultado + ((double)obtener(indice) * sumaVector);
            }
            if (resultado == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Es lo opuesto al metodo anterior
    public boolean sonLinealmenteIndependientes(ListaInfoEstatica listaVectores){
        if (sonLinealmenteDependientes(listaVectores) == false) {
            return true;
        } else {
            return false;
        }
    }

    // Si la  multiplicacion de la suma de los vectores es igual a 0 entonces es ortogonal
    public boolean sonOrtogonales(ListaInfoEstaticaNumerica lista2){
        double resultado = productoEscalar(lista2);
        if (resultado == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Buscaremos si la posicion de los vectores son paralelos referente al otro
    public boolean esParalelo(ListaInfoEstaticaNumerica lista2){
        double elemento = 0;

        for (int IndiceRecorrido = 0; IndiceRecorrido < cantidad(); IndiceRecorrido++) {
            if ((double) lista2.obtener(IndiceRecorrido) != 0) {
                elemento = (double) obtener(IndiceRecorrido) / (double) lista2.obtener(IndiceRecorrido);
                break;
            }
        }

        for (int indiceActual = 0; indiceActual < cantidad(); indiceActual++) {
            double elemento2 = (double) obtener(indiceActual) / (double) lista2.obtener(indiceActual);
            if (elemento != elemento2) {
                return false;
            }
        }

        return true;
    }
    
    //Metodo de apoyo para calcular la suma de todos los elementos de una lista 
    public double sumarTodo(ListaInfoEstaticaNumerica lista){
        double resultado = 0;
        for (int indice = 0; indice < lista.cantidad(); indice++){
            resultado = resultado + (double)lista.obtener(indice);
        }
        return resultado;
    }

    public boolean entreEscalar(Number escalar){
        if (estaVacia() == false) {
            for (int indice = 0; indice < cantidad(); indice++){
                double valorNuevo = (double)obtener(indice) / Double.parseDouble(escalar.toString());
                cambiar(indice, valorNuevo);
            }
            return true;
        }else{
            return false;
        }
    }

    //Suma la cantidad de elementos consecutivos
    public void promediarConsecutivos(Number escalar){
        ListaInfoEstaticaNumerica listaPromediada = new ListaInfoEstaticaNumerica(CAPACIDAD); //Creo la lista temporal
        
        for (int indice = 0; indice < cantidad(); indice += (int)escalar){
            double sumaParcial = 0.0; ;
            for(int unNumero = 0; unNumero < (int)escalar; unNumero++){
                sumaParcial = sumaParcial + (double)obtener(indice + unNumero); //Obtengo la suma de la cantidad de elementos que se indican
            }
            double promedio = sumaParcial / (int)escalar; //Obtenemos el promedio dividiendo la suma parcial entre el escalar ingresado
            listaPromediada.insertar(promedio);
        }
        copiarLista(listaPromediada); //Copio los datos de la lista temporal en la lista real utilizable
    }

    public void expandirLista(Number escalar){
        ListaInfoEstaticaNumerica listaExpandida = new ListaInfoEstaticaNumerica(CAPACIDAD * (int)escalar);//Creo una lista de tamaño mayor a la original

        for (int indice = 0; indice < cantidad(); indice++){
            listaExpandida.insertar((double)obtener(indice));

            //Calcular la muestra inventada
            double muestraInventada;
            if (indice == posFinal) {
                muestraInventada = (double) obtener(indice);
            } else {
                muestraInventada = ((double) obtener(indice) + (double) obtener(indice + 1)) / 2;
            }
            for (int contador = 0; contador < (int) escalar - 1; contador++){
                listaExpandida.insertar(muestraInventada); //Inserto la muestra inventada segun cuanto se amplio
            }
            
        }
        redimensionar(CAPACIDAD * (int) escalar); //Cambio el tamaño de la lista 
        vaciar(); 
        copiarLista(listaExpandida); //Guardo todos los elementos de la lista temporal en el arreglo real
    }
    
    //Buscaremos los elementos encontrados en los valores de la listaIndices y los agregara en una sublista
    @Override
    public ListaInfoEstaticaNumerica subLista(ListaInfoEstaticaNumerica listaIndices){
        ListaInfoEstaticaNumerica sublista = new ListaInfoEstaticaNumerica(listaIndices.cantidad());
        for (int contador = 0; contador < listaIndices.cantidad(); contador++){
            double indiceBusqueda = (double)listaIndices.obtener(contador);
            double elementoEncontrado = (double)obtener((int)indiceBusqueda);
            sublista.insertar(elementoEncontrado);
        }
        return sublista;
    }
    
    @Override
    public Object borrar(Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.borrar(valorNumerico);
        } else {
            return null;
        }
    }

    @Override
    public boolean cambiar(Object valorViejo, Object valorNuevo, int numVeces) {
        if (valorViejo instanceof Number && valorNuevo instanceof Number) {
            double valorViejoNum = Double.parseDouble(valorViejo.toString());
            double valorNuevoNum = Double.parseDouble(valorNuevo.toString());
            return super.cambiar(valorViejoNum, valorNuevoNum, numVeces);
        }else{
            return false;
        }
    }

    @Override
    public boolean cambiar(int indice, Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.cambiar(indice, valorNumerico);
        }else{
            return false;
        }
        
    }

    @Override
    public int contar(Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.contar(valorNumerico);
        }else{
            return 0;
        }
        
    }

    @Override
    public Object encontrar(Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.encontrar(valorNumerico);
        } else {
            return -1;
        }
    }

    @Override
    public ListaInfoEstatica encontrarValores(Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.encontrarValores(valorNumerico);
        }else{
            return null;
        }
    }

    @Override
    public Integer insertar(Object valor) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            return super.insertar(valorNumerico);
        }else{
            return -1;
        }
    }

    @Override
    public boolean insertar(int indice, Object info) {
        if (info instanceof Number) {
            double valorNumerico = Double.parseDouble(info.toString());
            return super.insertar(indice, valorNumerico);
        }else{
            return false;
        }
    }

    @Override
    public void rellenar(Object valor, int cantidad) {
        if (valor instanceof Number) {
            double valorNumerico = Double.parseDouble(valor.toString());
            super.rellenar(valorNumerico, cantidad);
        }
    }    

    @Override
    public void convertirArray(Object[] buffer){
        for (int contador = 0; contador < buffer.length; contador++){
            insertar(buffer[contador]); //Recorremos todo el arreglo y guardamos todos los datos en la lista
        }
    }

    @Override
    public ListaInfoEstaticaNumerica clonar(){
        return this;
    }
}
