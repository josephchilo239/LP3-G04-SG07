package Ejercicio2;
import java.util.Comparator;
import java.util.List;

public class DescuentoPorcentualAcumulado implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(List<Producto> productos) {
        if (productos.size() >= 3) {
            Producto menor = productos.stream()
                    .min(Comparator.comparingDouble(Producto::getPrecio)).get();

            double total = productos.stream()
                    .mapToDouble(Producto::getPrecio).sum();

            return total - (menor.getPrecio() * 0.50);
        }
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
