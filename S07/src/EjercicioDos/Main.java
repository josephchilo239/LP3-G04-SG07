package EjercicioDos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n--- MENÚ AVANZADO ---");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Actualizar atributo individual");
            System.out.println("5. Filtrar por atributo");
            System.out.println("6. Mostrar estadísticas");
            System.out.println("7. Subir nivel");
            System.out.println("8. Importar desde archivo externo");
            System.out.println("9. Eliminar personaje");
            System.out.println("10. Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> gestor.mostrar();
                case 2 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Vida: ");
                    int v = sc.nextInt();
                    System.out.print("Ataque: ");
                    int a = sc.nextInt();
                    System.out.print("Defensa: ");
                    int d = sc.nextInt();
                    System.out.print("Alcance: ");
                    int al = sc.nextInt();
                    gestor.agregar(new Personaje(n, v, a, d, al));
                }
                case 3 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Nueva vida: ");
                    int v = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int a = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int d = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int al = sc.nextInt();
                    gestor.modificar(n, v, a, d, al);
                }
                case 4 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Atributo a cambiar (vida, ataque, defensa, alcance): ");
                    String atr = sc.nextLine();
                    System.out.print("Nuevo valor: ");
                    int val = sc.nextInt();
                    gestor.actualizarAtributo(n, atr, val);
                }
                case 5 -> {
                    System.out.print("Atributo para filtrar: ");
                    String atr = sc.nextLine();
                    gestor.filtrarPor(atr);
                }
                case 6 -> gestor.estadisticas();
                case 7 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    gestor.subirNivel(n);
                }
                case 8 -> {
                    System.out.print("Nombre del archivo externo: ");
                    String arch = sc.nextLine();
                    gestor.importarDesdeArchivo(arch);
                }
                case 9 -> {
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    gestor.eliminar(n);
                }
                case 10 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 10);
    }
}
