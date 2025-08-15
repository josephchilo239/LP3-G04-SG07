package Ejercicios;
import java.util.Scanner;

public class Quinto {

    public static double calcularCargo(int horas) {
        double cargo = 3.0;

        if (horas > 1) {
            cargo += (horas - 1) * 0.5;
        }

        return Math.min(cargo, 12.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de horas de estacionamiento: ");
        int horas = scanner.nextInt();

        double total = calcularCargo(horas);
        System.out.printf("El cargo por %d hora(s) es: S/%.2f\n", horas, total);
    }
}
