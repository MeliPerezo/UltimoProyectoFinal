public class Cliente {
    int tiempoTranscurrido;
    int tiempoTramite;
    int tiempoCola;
    boolean enCaja;
    int numeroCliente;
    static int contadorCliente;
    boolean tramiteTerminado = false;


    public Cliente() {
        tiempoTranscurrido = 0;
        generarTiempo();
        tiempoCola = 0;
        enCaja = false;
        numeroCliente = contadorCliente;
        contadorCliente++;
    }

    public void generarTiempo() {
        double tiempoEnCajas = Math.random();
        if (tiempoEnCajas >= 0 && tiempoEnCajas <= 0.30) {
            tiempoTramite = 1;
        } else if (tiempoEnCajas >= 0.20 && tiempoEnCajas <= 0.4) {
            tiempoTramite = 2;
        } else if (tiempoEnCajas >= 0.4 && tiempoEnCajas <= 0.6) {
            tiempoTramite = 3;
        } else if (tiempoEnCajas >= 0.6 && tiempoEnCajas <= 0.8) {
            tiempoTramite = 5;
        } else if (tiempoEnCajas >= 0.8 && tiempoEnCajas <= 0.9) {
            tiempoTramite = 8;
        } else if (tiempoEnCajas >= 0.9 && tiempoEnCajas <= 0.95) {
            tiempoTramite = 13;
        }
        tiempoTramite = (int) (13 + (13 * Math.random()));
    }

}