package EjercicioTres;
import java.util.List;
import java.util.Scanner;

public class CombateVista {
    private Scanner sc = new Scanner(System.in);

    public void mostrarEstado(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n=== ESTADO DEL COMBATE ===");
        System.out.println(jugador.getNombre() + " - HP: " + jugador.getSalud());
        System.out.println(enemigo.getNombre() + " - HP: " + enemigo.getSalud());
    }

    public String leerAccion() {
        System.out.println("\n1. Atacar");
        System.out.println("2. Usar objeto");
        System.out.println("3. Pasar turno");
        System.out.print("Elige una acción: ");
        return sc.nextLine();
    }

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n--- Inventario ---");
        if (items.isEmpty()) System.out.println("Vacío.");
        else items.forEach(System.out::println);
    }

    public String leerTexto(String msg) {
        System.out.print(msg + ": ");
        return sc.nextLine();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
