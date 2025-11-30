package Ejercicio2;
import java.util.List;

public class DescuentoFijo implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(List<Producto> productos) {
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
        return total * 0.90;
    }
}
