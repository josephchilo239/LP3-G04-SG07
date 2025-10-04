package EjercicioUno;
import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== CARRITO DE COMPRAS ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Ver carrito");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Calcular total");
        System.out.println("5. Realizar compra");
        System.out.println("6. Ver historial de compras");
        System.out.println("7. Salir");
    }

    public String leerTexto(String msg) {
        System.out.print(msg + ": ");
        return sc.nextLine();
    }

    public double leerNumero(String msg) {
        System.out.print(msg + ": ");
        return Double.parseDouble(sc.nextLine());
    }

    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) System.out.println("Carrito vacío.");
        else productos.forEach(p -> System.out.println("- " + p));
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
