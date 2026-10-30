package registros.indices;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructuraslineales.ListaInfoEstaticaOrdenada;
import utils.comunes.TipoOrdenamiento;

public class GestorIndices {
    protected ListaInfoEstaticaOrdenada terminos;
    protected TipoOrdenamiento orden = TipoOrdenamiento.INC;

    public GestorIndices(int maximoIndices){
        terminos = new ListaInfoEstaticaOrdenada(maximoIndices, orden);
    }

    public boolean agregarTerminos(Termino terminoParam){//Se añade el termino a la lista
        int retornoTer = terminos.insertar(terminoParam);
        if (retornoTer < 0) { //hubo error
            return false;   
        }
        return true; //Si llega hasta acá, quiere decir que no hubo error
    }

    public boolean buscarTermino(String nombre){//Se busca un termino mediente el nombre y lo muestra en pantalla
        int indiceTermino = (int) terminos.encontrar(nombre);
        if (indiceTermino > 0) {
            Termino terminoEncontrado = (Termino) terminos.obtener(indiceTermino - 1);
            terminoEncontrado.mostrarTermino();
            return true;
        }else{
            SalidaPorDefecto.consola("No se encontro ningún termino con ese nombre\n\n");
            return false;
        }
    }

    public void buscarSubtermino(String nombre){//Se busca un subtermino mediente el nombre y lo muestra en pantalla
        for (int indices = 0; indices < terminos.cantidad(); indices++){
            Termino unTermino = (Termino) terminos.obtener(indices);
            unTermino.buscarSubtermino(nombre);
        }
    }

    public Termino pedirTermino(){ //Se crea un objeto de tipo termino mediante entrada del teclado
        Termino terminoARetornar = new Termino(1000);

        SalidaPorDefecto.consola("Nombre: ");
        String nombreTer = EntradaPorDefecto.consolaCadenas();

        SalidaPorDefecto.consola("Pagina de inicio: ");
        int pagInicio = EntradaPorDefecto.consolaInt();

        SalidaPorDefecto.consola("Pagina final: ");
        int pagFinal = EntradaPorDefecto.consolaInt();

        terminoARetornar.setNombre(nombreTer);
        terminoARetornar.setPaginaInicio(pagInicio);
        terminoARetornar.setPaginaFin(pagFinal);

        SalidaPorDefecto.consola("""
                ¿Desea agregar subterminos?
                1. Sí
                2. No
                """);
        int seleccion = EntradaPorDefecto.consolaInt();
        //Se agregan los subterminos que el usuario desee
        switch (seleccion) {
            case 1:
                SalidaPorDefecto.consola("¿Cuantos? \n");
                int cantSubterminos = EntradaPorDefecto.consolaInt();
                for(int contador = 0; contador < cantSubterminos; contador++){
                    Subtermino unSubtermino = pedirSubtermino();
                    terminoARetornar.agregarSubtermino(unSubtermino);
                }
                break;
            default:
                break;
        }

        return terminoARetornar;
        
    }

    public Subtermino pedirSubtermino(){ //Metodo de apoyo para completar un termino, sirve para crear un subtermino mediante los datos que ingresemos por teclado
        Subtermino subterminoARetornar = new Subtermino();

        SalidaPorDefecto.consola("Nombre: ");
        String nombreSub = EntradaPorDefecto.consolaCadenas();

        SalidaPorDefecto.consola("Pagina de inicio: ");
        int pagInicio = EntradaPorDefecto.consolaInt();

        SalidaPorDefecto.consola("Pagina final: ");
        int pagFinal = EntradaPorDefecto.consolaInt();

        subterminoARetornar.setNombre(nombreSub);
        subterminoARetornar.setPaginaInicio(pagInicio);
        subterminoARetornar.setPaginaFin(pagFinal);

        return subterminoARetornar;
    }

    public void mostrarTodos(){ //Muestra todos los terminos de la lista y con ellos sus subterminos
        for(int indice = 0; indice < terminos.cantidad(); indice++){
            Termino unTermino = (Termino) terminos.obtener(indice);
            unTermino.mostrarTermino();
        }
    }
    
    public void mostrarPorRango(char letraInicial1, char letraInicial2) { //Filtra los terminos que empiecen por una letra entre un rango indicado y los muestra
        for (int indice = 0; indice < terminos.cantidad(); indice++) {
            Termino unTermino = (Termino) terminos.obtener(indice);
            char primeraLetra = unTermino.getNombre().charAt(0);
            if (primeraLetra >= letraInicial1 && primeraLetra <= letraInicial2) {
                unTermino.mostrarTermino();
            }
        }
    }
    
    public void menuPrincipal(){
        SalidaPorDefecto.consola("""
            1. Agregar termino
            2. Consultar termino o subtermino
            3. Listar
            Selecciona una opcion: \n""");
        int seleccion = EntradaPorDefecto.consolaInt();
        SalidaPorDefecto.consola("\n");
        switch (seleccion) {
            case 1:
                agregarTerminos(pedirTermino());
                break;
            case 2:
                menuConsultar();
                break;
            case 3:
                menuListar();
                break;
            default:
                break;
        }
    }

    public void menuConsultar(){
        SalidaPorDefecto.consola("""
                1. Buscar termino
                2. Buscar subtermino
                Selecciona una opcion: \n""");
        int seleccion = EntradaPorDefecto.consolaInt();
        SalidaPorDefecto.consola("\n");
        switch (seleccion) {
            case 1:
                SalidaPorDefecto.consola("Ingresa el nombre: ");
                String nombreTer = EntradaPorDefecto.consolaCadenas();
                buscarTermino(nombreTer);
                break;
            case 2:
                SalidaPorDefecto.consola("Ingresa el nombre: ");
                String nombreSubter = EntradaPorDefecto.consolaCadenas();
                buscarSubtermino(nombreSubter);
                break;
            default:
                break;
        }
    }

    public void menuListar(){
        SalidaPorDefecto.consola("""
                1. Listar todos
                2. Listar por rango de letras iniciales
                Selecciona una opción: \n""");
        int seleccion = EntradaPorDefecto.consolaInt();
        switch (seleccion) {
            case 1:
                mostrarTodos();
                break;
            case 2:
                SalidaPorDefecto.consola("Ingresa el rango, ejemplp(C-H)");
                String rango = EntradaPorDefecto.consolaCadenas();
                char letra1 = rango.charAt(0);
                char letra2 = rango.charAt(2);
                mostrarPorRango(letra1, letra2);
                break;
            default:
                break;
        }
    }

}
