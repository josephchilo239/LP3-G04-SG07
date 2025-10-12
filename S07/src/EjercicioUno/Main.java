package EjercicioUno;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n--- MENÚ GESTOR DE PERSONAJES ---");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Salir");
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
                    gestor.eliminar(n);
                }
            }
        } while (op != 5);
    }
}
