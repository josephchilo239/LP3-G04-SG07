package Ejercicio2;
import java.util.*;

public class MainStrategy {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CalculadoraDePrecios calc = new CalculadoraDePrecios();

        List<Producto> productos = new ArrayList<>();

        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Teclado", 100));
        productos.add(new Producto("Pc", 1000));

        int opcion;
        do {
            System.out.println("\n-- MENÚ DE DESCUENTOS --");
            System.out.println("1. Sin Descuento");
            System.out.println("2. Descuento Fijo 10%");
            System.out.println("3. Descuento Porcentual (2 iguales 30%)");
            System.out.println("4. Descuento Acumulado (3 productos → 50% al menor)");
            System.out.println("5. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> calc.setEstrategia(new SinDescuento());
                case 2 -> calc.setEstrategia(new DescuentoFijo());
                case 3 -> calc.setEstrategia(new DescuentoPorcentual());
                case 4 -> calc.setEstrategia(new DescuentoPorcentualAcumulado());
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }

            if (opcion >= 1 && opcion <= 4) {
                System.out.println("Precio final: " + calc.calcular(productos));
            }

        } while (opcion != 5);

        sc.close();
    }
}
