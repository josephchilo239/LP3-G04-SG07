package EjercicioTres;
import java.util.*;

public class EmpleadoVista {
    private static final Scanner sc = new Scanner(System.in);
    private static final EmpleadoControlador controlador = new EmpleadoControlador();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- GESTOR DE EMPLEADOS ---");
            System.out.println("1. Listar todos los empleados");
            System.out.println("2. Agregar nuevo empleado");
            System.out.println("3. Buscar empleado por número");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> listar();
                case 2 -> agregar();
                case 3 -> buscar();
                case 4 -> eliminar();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void listar() {
        System.out.println("\n-- LISTADO DE EMPLEADOS --");
        var empleados = controlador.leerEmpleados();
        if (empleados.isEmpty()) System.out.println("No hay empleados registrados.");
        else empleados.forEach(System.out::println);
    }

    private static void agregar() {
        System.out.print("Número: ");
        int num = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = sc.nextDouble();

        controlador.agregarEmpleado(new Empleado(num, nombre, sueldo));
    }

    private static void buscar() {
        System.out.print("Ingrese el número del empleado: ");
        int num = sc.nextInt();
        Empleado emp = controlador.buscarEmpleado(num);
        if (emp != null) System.out.println(emp);
        else System.out.println("Empleado no encontrado.");
    }

    private static void eliminar() {
        System.out.print("Ingrese el número del empleado a eliminar: ");
        int num = sc.nextInt();
        controlador.eliminarEmpleado(num);
    }
}
