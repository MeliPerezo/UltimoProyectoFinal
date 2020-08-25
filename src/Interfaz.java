import java.util.Scanner;

public class Interfaz {
    private Scanner input = new Scanner(System.in);
    private int cantidadCajas = 0;
    private int option = 0;

    Logica L = new Logica();

    public void menuPrincipal() {
        System.out.println("------------------------------------------------------");
        System.out.println("\n\t*** Inicio de la simulacion ***\n");
        System.out.println("  Ingrese el numero de cajas:  ");
        cantidadCajas = input.nextInt();
        L.CargarDatos(cantidadCajas);
        L.obtenerTiempoPromedioColas();
        L.obtenerTiempoPromedioTramites();
        L.obtenerTiempoPromedioTotal();
        L.obtenerPersonasenColaYPromedio();
        System.out.println("------------------------------------------------------------");
    }
}