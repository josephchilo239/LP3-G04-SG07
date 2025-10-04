package EjercicioUno;
import java.util.ArrayList;
import java.util.List;

public class CarritoModelo {
    private List<Producto> carrito;
    private List<List<Producto>> historial;

    public CarritoModelo() {
        carrito = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        carrito.add(producto);
    }

    public void eliminarProducto(String nombre) {
        carrito.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public double calcularTotal(double descuento, double envio) {
        double subtotal = carrito.stream().mapToDouble(Producto::getPrecio).sum();
        subtotal -= subtotal * descuento;
        return subtotal + envio;
    }

    public void realizarCompra() {
        if (!carrito.isEmpty()) {
            historial.add(new ArrayList<>(carrito));
            carrito.clear();
        }
    }

    public List<Producto> getCarrito() { return carrito; }
    public List<List<Producto>> getHistorial() { return historial; }
}
