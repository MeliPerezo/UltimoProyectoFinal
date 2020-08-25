import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Logica {

    private static Scanner input = new Scanner(System.in);
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    Queue<Cliente> prioridad = new LinkedList<>();
    Queue<Cliente>  noprioridad  = new LinkedList<>();
    Cajero[] cajero;
    int tiempoTramiteTotal=0;
    int personaAtendidas=0;

    private void procesoDeColasUno(int cantidadDeCajas){
        for(int  i = 0; i < cantidadDeCajas; i++){
            if(cajero[i].estaocupada()){
                if(cajero[i].cliente.tiempoTranscurrido == cajero[i].cliente.tiempoTramite){
                   tiempoTramiteTotal = tiempoTramiteTotal+cajero[i].cliente.tiempoTramite;
                    personaAtendidas++;
                    cajero[i].cliente.enCaja = false;
                    cajero[i].cliente.tramiteTerminado = true;
                    cajero[i].cliente = null;
                }else{
                    cajero[i].cliente.tiempoTranscurrido++;
                }
            }
        }
    }

    private void procesoDeColasDos(int cantidadDeCajas){
        if(!prioridad.isEmpty()){
            for(int  i = 0; i < cantidadDeCajas; i++) {
                if (!cajero[i].estaocupada()) {
                    Cliente cliente = prioridad.poll();
                    cliente.generarTiempo(); 
                    cajero[i].cliente = cliente;
                    if(cajero[i].cliente != null){
                        cajero[i].cliente.enCaja = true;
                    }
                    break;
                }
            }
        }
        if(!noprioridad.isEmpty()) {
            for (int i = 0; i < cantidadDeCajas; i++) {
                if (!cajero[i].estaocupada()) {
                    cajero[i].cliente = noprioridad.poll();
                    cajero[i].cliente.enCaja = true;
                }

            }
        }
    }
    private void procesoDeColasTres(){
        if(prioridad != null){
            Queue<Cliente> prioridadTemp = new LinkedList<>();
            while(!prioridad.isEmpty()){
                if(prioridad.peek() != null){
                    prioridad.peek().tiempoCola++;
                    prioridadTemp.add(prioridad.poll());
                }
            }
            prioridad = prioridadTemp;
        }
        if(noprioridad != null){
            Queue<Cliente> prioridadTemp = new LinkedList<>();
            while(!noprioridad.isEmpty()){
                if(noprioridad.peek() != null){
                    noprioridad.peek().tiempoCola++;
                    prioridadTemp.add(noprioridad.poll());
                }
            }
            noprioridad= prioridadTemp;
        }
    }
    private void procesoDeColasCuatro(String []Personas, int cantidadCajas){
       Cliente  cliente;
       for(int i = 0; i < Integer.parseInt(Personas[1]); i++){
            cliente = new Cliente();
            listaClientes.add(cliente);
            boolean agregadoCaja = false;
            for(int e = 0; e < cantidadCajas; e++){
                if(!cajero[e].estaocupada()){
                    cajero[e].cliente = cliente;
                    cliente.enCaja = true;
                    agregadoCaja = true;
                    System.out.println("Cliente numero :" + cliente.numeroCliente + " de cola  prioridad  a la caja " + e + " con un tiempo de tramite de: " + cliente.tiempoTramite);
                    break;
                }
            }
            if(!agregadoCaja){
                prioridad.add(cliente);
                System.out.println("Cliente numero : " + cliente.numeroCliente +  " de cola prioridad  a la cola con un tiempo de tramite de: " + cliente.tiempoTramite);
            }
        }
        for(int i = 0; i < Integer.parseInt(Personas[0]); i++){
            cliente  = new Cliente();
            listaClientes.add(cliente);
            boolean agregadoCaja = false;
            for(int e = 0; e < cantidadCajas; e++){
                if(!cajero[e].estaocupada()){
                    cajero[e].cliente = cliente;
                    cliente.enCaja = true;
                    agregadoCaja = true;
                    System.out.println("Cliente numero : " + cliente.numeroCliente +  "de cola sin prioridada la caja " + e + " con un tiempo de tramite de: " + cliente.tiempoTramite);
                    break;
                }
            }
            if(!agregadoCaja){
                prioridad.add(cliente);
                System.out.println("Cliente numero : " + cliente.numeroCliente +  " de cola sin prioridad  con un tiempo de tramite de: " + cliente.tiempoTramite);
            }
        }
    }
    private void Cajas(int cantidadCajas){
        cajero = new Cajero[cantidadCajas];
            for(int  i = 0; i < cantidadCajas; i++){
            cajero[i] = new Cajero(false);
        }
    }
    public void CargarDatos(int cantidadCajas) {
       Cajas(cantidadCajas);
        try {
            int contador = 1;
            Scanner lector = new Scanner(new File("C:\\Users\\usuario\\IdeaProjects\\UltimoProyectoFinal\\src\\PersonasEnCola.txt"));
            while (lector.hasNextLine()) {
                String[] Personas = lector.nextLine().split(",");

               Cliente cliente;
               System.out.println("\n\nMinuto " + contador + "\n\n");
               contador++;

                procesoDeColasUno(cantidadCajas);
                procesoDeColasDos(cantidadCajas);
                procesoDeColasTres();
                procesoDeColasCuatro(Personas, cantidadCajas);
            }
            lector.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void obtenerTiempoPromedioColas(){
        int contadorTiempos  = 0;
            for(int  i =0; i < listaClientes.size(); i++){
            contadorTiempos += listaClientes.get(i).tiempoCola;
        }
        System.out.println("\t\t# Promedio de tiempo en colas: " + contadorTiempos/listaClientes.size());
    }
    public void obtenerTiempoPromedioTramites(){
        System.out.println("\t\t# Promedio de tiempo en tramites: " + tiempoTramiteTotal/personaAtendidas);
    }
    public void obtenerTiempoPromedioTotal(){
        int contadorTiempos  = 0;
            for(int  i =0; i < listaClientes.size(); i++){
            contadorTiempos += listaClientes.get(i).tiempoTranscurrido + listaClientes.get(i).tiempoCola;
        }
        System.out.println("\t\t# Promedio de tiempo total: " + contadorTiempos/listaClientes.size());
    }
    public void obtenerPersonasenColaYPromedio(){
        int contadorTiempos  = 0;
        int contadorPersonasCola = 0;
             for(int  i =0; i < listaClientes.size(); i++){
            if(!listaClientes.get(i).tramiteTerminado){
                contadorPersonasCola++;
                contadorTiempos += listaClientes.get(i).tiempoCola;
            }
        }
        System.out.println("\t\t# Personas que se quedaron en cola: " + contadorPersonasCola + ", promedio de tiempo total en cola: " + Math.round(contadorTiempos/contadorPersonasCola) );
    }
}
