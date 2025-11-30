package Ejercicio2;
import java.util.List;

public class SinDescuento implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(List<Producto> productos) {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
