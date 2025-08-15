package Ejercicios;
import java.util.Random;

public class Tercero {
    public static void main(String[] args) {
        int[] frecuencias = new int[6]; 
        Random random = new Random();

        for (int i = 0; i < 20000; i++) {
            int resultado = random.nextInt(6);
            frecuencias[resultado]++;
        }

        System.out.println("Frecuencia de cada cara del dado en 20,000 lanzamientos:");
        for (int i = 0; i < frecuencias.length; i++) {
            System.out.println("Cara " + (i + 1) + ": " + frecuencias[i]);
        }
    }
}
