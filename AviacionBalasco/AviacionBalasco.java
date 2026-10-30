import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Entidades.Aeropuerto;
import Entidades.Avion;
import Entidades.Piloto;
import Entidades.Vuelo;
import utils.Menu;

public class AviacionBalasco {
    static List<Avion> aviones;
    static List<Piloto> pilotos;
    static List<Aeropuerto> aeropuertos;
    static List<Vuelo> vuelos;
    static Scanner teclado;
    static int seleccion;

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        aviones = new ArrayList<>();
        pilotos = new ArrayList<>();
        aeropuertos = new ArrayList<>();
        vuelos = new ArrayList<>();

        
        
        String[] opcionesPrincipales = {
            "Dar de alta avion",
            "Consultar aviones por tipo",
            "Dar de alta un piloto",
            "Dar de alta un aeropuerto",
            "Registrar un vuelo",
            "Consultar vuelos por origen",
            "Consultar vuelos por destino",
            "Consultar aeropuertos por ciudad",
            "Salir del programa"
        };
        
        Boolean programa = true;
        do {
            Menu menu = new Menu(opcionesPrincipales);
            menu.mostrarMenu();
            seleccion = menu.seleccionarDesdeMenu();
            switch (seleccion) {
                case 1:
                    agregarAvion();
                    break;
                case 2:
                    aviones = consultarAvionPorTipo();
                    for (int iterador = 0; iterador < aviones.size(); iterador++){
                        System.out.println("1. Modelo:" + aviones.get(iterador).getModelo() + ", Matricula:" + aviones.get(iterador).getMatricula());
                    }
                    break;
                case 3:
                    agregarPiloto();
                    break;
                case 4:
                    agregarAeropuerto();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    aeropuertos = consultarAeropuertoCiudad();
                    for (int iterador = 0; iterador < aeropuertos.size(); iterador++){
                        System.out.println("1. " + aeropuertos.get(iterador).getCiudad() + ", " + aeropuertos.get(iterador).getPais());
                    }
                    break;
                case 9:
                    programa = false;
                default:
                    break;
            }
        } while (programa);
        
    }


    public static void agregarAvion(){
        String[] tiposDeAvion = {"Carga", "Pasajeros", "Militar", "Recreativo", "Excursion"};
        Menu menuTipos = new Menu(tiposDeAvion);
        String tipo;
        System.out.println("Ingresa el tipo de avion: ");
        menuTipos.mostrarMenu();
        int seleccion = menuTipos.seleccionarDesdeMenu();
        if (seleccion >= 1 && seleccion <= tiposDeAvion.length) {
            tipo = tiposDeAvion[seleccion - 1]; // Ajusta el índice para obtener el tipo correcto
            System.out.println("Tipo de avión seleccionado: " + tipo);
        } else {
            System.out.println("Número no válido. No se asignó un tipo de avión.");
            return; // Agrega un return para salir del método si la selección no es válida
        }
        // } //Este if nos permite que dependiendo el numero que ingresemos, agregue un valor al tipo
        System.out.print("Ingresa la matricula del avion: ");
        String matricula = teclado.nextLine();
        System.out.print("Ingresa el fabricante del avion: ");
        String fabricante = teclado.nextLine();
        System.out.print("Ingresa el modelo del avion: ");
        String modelo = teclado.nextLine();
        System.out.print("Ingresa la capacidad del avion: ");
        int capacidad = teclado.nextInt();
        System.out.print("Ingresa el tiempo de autonomia del avion: ");
        int autonomia = teclado.nextInt();
        Avion nuevoAvion = new Avion();
        nuevoAvion.setTipo(tipo);
        nuevoAvion.setMatricula(matricula);
        nuevoAvion.setFabricante(fabricante);
        nuevoAvion.setModelos(modelo);
        nuevoAvion.setCapacidad(capacidad);
        nuevoAvion.setAutonomia(autonomia);
        aviones.add(nuevoAvion);
    }

    public static void agregarPiloto(){
        String[] tiposDeAvion = {"Carga", "Pasajeros", "Militar", "Recreativo", "Excursion"};
        Menu menuTipos = new Menu(tiposDeAvion);
        String licencia;
        Avion avion;
        System.out.println("Nombre del piloto: ");
        teclado.nextLine();
        String nombre = teclado.nextLine();
        System.out.println("Ingresa el tipo de licencia: ");
        menuTipos.mostrarMenu();
        int seleccionLicencia = menuTipos.seleccionarDesdeMenu();
        if (seleccionLicencia >= 1 && seleccionLicencia <= tiposDeAvion.length) {
            licencia = tiposDeAvion[seleccionLicencia - 1]; // Ajusta el índice para obtener el tipo correcto
            System.out.println("Tipo de avión seleccionado: " + licencia);
        } else {
            System.out.println("Número no válido. No se asignó una licencia.");
            return; // Agrega un return para salir del método si la selección no es válida
        }

        Avion[] aviones = consultarAvionPorTipoParaPiloto(licencia);
        String[] modelosAviones = new String[aviones.length];
        for (int contador = 0; contador < aviones.length; contador++){
            modelosAviones[contador] = aviones[contador].getModelo();
        }
        Menu menuAviones = new Menu(modelosAviones);
        menuAviones.mostrarMenu();
        int seleccionAvion = menuAviones.seleccionarDesdeMenu();
        if (seleccionAvion >= 1 && seleccionAvion <= aviones.length) {
            avion = aviones[seleccionAvion - 1]; // Ajusta el índice para obtener el tipo correcto
            System.out.println("Avión seleccionado: " + avion.getModelo());
        } else {
            System.out.println("Número no válido. No se asignó un avión.");
            return; // Agrega un return para salir del método si la selección no es válida
        }
        Piloto nuevoPiloto = new Piloto();
        nuevoPiloto.setLicencia(licencia);
        nuevoPiloto.setNombre(nombre);
        nuevoPiloto.setAvionAsignado(avion);
        pilotos.add(nuevoPiloto);
        System.out.println(pilotos.get(0).getNombre() + " " + pilotos.get(0).getLicencia());
    }

    public static void agregarAeropuerto(){
        String[] ciudades = {"Guadalajara", "Madrid", "Los Angeles", "Roma", "Birmingham"};
        String[] paises = {"México", "España", "Estados Unidos", "Italia", "Inglaterra"};
        Menu menuCiudades = new Menu(ciudades);
        String ciudad;
        String pais;
        System.out.println("Selecciona la ciudad");
        menuCiudades.mostrarMenu();
        int seleccionCiudad = menuCiudades.seleccionarDesdeMenu();
        if (seleccionCiudad >= 1 && seleccionCiudad <= ciudades.length) {
            ciudad = ciudades[seleccionCiudad - 1]; 
            pais = paises[seleccionCiudad -1];
            System.out.println("Ciudad seleccionada: " + ciudad + ", " + pais);
        } else {
            System.out.println("Número no válido. No se asignó una ciudad.");
            return; // Agrega un return para salir del método si la selección no es válida
        }
        Aeropuerto nuevoAeropuerto = new Aeropuerto();
        nuevoAeropuerto.setCiudad(ciudad);
        nuevoAeropuerto.setPais(pais);
        aeropuertos.add(nuevoAeropuerto);
    }
    //Falta aun el de registrar vuelo
    public void registrarVuelo(Aeropuerto origen, Aeropuerto destino, String fecha, Piloto piloto){
        Vuelo nuevoVuelo = new Vuelo();
        nuevoVuelo.setOrigen(origen);
        nuevoVuelo.setDestino(destino);
        nuevoVuelo.setFecha(fecha);
        nuevoVuelo.setPiloto(piloto);
    }

    public static Avion[] consultarAvionPorTipoParaPiloto(String tipoBuscar){
        List<Avion> avionesPorTipo = new ArrayList<>();
        for (Avion avion : aviones) {
            if (tipoBuscar.equals(avion.getTipo())) {
                avionesPorTipo.add(avion);  
            }
        }
        Avion[] avionesArray = new Avion[avionesPorTipo.size()];
        avionesArray = avionesPorTipo.toArray(avionesArray);
        return avionesArray;
    }

    public static List<Avion> consultarAvionPorTipo(){
        String[] tiposDeAvion = {"Carga", "Pasajeros", "Militar", "Recreativo", "Excursion"};
        Menu menuTipos = new Menu(tiposDeAvion);
        String tipoBuscar;
        System.out.println("Ingresa el tipo de avion: ");
        menuTipos.mostrarMenu();
        int seleccion = menuTipos.seleccionarDesdeMenu();
        if (seleccion >= 1 && seleccion <= tiposDeAvion.length) {
            tipoBuscar = tiposDeAvion[seleccion - 1]; // Ajusta el índice para obtener el tipo correcto
            System.out.println("Tipo de avión seleccionado: " + tipoBuscar);
        } else {
            System.out.println("Número no válido. No se selecciono ningun tipo de avión.");
            return null; // Agrega un return para salir del método si la selección no es válida
        }
        List<Avion> avionesPorTipo = new ArrayList<>();
        for (Avion avion : aviones) {
            if (tipoBuscar.equals(avion.getTipo())) {
                avionesPorTipo.add(avion);  
            }
        }
        return avionesPorTipo;
    }

    public List<Vuelo> consultarVueloOrigen(Aeropuerto origen){
        List<Vuelo> vuelosPorOrigen = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (origen.equals(vuelo.getOrigen())) {
                vuelosPorOrigen.add(vuelo);
            }
        }
        return vuelosPorOrigen;
    }

    public List<Vuelo> consultarVueloDestino(Aeropuerto destino){
        List<Vuelo> vuelosPorDestino = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (destino.equals(vuelo.getOrigen())) {
                vuelosPorDestino.add(vuelo);
            }
        }
        return vuelosPorDestino;
    }

    public static List<Aeropuerto> consultarAeropuertoCiudad(){
        String[] ciudades = {"Guadalajara", "Madrid", "Los Angeles", "Roma", "Birmingham"};
        Menu menuCiudades = new Menu(ciudades);
        String ciudad;
        System.out.println("Selecciona la ciudad");
        menuCiudades.mostrarMenu();
        int seleccionCiudad = menuCiudades.seleccionarDesdeMenu();
        if (seleccionCiudad >= 1 && seleccionCiudad <= ciudades.length) {
            ciudad = ciudades[seleccionCiudad - 1]; 
            System.out.println("Ciudad seleccionada: " + ciudad);
        } else {
            System.out.println("Número no válido. No se asignó una ciudad.");
            return null; // Agrega un return para salir del método si la selección no es válida
        }
        List<Aeropuerto> aeropuertosCiudad = new ArrayList<>();
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (ciudad.equals(aeropuerto.getCiudad()));
                aeropuertosCiudad.add(aeropuerto);
        }
        return aeropuertosCiudad;
    }
}