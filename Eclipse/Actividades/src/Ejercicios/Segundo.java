package Ejercicios;
import java.util.Scanner;
public class Segundo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arreglo = new int[10];

        System.out.println("Ingrese 10 números en orden creciente:");

        for (int i = 0; i < 10; i++) {
            int numero;
            while (true) {
                System.out.print("Número " + (i + 1) + ": ");
                
                if (scanner.hasNextInt()) {
                    numero = scanner.nextInt();

                    if (i == 0 || numero > arreglo[i - 1]) {
                        arreglo[i] = numero;
                        break;
                    } else {
                        System.out.println("El número debe ser mayor que el anterior (" + arreglo[i - 1] + "). Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada inválida. Ingrese un número entero.");
                    scanner.next(); 
                }
            }
        }

        System.out.println("\nNúmeros ingresados en orden creciente:");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
