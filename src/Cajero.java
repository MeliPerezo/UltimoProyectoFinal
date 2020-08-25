public class Cajero {
    Cliente cliente;
    boolean isNew;

    public Cajero(boolean isNew) {
        this.isNew = isNew;
    }
    public boolean estaocupada() {

        return cliente!=null;
    }
}
