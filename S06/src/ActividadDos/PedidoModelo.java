package ActividadDos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void eliminarPedido(String nombre) {
        pedidos.removeIf(p -> p.getNombrePlato().equalsIgnoreCase(nombre));
    }

    public void actualizarPedido(String nombreViejo, String nuevoNombre) {
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().equalsIgnoreCase(nombreViejo)) {
                p.setNombrePlato(nuevoNombre);
                break;
            }
        }
    }

    public List<Pedido> buscarPorNombreOTipo(String texto) {
        return pedidos.stream()
                .filter(p -> p.getNombrePlato().equalsIgnoreCase(texto) ||
                             p.getTipoPlato().equalsIgnoreCase(texto))
                .collect(Collectors.toList());
    }

    public int contarPedidos() {
        return pedidos.size();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
