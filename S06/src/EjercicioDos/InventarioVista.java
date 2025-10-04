package EjercicioDos;
import java.util.List;
import java.util.Scanner;

public class InventarioVista {
    private Scanner sc = new Scanner(System.in);

    public void mostrarInventario(List<Item> items) {
        if (items.isEmpty()) System.out.println("Inventario vacío.");
        else items.forEach(System.out::println);
    }

    public void mostrarDetallesItem(Item item) {
        if (item == null) System.out.println("Item no encontrado.");
        else {
            System.out.println("\nNombre: " + item.getNombre());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Descripción: " + item.getDescripcion());
        }
    }

    public String leerTexto(String msg) {
        System.out.print(msg + ": ");
        return sc.nextLine();
    }

    public int leerEntero(String msg) {
        System.out.print(msg + ": ");
        return Integer.parseInt(sc.nextLine());
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
