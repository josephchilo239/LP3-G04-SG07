package Ejercicios;
import java.util.Scanner;

public class Cuarto {
	
    public static double menor(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
        
    }
    
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el primer número decimal: ");
        double num1 = scanner.nextDouble();
        System.out.print("Ingrese el segundo número decimal: ");
        double num2 = scanner.nextDouble();
        System.out.print("Ingrese el tercer número decimal: ");
        double num3 = scanner.nextDouble();
        double resultado = menor(num1, num2, num3);
        System.out.println("El menor de los tres números es: " + resultado);
        
    }
}
