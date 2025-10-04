package ActividadTres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoModelo {
	private List<Pedido> pedidos;
	private List<Pedido> historial;

	public PedidoModelo() {
		pedidos = new ArrayList<>();
		historial = new ArrayList<>();
	}

	public void agregarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void marcarComoCompleto(String nombre) {
		for (Pedido p : pedidos) {
			if (p.getNombrePlato().equalsIgnoreCase(nombre)) {
				p.setEstado("Completo");
				historial.add(p);
			}
		}
	}

	public void eliminarPedido(String nombre) {
		pedidos.removeIf(p -> {
			if (p.getNombrePlato().equalsIgnoreCase(nombre)) {
				p.setEstado("Eliminado");
				historial.add(p);
				return true;
			}
			return false;
		});
	}

	public List<Pedido> obtenerPorEstado(String estado) {
		return pedidos.stream().filter(p -> p.getEstado().equalsIgnoreCase(estado)).collect(Collectors.toList());
	}

	public int contarPendientes() {
		return (int) pedidos.stream().filter(p -> p.getEstado().equalsIgnoreCase("Pendiente")).count();
	}

	public List<Pedido> getHistorial() {
		return historial;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
}
