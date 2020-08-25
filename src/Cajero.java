public class Cajero {
    Cliente cliente;
    boolean isNew;

    public Cajero(boolean isNew) {
        this.isNew = isNew;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    public boolean estaocupada() {

        return cliente!=null;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
