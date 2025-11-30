package Ejercicio2;
import java.util.List;

public class DescuentoPorcentual implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(List<Producto> productos) {
        if (productos.size() == 2 &&
            productos.get(0).getNombre().equals(productos.get(1).getNombre())) {

            double total = productos.get(0).getPrecio() * 2;
            return total * 0.70;
        }
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
