package ActividadUno;

public class Pedido {
    private String nombrePlato;

    public Pedido(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    @Override
    public String toString() {
        return nombrePlato;
    }
}
